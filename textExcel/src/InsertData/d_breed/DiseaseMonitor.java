package InsertData.d_breed;

import getAllByExcel.breed.GetAquaBase;
import getAllByExcel.breed.GetBreedPerson;
import getAllByExcel.breed.GetMonitorPoint;
import getAllByExcel.company.GetCompany;
import random.RandomDate;
import random.RandomNumber;
import utilClass.breed.AquaBase;
import utilClass.breed.BreedPerson;
import utilClass.breed.MonitorPoint;
import utilClass.company.Company;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class DiseaseMonitor {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void diseaseMonitor() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] name1 = {"撒","个","阿萨","发给","地方"};
        String[] name2 = {"范德","热帖","发到","手动","发到"};
        String[] name3 = {"地方","啊","阿萨","发","官方"};
        String[] longitude = {"122.2351","122.2596","122.2912","122.2101","122.2412","122.2722","122.2234"};
        String[] latitude = {"30.1216","30.1196","30.1206","30.1431","30.1551","30.1526","30.1765"};
        String[] range = {"5","10","15"};


        // 创建获得  excel 表数据的类
        GetMonitorPoint getMonitorPoint = new GetMonitorPoint();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<MonitorPoint> monitorPointList = getMonitorPoint.getAllByExcel();




        //开始插入数据
        for (MonitorPoint monitorPoint : monitorPointList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_diseaseMonitor(monitorPointId,monitorPointName,monitorPointLoc,buildTime)" +
                        "VALUES(?,?,?,?)";


                pstm = conn.prepareStatement(sql);


                pstm.setString(1,monitorPoint.id);
                pstm.setString(2,name1[random.nextInt(name1.length)]+name2[random.nextInt(name2.length)]+name3[random.nextInt(name3.length)]+"监测点");
                pstm.setString(3,longitude[random.nextInt(longitude.length)]+","+latitude[random.nextInt(latitude.length)]);
                pstm.setObject(4,randomDate.generateRandomDate("2019-01-01","2019-12-12"));
                int j = random.nextInt(range.length);



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
