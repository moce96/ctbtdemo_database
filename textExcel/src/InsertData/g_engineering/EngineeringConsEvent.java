package InsertData.g_engineering;

import getAllByExcel.GetRegion;
import getAllByExcel.engineering.GetEngineering;
import random.*;
import random.RandomNumber;
import utilClass.Region;
import utilClass.engineering.Engineering;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class EngineeringConsEvent {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void engineeringConsEvent() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] object = {"工人","工程"};
        String[] eventContent1 = {"工人操作不慎，发生受伤事件","工人因操作失误，造成不良后果"};
        String[] eventContent2 = {"遭遇地质渗流问题","现场施工道具因故损坏"};
        String[] event1 = {"工人受伤","工人操作失误","工程地质事故","工程道具损坏"};
        String[] event2 = {"工程地质事故","工程道具损坏"};
        String[] photo = {"https://www.16pic.com/pic/pic_7573700.html"};


        // 创建获得  excel 表数据的类
        GetEngineering getEngineering = new GetEngineering();
        GetRegion getRegion = new GetRegion();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Engineering> engineeringList = getEngineering.getAllByExcel();
        List<Region> regionList = getRegion.getAllByExcel();




        //开始插入数据
        for (int i=0; i<=50; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO g_engineeringConsEvent(engiId,Date,object,eventContent,event,photo)" +
                        "VALUES(?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(engineeringList.size());
                pstm.setString(1,engineeringList.get(j).engineeringId);
                pstm.setObject(2,randomDate.generateRandomDate("2018-10-10","2019-12-12"));
                j = random.nextInt(object.length);
                pstm.setString(3,object[j]);
                if (object[j].equals("工人")) {
                    pstm.setString(4,eventContent1[random.nextInt(eventContent1.length)]);
                    pstm.setString(5,event1[random.nextInt(event1.length)]);
                } else {
                    pstm.setString(4,eventContent2[random.nextInt(eventContent2.length)]);
                    pstm.setString(5,event2[random.nextInt(event2.length)]);
                }
                pstm.setString(6,photo[random.nextInt(photo.length)]);




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
