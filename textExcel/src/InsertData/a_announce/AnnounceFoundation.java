package InsertData.a_announce;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import random.Json2;
import random.*;
import random.RandomNumber;

import java.sql.*;
import java.util.Random;

public class AnnounceFoundation {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void announceFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] sendWay = {"微信","QQ","钉钉","网上公示"};



        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();

        jsonArray = Json2.generate();  //生成json数组

        for (int i=0; i<jsonArray.size(); i++) {

            jsonObject = (JsonObject) jsonArray.get(i);

            System.out.println(String.valueOf(jsonObject.get("announceId")));


            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  a_announceFoundation(announceId,startTime,readPerson,sendWay,announceContent)" +
                        "VALUES(?,?,?,?,?)";

                pstm = conn.prepareStatement(sql);

                pstm.setString(1,String.valueOf(jsonObject.get("announceId")).substring(1,String.valueOf(jsonObject.get("announceId")).length()-1));
                pstm.setObject(2,randomDate.generateRandomDate("2018-01-01","2018-06-06"));
                pstm.setString(3,String.valueOf(jsonObject.get("element")));
                pstm.setString(4,sendWay[random.nextInt(sendWay.length)]);
                pstm.setString(5,"zanwu");

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


