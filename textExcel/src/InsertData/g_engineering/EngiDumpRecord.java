package InsertData.g_engineering;

import getAllByExcel.engineering.GetDumpPlace;
import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomNumber;
import utilClass.engineering.DumpPlace;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class EngiDumpRecord {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void engiDumpRecord() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] loadAmount = {"3000立方米","4000立方米","5000立方米"};
        String[] dumpAmount = {"1000立方米","2000立方米","3000立方米"};
        String[] dumpLoc = {"122.30,30.20","122.31,30.21","122.25,30.15"};


        // 创建获得  excel 表数据的类
        GetShip getShip = new GetShip();
        GetDumpPlace getDumpPlace = new GetDumpPlace();



        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Ship> shipList = getShip.getAllByExcel();
        List<DumpPlace> dumpPlaceList = getDumpPlace.getAllByExcel();




        //开始插入数据
        for (int i=0; i<=50; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO g_engiDumpRecord(shipId,loadAmount,dumpAmount,dumpDate,dumpLoc,dumpPlaceId)" +
                        "VALUES(?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                //找到船舶基础表中  工程船的id 才可以插入该数据表，因为只有工程船才能进行工程。
                int j = random.nextInt(shipList.size());
                while (!shipList.get(j).shipTypeId.equals("2")) {
                    j = random.nextInt(shipList.size());
                }
                pstm.setString(1,shipList.get(j).shipId);
                pstm.setString(2,loadAmount[random.nextInt(loadAmount.length)]);
                pstm.setString(3,dumpAmount[random.nextInt(dumpAmount.length)]);
                pstm.setObject(4,randomDate.generateRandomDate("2019-01-01","2019-06-06"));
                pstm.setString(5,dumpLoc[random.nextInt(dumpLoc.length)]);
                j = random.nextInt(dumpPlaceList.size());
                pstm.setString(6,dumpPlaceList.get(j).dumpPlaceId);


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
