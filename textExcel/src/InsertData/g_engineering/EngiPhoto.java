package InsertData.g_engineering;

import getAllByExcel.engineering.GetAudit;
import getAllByExcel.engineering.GetEngineering;
import random.*;
import random.RandomNumber;
import utilClass.engineering.Audit;
import utilClass.engineering.Engineering;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class EngiPhoto {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void engiPhoto() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] photo = {"http://699pic.com/tupian-501224087.html"};

        // 创建获得  excel 表数据的类
        GetAudit getAudit = new GetAudit();
        GetEngineering getEngineering = new GetEngineering();



        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Audit> auditList = getAudit.getAllByExcel();
        List<Engineering> engineeringList = getEngineering.getAllByExcel();





        //开始插入数据
        for (int i=0; i<=50; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO g_engiPhoto(engiId,collectionDate,photo)" +
                        "VALUES(?,?,?)";


                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(engineeringList.size());
                pstm.setString(1,engineeringList.get(j).engineeringId);
                pstm.setObject(2,randomDate.generateRandomDate("2019-01-01","2019-12-12"));
                pstm.setString(3,photo[random.nextInt(photo.length)]);


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
