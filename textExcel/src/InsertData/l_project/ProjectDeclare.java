package InsertData.l_project;

import getAllByExcel.project.GetDeclarePerson;
import getAllByExcel.project.GetProject;
import random.RandomDate;
import random.RandomNumber;
import utilClass.project.DeclarePerson;
import utilClass.project.Project;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ProjectDeclare {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void projectBank() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] content = {"贪污受贿","证书不全","在逃嫌疑人"};
        String[] budgetTypeId = {"1001","1002","1003","1004","1005"};
        String[] workTypeId = {"101","102","103","104"};




        // 创建获得 ship excel 表数据的类
        GetProject getProject = new GetProject();
        GetDeclarePerson getDeclarePerson = new GetDeclarePerson();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Project> projectList = getProject.getAllByExcel();
        List<DeclarePerson> declarePersonList = getDeclarePerson.getAllByExcel();

        //开始插入数据
        for (Project project : projectList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  l_projectDeclare(projectId,projectName,projectSpecificId,declarePersonId,startTime,endTime,projectState,declareMoney,approveMoney,approveTime,projectBudgetTypeId,projectWorkTypeId,declareTime)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,project.projectId);
                pstm.setString(2,project.projectName);
                pstm.setString(3,project.projectSpecificId);
                int j = random.nextInt(declarePersonList.size());
                pstm.setString(4,declarePersonList.get(j).personId);
                pstm.setObject(5,randomDate.generateRandomDate("2020-01-02","2021-01-01"));
                pstm.setObject(6,randomDate.generateRandomDate("2021-01-02","2022-01-01"));
                pstm.setString(7,"申报中");
                pstm.setString(8,"1"+randomNumber.generate(1,6));
                pstm.setString(9,"1"+randomNumber.generate(1,6));
                pstm.setObject(10,randomDate.generateRandomDate("2020-01-02","2021-01-01"));
                pstm.setString(11,budgetTypeId[random.nextInt(budgetTypeId.length)]);
                pstm.setString(12,workTypeId[random.nextInt(workTypeId.length)]);
                pstm.setObject(13,project.reportTime);



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
