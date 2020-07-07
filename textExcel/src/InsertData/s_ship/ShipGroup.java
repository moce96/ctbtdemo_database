package InsertData.s_ship;

import getAllByExcel.ship.GetShip;
import getAllByExcel.ship.GetShipGroup;
import random.RandomDate;
import random.RandomNumber;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipGroup {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void shipGroup() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();







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
                String sql = "INSERT INTO s_shipGroup(groupId,shipId,startTime,endTime,groupName,description)" +
                        "VALUES(?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                int j = random.nextInt(shipGroups.size());
                pstm.setString(1,shipGroups.get(j).id);
                pstm.setString(2,ship.shipId);
                pstm.setObject(3,randomDate.generateRandomDate("2017-01-01","2017-06-06"));
                pstm.setObject(4,randomDate.generateRandomDate("2021-01-01","2021-06-06"));
                pstm.setString(5,shipGroups.get(j).name);
                pstm.setString(6,shipGroups.get(j).description);





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
