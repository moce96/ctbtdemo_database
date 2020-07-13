package InsertData.g_engineering;

import getAllByExcel.GetRegion;
import getAllByExcel.engineering.GetEngineering;
import random.MysqlRead;
import random.RandomDate;
import random.RandomNumber;
import utilClass.Region;
import utilClass.engineering.Engineering;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class EngiEquipment {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void engiEquipment() {
    Random random = new Random();
    RandomDate randomDate = new RandomDate();
    RandomNumber randomNumber = new RandomNumber();

    String[] equipmentType = {"摄像头","温度传感器","灰尘检测器","湿度检测器"};
    String[] status = {"正在运行","未运行","正在运行","正在运行","正在运行","正在运行"};
    String[] engiState = {"建设期","运营期"};


    // 创建获得  excel 表数据的类
    GetEngineering getEngineering = new GetEngineering();
    GetRegion getRegion = new GetRegion();


    // 用从表里获得的数据  生成列表  用于填充数据库
    List<Engineering> engineeringList = getEngineering.getAllByExcel();
    List<Region> regionList = getRegion.getAllByExcel();




    //开始插入数据
        for (int i=0; i<40; i++) {

        try {
            Class.forName(mysqlMessage[0]);
            conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
            String sql = "INSERT INTO  g_engiEquipment(engiId,equipmentId,equipmentType,equipmentName,time,status,longitude,latitude)" +
                    "VALUES(?,?,?,?,?,?,?,?)";





            pstm = conn.prepareStatement(sql);

            int j = random.nextInt(engineeringList.size());
            pstm.setString(1,engineeringList.get(j).engineeringId);
            String id = randomNumber.generate(1,4);
            pstm.setString(2,id);
            pstm.setString(3,equipmentType[random.nextInt(equipmentType.length)]);
            pstm.setString(4,id+"号"+"监测设备");
            pstm.setObject(5,randomDate.generateRandomDate("2018-01-01","2020-01-01"));
            pstm.setString(6,status[random.nextInt(status.length)]);
            pstm.setString(7,"longitude");
            pstm.setString(8,"latitude");





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
