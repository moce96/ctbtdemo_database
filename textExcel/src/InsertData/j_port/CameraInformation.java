package InsertData.j_port;

import getAllByExcel.port.GetBeacon;
import random.*;
import random.RandomJson;
import random.RandomNumber;
import utilClass.port.Beacon;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class CameraInformation {
    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void cameraInformation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};

        String[] downReason = {"发生碰撞","天气问题","耐久度问题"};


        // 创建获得 ship excel 表数据的类
        GetBeacon getBeacon = new GetBeacon();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Beacon> beaconList = getBeacon.getAllByExcel();



        //开始插入数据
        for (int i=0; i<=70; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  j_cameraInformation(cameraId,photo,date)" +
                        "VALUES(?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(beaconList.size());
                while (!beaconList.get(j).type.equals("摄像头")) {
                    j = random.nextInt(beaconList.size());
                }
                pstm.setString(1,beaconList.get(j).beaconId);
                pstm.setString(2,"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2890983543,196835147&fm=26&gp=0.jpg");
                pstm.setObject(3,randomDate.generateRandomDate("2018-07-01","2019-01-01"));

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
