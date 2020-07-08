package InsertData;

import getAllByExcel.GetInvestigation;
import getAllByExcel.GetLawEnforcement;
import getAllByExcel.GetRegion;
import getAllByExcel.crew.GetCrew;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomNumber;
import utilClass.Region;
import utilClass.crew.Crew;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class RegionBW {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void regionBW() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] listType = {"黑","白"};




        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();
        GetRegion getRegion = new GetRegion();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> shipList = getShip.getAllByExcel();
        List<Region> regionList = getRegion.getAllByExcel();



        //开始插入数据
        for (int i=0; i<250; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO z_regionBW(regionId,listType,shipId)" +
                        "VALUES(?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(regionList.size());
                pstm.setString(1,regionList.get(j).regionId);
                pstm.setString(2,listType[random.nextInt(listType.length)]);
                j = random.nextInt(shipList.size());
                pstm.setString(3,shipList.get(j).shipId);

                pstm.executeUpdate();


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

                try {
                    conn.close();
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }


        }

    }



}
