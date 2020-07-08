package InsertData.a_announce;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import getAllByExcel.user.GetUser;
import random.Json2;
import random.*;
import random.RandomNumber;
import utilClass.user.User;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class AnnounceConsultSituation {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void  AnnounceConsultSituation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        GetUser getUser = new GetUser();
        List<User> userList = getUser.getAllByExcel();

        String[] sendWay = {"微信","QQ","钉钉"};



        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();

        jsonArray = Json2.generate();  //生成json数组

        for (int i=0; i<jsonArray.size(); i++) {
            jsonObject = (JsonObject) jsonArray.get(i);
            JsonArray jsonElements = (JsonArray) jsonObject.get("element");

            for (int j=0; j<jsonElements.size(); j++) {

                JsonObject jsonObject1 = (JsonObject) jsonElements.get(j);

                //判断该user是否阅读了公告，有则插入数据，没有则不插入数据
                User user1 = null;
                for (User a : userList) {
                    if (a.userId.equals(String.valueOf(jsonObject1.get("personId")).substring(1,String.valueOf(jsonObject1.get("personId")).length()-1))) {
                        user1 = a;
                        break;
                    }
                }

                if (user1.announcementReading.equals("1")) {

                    try {
                        Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                        String sql = "INSERT INTO   a_announceConsultSituation(announceId,consultedPersonId,consultTime)" +
                                "VALUES(?,?,?)";

                        pstm = conn.prepareStatement(sql);

                        pstm.setString(1,String.valueOf(jsonObject.get("announceId")).substring(1,String.valueOf(jsonObject.get("announceId")).length()-1));
                        pstm.setString(2,String.valueOf(jsonObject1.get("personId")).substring(1,String.valueOf(jsonObject1.get("personId")).length()-1));
                        pstm.setObject(3,randomDate.generateRandomDate("2018-01-01","2018-06-06"));

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


}
