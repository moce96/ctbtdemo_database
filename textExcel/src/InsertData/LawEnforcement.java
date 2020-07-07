package InsertData;

import getAllByExcel.GetInvestigation;
import getAllByExcel.GetLawEnforcement;
import getAllByExcel.crew.GetCrew;
import random.RandomDate;
import random.RandomNumber;
import utilClass.crew.Crew;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class LawEnforcement {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void lawEnforcement() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] content = {"贪污受贿","证书不全","在逃嫌疑人"};




        // 创建获得 ship excel 表数据的类
        GetCrew getPersonId = new GetCrew();
        GetInvestigation getInvestigation = new GetInvestigation();
        GetLawEnforcement getLawEnforcement = new GetLawEnforcement();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Crew> personList = getPersonId.getAllByExcel();
        List<utilClass.Investigation> investigations = getInvestigation.getAllByExcel();
        List<utilClass.LawEnforcement> lawEnforcements = getLawEnforcement.getAllByExcel();


        //开始插入数据
        for (utilClass.LawEnforcement lawEnforcement : lawEnforcements) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO z_lawEnforcement(id,content,object,personList,time)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,lawEnforcement.id);
                pstm.setString(2,content[random.nextInt(content.length)]);
                pstm.setString(3,lawEnforcement.object);
                int j = random.nextInt(personList.size()-2);
                pstm.setString(4,"{"+"1:"+personList.get(j).id+","+"2:"+personList.get(j+1).id+","+"3:"+personList.get(j+2).id+"}");
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
