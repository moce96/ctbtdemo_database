package InsertData.l_project;

import getAllByExcel.project.GetDeclarePerson;
import getAllByExcel.project.GetProject;
import random.*;
import random.RandomNumber;
import utilClass.project.DeclarePerson;
import utilClass.project.Project;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ProjectDeclarePerson {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void projectDeclarePerson() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] gender = {"男","女"};




        // 创建获得 ship excel 表数据的类
        GetProject getProject = new GetProject();
        GetDeclarePerson getDeclarePerson = new GetDeclarePerson();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Project> projectList = getProject.getAllByExcel();
        List<DeclarePerson> declarePersonList = getDeclarePerson.getAllByExcel();

        //开始插入数据
        for (DeclarePerson declarePerson : declarePersonList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  l_projectDeclarePerson(declarePersonId,name,gender,birthday,contact)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,declarePerson.personId);
                pstm.setString(2,declarePerson.personName);
                pstm.setString(3,gender[random.nextInt(gender.length)]);
                pstm.setObject(4,randomDate.generateRandomDate("1972-01-01","1997-01-01"));
                pstm.setString(5,"1"+randomNumber.generate(1,10));


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
