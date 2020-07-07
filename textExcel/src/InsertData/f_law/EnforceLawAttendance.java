package InsertData.f_law;

import getAllByExcel.GetLawEnforcement;
import getAllByExcel.law.GetLawMan;
import getAllByExcel.law.GetLawShip;
import random.RandomDate;
import random.RandomJson;
import random.RandomNumber;
import utilClass.LawEnforcement;
import utilClass.law.LawMan;
import utilClass.law.LawShip;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class EnforceLawAttendance {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";





    public static void   enforceLawAttendance() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] gender = {"男","女"};



        // 创建获得 ship excel 表数据的类
        GetLawMan getLawMan = new GetLawMan();
        GetLawShip getLawShip = new GetLawShip();
        GetLawEnforcement getLawEnforcement = new GetLawEnforcement();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<LawMan> lawManList = getLawMan.getAllByExcel();
        List<LawShip> lawShips = getLawShip.getAllByExcel();
        List<LawEnforcement> lawEnforcements = getLawEnforcement.getAllByExcel();

        //开始插入数据
        for (LawEnforcement lawEnforcement : lawEnforcements) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO      f_enforceLawAttendance(enforceLawId,shipId,personId,startTime,endTime)" +
                        "VALUES(?,?,?,?,?)";

                pstm = conn.prepareStatement(sql);

                pstm.setString(1,lawEnforcement.id);
                int j = random.nextInt(2)+1;
                pstm.setString(2,randomJson.generateLawShip(j,lawShips));
                j = random.nextInt(7)+1;
                pstm.setString(3,randomJson.generateLawman(j,lawManList));
                pstm.setObject(4,randomDate.generateRandomDate("2018-01-01","2018-06-12"));
                pstm.setObject(5,randomDate.generateRandomDate("2018-07-01","2018-08-12"));


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
