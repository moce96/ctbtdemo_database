package InsertData;

import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomJson;
import random.RandomNumber;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class FisheryProtectedArea {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void  fisheryProtectedArea(){

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] regionId = {"10014","10001"};

        String[] first = {"新","汇","石","德","洛","立","台"};
        String[] second = {"业","泉","佬","阳","东","藏","登"};
        String[] description = {"国家级渔业保护区","浙江省渔业保护区"};




        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> shipList = getShip.getAllByExcel();



        //开始插入数据
        for (int i=0; i<10; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  z_fisheryProtectedArea(protectedAreaName,protectedAreaId,regionId,description,buildTime)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,first[random.nextInt(first.length)]+second[random.nextInt(second.length)]+"保护区");
                pstm.setString(2,randomNumber.generate(1,4));
                pstm.setString(3,regionId[random.nextInt(regionId.length)]);
                pstm.setString(4,description[random.nextInt(description.length)]);
                pstm.setObject(5,randomDate.generateRandomDate("2017-01-01","2017-06-06"));


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
