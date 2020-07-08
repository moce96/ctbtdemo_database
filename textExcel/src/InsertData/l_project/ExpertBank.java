package InsertData.l_project;

import getAllByExcel.project.GetExpert;
import getAllByExcel.project.GetProject;
import random.*;
import random.RandomNumber;
import utilClass.project.Expert;
import utilClass.project.Project;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ExpertBank {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void expertBank() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] content = {"贪污受贿","证书不全","在逃嫌疑人"};
        String[] type = {"渔业","建筑业","农业"};
        String[] professionalDirection = {"水产养殖","土木建筑","农学"};
        String[] title = {"教授","副教授","讲师"};




        // 创建获得 ship excel 表数据的类
        GetProject getProject = new GetProject();
        GetExpert getExpert = new GetExpert();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Project> projectList = getProject.getAllByExcel();
        List<Expert> expertList = getExpert.getAllByExcel();

        //开始插入数据
        for (int i=0; i<90; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO l_expertBank(expertId,expertName,type,professionalDirection,title,age,contact,updataTime)" +
                        "VALUES(?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(expertList.size());
                pstm.setString(1,expertList.get(j).personId);
                pstm.setString(2,expertList.get(j).personName);
                pstm.setString(3,type[random.nextInt(type.length)]);
                pstm.setString(4,professionalDirection[random.nextInt(professionalDirection.length)]);
                pstm.setString(5,title[random.nextInt(title.length)]);
                pstm.setString(6,"5"+randomNumber.generate(1,1));
                pstm.setString(7,1+randomNumber.generate(1,10));
                pstm.setObject(8,randomDate.generateRandomDate("2018-01-01","2019-01-01"));


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
