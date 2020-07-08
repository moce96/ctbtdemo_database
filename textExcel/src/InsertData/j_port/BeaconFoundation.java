package InsertData.j_port;

import getAllByExcel.crew.GetCrew;
import getAllByExcel.port.GetBeacon;
import getAllByExcel.port.GetPort;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomJson;
import random.RandomNumber;
import utilClass.crew.Crew;
import utilClass.port.Beacon;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class BeaconFoundation {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void beaconFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();





        // 创建获得 ship excel 表数据的类
        GetBeacon getBeacon = new GetBeacon();



        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Beacon> beaconList = getBeacon.getAllByExcel();

        //开始插入数据
        for (Beacon beacon : beaconList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  j_beaconFoundation(beaconId,type,regionId)" +
                        "VALUES(?,?,?)";



                pstm = conn.prepareStatement(sql);


                pstm.setString(1,beacon.beaconId);
                pstm.setString(2,beacon.type);
                pstm.setString(3,beacon.regionId);

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
