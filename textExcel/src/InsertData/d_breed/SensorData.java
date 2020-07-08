package InsertData.d_breed;

import getAllByExcel.breed.GetAquaBase;
import getAllByExcel.breed.GetBreedPerson;
import getAllByExcel.breed.GetSensor;
import getAllByExcel.company.GetCompany;
import random.*;
import random.RandomNumber;
import utilClass.breed.AquaBase;
import utilClass.breed.BreedPerson;
import utilClass.breed.Sensor;
import utilClass.company.Company;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class SensorData {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void sendsorData() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] healthCondition = {"健康","健康","健康","健康","不健康"};
        String[] data = {"http://pic.5tu.cn/photo/201411/duoren-140924.html"};


        // 创建获得  excel 表数据的类
        GetSensor getSensor = new GetSensor();



        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Sensor> sensorList = getSensor.getAllByExcel();




        //开始插入数据
        for (int i=0; i<=100; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_sensorData(sensorId,data,dateTime)" +
                        "VALUES(?,?,?)";


                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(sensorList.size());
                pstm.setString(1,sensorList.get(j).id);
                pstm.setString(2,data[random.nextInt(data.length)]);
                pstm.setObject(3,randomDate.generateRandomDate("2019-01-01","2019-12-12"));

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
