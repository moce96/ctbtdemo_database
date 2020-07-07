package InsertData.l_project;

import getAllByExcel.project.GetExpert;
import getAllByExcel.project.GetProject;
import random.RandomDate;
import random.RandomNumber;
import utilClass.project.Expert;
import utilClass.project.Project;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ExpertFoundation {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void expertFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] content = {"贪污受贿","证书不全","在逃嫌疑人"};




        // 创建获得 ship excel 表数据的类
        GetProject getProject = new GetProject();
        GetExpert getExpert = new GetExpert();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Project> projectList = getProject.getAllByExcel();
        List<Expert> expertList = getExpert.getAllByExcel();

        //开始插入数据
        for (Expert expert : expertList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  l_expertFoundation(expertId,expertName)" +
                        "VALUES(?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,expert.personId);
                pstm.setString(2,expert.personName);


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
