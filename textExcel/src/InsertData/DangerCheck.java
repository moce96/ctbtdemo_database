package InsertData;

import getAllByExcel.GetInvestigation;
import getAllByExcel.crew.GetCrew;
import random.*;
import random.RandomNumber;
import utilClass.crew.Crew;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class DangerCheck {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void investigation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] content = {"贪污受贿","证书不全","在逃嫌疑人"};




        // 创建获得 ship excel 表数据的类
        GetCrew getPersonId = new GetCrew();
        GetInvestigation getInvestigation = new GetInvestigation();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Crew> personList = getPersonId.getAllByExcel();
        List<utilClass.Investigation> investigations = getInvestigation.getAllByExcel();


        //开始插入数据
        for (utilClass.Investigation investigation : investigations) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  z_dangerCheck(id,content,object,personList,time)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,investigation.id);
                pstm.setString(2,content[random.nextInt(content.length)]);
                pstm.setString(3,investigation.object);
                int j = random.nextInt(personList.size()-2);
                pstm.setString(4,personList.get(j).id+","+personList.get(j+1).id+","+personList.get(j+2).id);
                pstm.setObject(5,randomDate.generateRandomDate("2018-01-01","2019-01-01"));



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
