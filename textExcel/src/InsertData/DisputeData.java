package InsertData;

/**
 * 渔事纠纷
 */

import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomJson;
import random.RandomNumber;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class DisputeData {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void disputeData() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] disputeDiscription = {"船体碰撞","发生口角","渔船追尾","争抢渔业资源"};
        String[] conclusion = {"赔偿解决","协商解决"};
        String[] loc1 = {"121.94","121.55","122.34","123.11","122.56","121.93","121.65","122.25","123.09","122.44","121.94","121.25","122.34","123.21","122.56"};
        String[] loc2 = {"30.19","30.22","30.33","30.22","30.36","30.17","30.25","30.12","30.25","30.26","30.39","30.22","30.13","30.22","30.16"};





        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> shipList = getShip.getAllByExcel();



        //开始插入数据
        for (int i=0; i<50; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  z_disputeData(fisheryDisputeId,disputeDate,disputeDiscription,Loc,conclusion,disputePhoto,enforceLawPhoto,involveShip)" +
                        "VALUES(?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,randomNumber.generate(1,5));
                pstm.setObject(2,randomDate.generateRandomDate("2018-01-01","2019-12-12"));
                pstm.setString(3,disputeDiscription[random.nextInt(disputeDiscription.length)]);
                pstm.setString(4,loc1[random.nextInt(loc1.length)]+","+loc2[random.nextInt(loc2.length)]);
                pstm.setString(5,conclusion[random.nextInt(conclusion.length)]);
                pstm.setString(6,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583769817460&di=278e6424e8240bff7635ffa22c66f52c&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20070814%2FImg251570392.jpg;https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583769817460&di=278e6424e8240bff7635ffa22c66f52c&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20070814%2FImg251570392.jpg");
                pstm.setString(7,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583769817460&di=278e6424e8240bff7635ffa22c66f52c&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20070814%2FImg251570392.jpg");
                int j = random.nextInt(2)+2;
                pstm.setString(8,randomJson.generateShip(j,shipList));


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
