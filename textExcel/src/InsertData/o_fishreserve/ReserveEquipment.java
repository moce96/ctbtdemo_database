package InsertData.o_fishreserve;

import getAllByExcel.ship.GetShip;
import random.MysqlRead;
import random.RandomDate;
import random.RandomJson;
import random.RandomNumber;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ReserveEquipment {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void reserveEquipment() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] equipmentType = {"摄像头","温度传感器","水质传感器","摄像头","摄像头"};
        String[] status = {"正在运行","正在运行","正在运行","正在运行","已损坏"};
        String[] loc1 = {"121.94","121.55","122.34","123.11","122.56","121.93","121.65","122.25","123.09","122.44","121.94","121.25","122.34","123.21","122.56"};
        String[] loc2 = {"30.19","30.22","30.33","30.22","30.36","30.17","30.25","30.12","30.25","30.26","30.39","30.22","30.13","30.22","30.16"};





        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> shipList = getShip.getAllByExcel();



        //开始插入数据
        for (int i=0; i<5; i++) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  o_reserveEquipment(reserveId,equipmentId,equipmentType,equipmentName,time,status,longitude,latitude)" +
                        "VALUES(?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,"1001");
                String id = randomNumber.generate(1,4);
                pstm.setString(2,id);
                pstm.setString(3,equipmentType[random.nextInt(equipmentType.length)]);
                pstm.setString(4,id+"号设备");
                pstm.setObject(5,randomDate.generateRandomDate("2018-01-01","2018-06-06"));
                pstm.setString(6,status[random.nextInt(status.length)]);
                pstm.setString(7,"jindu");
                pstm.setString(8,"weidu");


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
