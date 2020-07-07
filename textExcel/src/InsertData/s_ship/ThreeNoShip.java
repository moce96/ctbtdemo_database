package InsertData.s_ship;

import getAllByExcel.port.GetPort;
import getAllByExcel.ship.GetEquipmentType;
import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomNumber;
import utilClass.port.Port;
import utilClass.ship.EquipmentType;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ThreeNoShip {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void threeNoShip() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] deviceState = {"较差","良好","优秀"};
        String[] photo = {"http://pic17.nipic.com/20111101/3179217_122853071000_2.jpg","http://pic16.nipic.com/20110919/7264122_172835404113_2.jpg","http://pic30.nipic.com/20130626/8821914_114141685134_2.jpg"};



        // 创建获得 ship excel 表数据的类
        GetShip getShipExcel = new GetShip();
        GetEquipmentType getEquipmentType = new GetEquipmentType();
        GetPort getPort = new GetPort();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<EquipmentType> equipmentTypes = getEquipmentType.getAllByExcel();
        List<Port> portList = getPort.getAllByExcel();

        //开始插入数据
        for (int i=0;i<=10;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO s_threeNoShip(loc,time,portId,photo)" +
                        "VALUES(?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,"a");
                pstm.setObject(2,randomDate.generateRandomDate("2018-01-01","2019-01-01"));
                int j = random.nextInt(portList.size());
                pstm.setString(3,portList.get(j).portId);
                pstm.setString(4,photo[random.nextInt(photo.length)]);







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
