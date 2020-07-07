package InsertData.l_project;

import getAllByExcel.project.GetDeclarePerson;
import getAllByExcel.project.GetProject;
import getAllByExcel.project.GetProjectSpecific;
import random.RandomDate;
import random.RandomNumber;
import utilClass.project.DeclarePerson;
import utilClass.project.Project;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ProjectSpecific {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void  projectSpecific() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] gender = {"男","女"};




        // 创建获得 ship excel 表数据的类
        GetProject getProject = new GetProject();
        GetDeclarePerson getDeclarePerson = new GetDeclarePerson();
        GetProjectSpecific getProjectSpecific = new GetProjectSpecific();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Project> projectList = getProject.getAllByExcel();
        List<DeclarePerson> declarePersonList = getDeclarePerson.getAllByExcel();
        List<utilClass.project.ProjectSpecific> projectSpecificList = getProjectSpecific.getAllByExcel();

        //开始插入数据
        for (utilClass.project.ProjectSpecific projectSpecific : projectSpecificList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO   l_projectSpecific(classificationId,classificationYear)" +
                        "VALUES(?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,projectSpecific.projectSpecificId);
                pstm.setString(2,"201"+randomNumber.generate(1,1));


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
