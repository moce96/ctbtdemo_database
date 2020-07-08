package InsertData.e_accident;

import getAllByExcel.ship.GetShip;
import random.MysqlRead;
import random.RandomDate;
import random.RandomNumber;
import utilClass.Investigation;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class e_accidentFoundation {
    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void accidentFoudation() {
        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] longitude = {"30.19", "30.22", "30.33", "30.22", "30.36", "30.17", "30.25", "30.12", "30.25", "30.26", "30.39", "30.22", "30.13", "30.22", "30.16"};
        String[] latitude = {"121.94", "121.55", "122.34", "123.11", "122.56", "121.93", "121.65", "122.25", "123.09", "122.44", "121.94", "121.25", "122.34", "123.21", "122.56"};
        String[] cause = {"渔船碰撞", "渔船搁浅", "渔船损坏", "自然灾害事故"};
        Map<String, String[]> accidentLoss = new HashMap<String, String[]>();
        accidentLoss.put("渔船碰撞", new String[]{"船体轻微损坏", "设备损坏", "船体严重损坏"});
        accidentLoss.put("渔船搁浅", new String[]{"船体轻微损坏", "设备损坏", "船体严重损坏"});
        accidentLoss.put("渔船损坏", new String[]{"船体严重损坏", "船体轻微损坏"});
        accidentLoss.put("自然灾害事故", new String[]{"船体严重损坏", "设备损坏", "人员伤亡"});

        GetShip getShip = new GetShip();
        List<Ship> shipList = getShip.getAllByExcel();

        //设置添加数据条数i
        for (int i = 0; i < 100; i++) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO     e_accidentFoundation(accidentId,accidentName,accidentLoc,accidentReason,accidentLoss,involveShipId,accidentTime)" +
                        "VALUES(?,?,?,?,?,?,?)";

                pstm = conn.prepareStatement(sql);

                String accidentId = new String().valueOf(4000000 + random.nextInt(1000000));
                pstm.setString(1, accidentId);
                pstm.setString(2, accidentId + "事故");
                pstm.setString(3, longitude[random.nextInt(longitude.length)] + "," + latitude[random.nextInt(latitude.length)]);
                String chosenCause = cause[random.nextInt(cause.length)];
                pstm.setString(4, chosenCause);
                pstm.setString(5, accidentLoss.get(chosenCause)[random.nextInt(accidentLoss.get(chosenCause).length)]);
                if (chosenCause == "渔船碰撞")
                    pstm.setString(6, shipList.get(random.nextInt(shipList.size())).shipId + "," + shipList.get(random.nextInt(shipList.size())).shipId);
                else
                    pstm.setString(6, shipList.get(random.nextInt(shipList.size())).shipId);
                pstm.setObject(7, randomDate.generateRandomDate("2018-01-01", "2020-07-01"));


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
