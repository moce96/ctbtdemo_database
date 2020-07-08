package InsertData.s_ship;

import getAllByExcel.ship.GetShip;
import random.MysqlRead;
import random.*;
import random.RandomNumber;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class OilPatch {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void oilPatch() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] auditSituation = {"已审核","未审核"};


        // 创建获得 ship excel 表数据的类
        GetShip getShipExcel = new GetShip();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();

        //开始插入数据
        for (int i=0;i<=3000;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO s_oilPatch(shipId,startTime,endTime,voyageMileage,voyageList,oilPatchMoney,auditSituation)" +
                        "VALUES(?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                int j = random.nextInt(ships.size());
                pstm.setString(1,ships.get(j).shipId);
                pstm.setObject(2,randomDate.generateRandomDate("2017-01-01","2017-06-01"));
                pstm.setObject(3,randomDate.generateRandomDate("2017-06-02","2018-01-11"));
                int k = random.nextInt(50)+20;
                pstm.setDouble(4,k);
                pstm.setString(5,"voyagelist");
                pstm.setDouble(6,k*0.4);
                pstm.setString(7,auditSituation[random.nextInt(auditSituation.length)]);



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
