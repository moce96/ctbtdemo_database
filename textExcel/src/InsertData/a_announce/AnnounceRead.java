package InsertData.a_announce;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import getAllByExcel.user.GetUser;
import random.Json2;
import random.MysqlRead;
import random.*;
import random.RandomNumber;
import utilClass.user.User;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class AnnounceRead {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void  AnnounceRead() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();


        GetUser getUser = new GetUser();
        List<User> userList = getUser.getAllByExcel();



        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();

        jsonArray = Json2.generate();  //生成json数组

        for (int i=0; i<jsonArray.size(); i++) {
            jsonObject = (JsonObject) jsonArray.get(i);
            JsonArray jsonElements = (JsonArray) jsonObject.get("element");

            for (int j=0; j<jsonElements.size(); j++) {

                JsonObject jsonObject1 = (JsonObject) jsonElements.get(j);
                try {
                    Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                    String sql = "INSERT INTO   a_announceRead(announceId,userId,announceRead)" +
                            "VALUES(?,?,?)";

                    pstm = conn.prepareStatement(sql);

                    pstm.setString(1,String.valueOf(jsonObject.get("announceId")).substring(1,String.valueOf(jsonObject.get("announceId")).length()-1));
                    pstm.setString(2,String.valueOf(jsonObject1.get("personId")).substring(1,String.valueOf(jsonObject1.get("personId")).length()-1));

                    //在userList中找到相同userId的用户，读取里面的announceRead
                    User user1 = null ;
                    for (User a : userList) {
                        if (a.userId.equals(String.valueOf(jsonObject1.get("personId")).substring(1,String.valueOf(jsonObject1.get("personId")).length()-1))) {
                            user1 = a;
                            break;
                        }
                    }
                    pstm.setObject(3,user1.announcementReading);

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



}
