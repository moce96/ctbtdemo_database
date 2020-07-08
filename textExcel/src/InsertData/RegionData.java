package InsertData;

import getAllByExcel.GetRegion;
import getAllByExcel.port.GetPort;
import getAllByExcel.port.GetPortRegion;
import random.*;
import random.RandomNumber;
import utilClass.Region;
import utilClass.port.Port;
import utilClass.port.PortRegion;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class RegionData {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void regionData() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] listType = {"黑","白"};




        // 创建获得 ship excel 表数据的类
        GetRegion getRegion = new GetRegion();
        GetPort getPort = new GetPort();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Region> regions = getRegion.getAllByExcel();
        List<Port> portList = getPort.getAllByExcel();



        //开始插入数据
        for (Port port : portList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO z_regionData(regionId,regionName,regionTypeId)" +
                        "VALUES(?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,port.regionId);
                pstm.setString(2,port.name);
                pstm.setString(3,"港口");
//                pstm.setString(4,port.regionRange);


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
