package InsertData.s_ship;

import getAllByExcel.GetPersonId;
import getAllByExcel.ship.GetShip;
import getAllByExcel.ship.GetShipGroup;
import random.MysqlRead;
import random.RandomDate;
import random.RandomNumber;
import utilClass.Person;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipCirculation {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void shipCirculation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();


        String[] belongType = {"个人","单位","单位","单位"};




        // 创建获得 excel 表数据的类
        GetShip getShipExcel = new GetShip();
        GetShipGroup getShipGroup = new GetShipGroup();

        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<utilClass.ship.ShipGroup> shipGroups = getShipGroup.getAllByExcel();

        //开始插入数据
        for (Ship ship : ships) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO s_shipCirculation(shipId,belongType,belongObjectId,transferInDate,transferOutDate)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                pstm.setString(1,ship.shipId);
                int j = random.nextInt(belongType.length);
                pstm.setString(2,belongType[j]);
                if (belongType[j] == "单位") {
                    pstm.setString(3,ship.companyId);
                } else {
                    pstm.setString(3,randomNumber.generate(1,18));
                }
                pstm.setObject(4,randomDate.generateRandomDate("2018-01-01","2018-12-12"));
                pstm.setObject(5,randomDate.generateRandomDate("2020-01-01","2020-12-12"));






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
