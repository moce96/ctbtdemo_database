package InsertData.l_project;

import getAllByExcel.project.GetProject;
import getAllByExcel.project.GetProjectSpecific;
import random.RandomDate;
import random.RandomNumber;
import utilClass.project.Project;
import utilClass.project.ProjectSpecific;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ProjectExpenditure {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void projectExpenditure() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] year = {"2019","2020"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};
        String[] economicSubjects = {"渔业","建筑业","农业"};




        // 创建获得 ship excel 表数据的类
        GetProject getProject = new GetProject();
        GetProjectSpecific getProjectSpecific = new GetProjectSpecific();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Project> projectList = getProject.getAllByExcel();
        List<ProjectSpecific> projectSpecificList = getProjectSpecific.getAllByExcel();

        //开始插入数据
        for (int i=0; i<50; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  l_projectExpenditure(projectId,year,economicSubjects,totalMoney,budgetAllocation,ownFunds,remark)" +
                        "VALUES(?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(projectList.size());
                pstm.setString(1,projectList.get(j).projectId);
                pstm.setString(2,year[random.nextInt(year.length)]);
                pstm.setString(3,economicSubjects[random.nextInt(economicSubjects.length)]);
                int a = random.nextInt(5);
                int b = random.nextInt(5);
                pstm.setString(4,String.valueOf(a+b));
                pstm.setString(5,String.valueOf(a));
                pstm.setString(6,String.valueOf(b));
                pstm.setString(7,"暂无");

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
