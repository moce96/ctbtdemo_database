package InsertData.f_law;

import getAllByExcel.law.GetLawMan;
import random.RandomDate;
import random.RandomNumber;
import utilClass.law.LawMan;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class EnforceLawCrew {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";





    public static void   enforceLawCrew() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] gender = {"男","女"};



        // 创建获得 ship excel 表数据的类
        GetLawMan getLawMan = new GetLawMan();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<LawMan> lawManList = getLawMan.getAllByExcel();

        //开始插入数据
        for (LawMan lawMan : lawManList ) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO     f_enforceLawCrew(id,personId,personName,gender,photo,wordId,locatorId,camId,contactPhone)" +
                        "VALUES(?,?,?,?,?,?,?,?,?)";

                pstm = conn.prepareStatement(sql);

                pstm.setString(1,lawMan.id);
                pstm.setString(2,lawMan.personId);
                pstm.setString(3,lawMan.name);
                pstm.setString(4,gender[random.nextInt(gender.length)]);
                pstm.setString(5,"暂无");
                pstm.setString(6,randomNumber.generate(1,5));
                pstm.setString(7,randomNumber.generate(1,5));
                pstm.setString(8,randomNumber.generate(1,5));
                pstm.setString(9,"1"+randomNumber.generate(1,10));

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
