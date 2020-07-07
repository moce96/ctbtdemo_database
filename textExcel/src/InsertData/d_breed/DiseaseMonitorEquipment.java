package InsertData.d_breed;

import getAllByExcel.breed.GetMonitorPoint;
import getAllByExcel.breed.GetSensor;
import random.RandomDate;
import random.RandomNumber;
import utilClass.breed.MonitorPoint;
import utilClass.breed.Sensor;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class DiseaseMonitorEquipment {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void diseaseMonitorEquipment() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] sensorName = {"A","B","C"};
        String[] sensorType = {"摄像头","雷达"};

        // 创建获得  excel 表数据的类
        GetSensor getSensor = new GetSensor();
        GetMonitorPoint getMonitorPoint = new GetMonitorPoint();



        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Sensor> sensorList = getSensor.getAllByExcel();
        List<MonitorPoint> monitorPointList = getMonitorPoint.getAllByExcel();




        //开始插入数据
        for (Sensor sensor : sensorList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_diseaseMonitorEquipment(monitorPointId,sensorId,sensorName,sensorType,sensorDeployDate)" +
                        "VALUES(?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(monitorPointList.size());
                pstm.setString(1,monitorPointList.get(j).id);
                pstm.setString(2,sensor.id);
                pstm.setString(3,sensorName[random.nextInt(sensorName.length)]+sensor.id);
                pstm.setString(4,sensorType[random.nextInt(sensorType.length)]);
                pstm.setObject(5,randomDate.generateRandomDate("2019-01-01","2019-12-12"));

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
