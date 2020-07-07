package InsertData.l_project;

import getAllByExcel.GetInvestigation;
import getAllByExcel.crew.GetCrew;
import getAllByExcel.project.GetProject;
import getAllByExcel.project.GetProjectCampany;
import getAllByExcel.project.GetProjectSpecific;
import random.RandomDate;
import random.RandomNumber;
import utilClass.crew.Crew;
import utilClass.project.Project;
import utilClass.project.ProjectCompany;
import utilClass.project.ProjectSpecific;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ProjectBank {


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
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};





        // 创建获得 ship excel 表数据的类
        GetProject getProject = new GetProject();
        GetProjectSpecific getProjectSpecific = new GetProjectSpecific();
        GetProjectCampany getProjectCampany = new GetProjectCampany();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Project> projectList = getProject.getAllByExcel();
        List<ProjectSpecific> projectSpecificList = getProjectSpecific.getAllByExcel();
        List<ProjectCompany> projectCompanyList = getProjectCampany.getAllByExcel();

        //开始插入数据
        for (Project project : projectList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  l_projectBank(projectId,projectName,reportTime,projectSpecificId,reserveTime,executionTime,chargePerson,contact,buildingarea,strucompanyID,enviroInfluReport,confirmFile)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                String a = randomNumber.generate(1,3);
                pstm.setString(1,project.projectId);
                pstm.setString(2,project.projectName);
                pstm.setObject(3,project.reportTime);
                pstm.setString(4,project.projectSpecificId);
                pstm.setObject(5,randomDate.generateRandomDate("2019-01-02","2020-01-01"));
                pstm.setObject(6,randomDate.generateRandomDate("2020-01-02","2021-01-01"));
                pstm.setString(7,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(8,"1"+randomNumber.generate(1,10));
                pstm.setString(9,"a");
                int j = random.nextInt(projectCompanyList.size());
                pstm.setString(10,projectCompanyList.get(j).companyId);
                pstm.setString(11,"a");
                pstm.setString(12,"a");


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
