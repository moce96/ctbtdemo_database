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

public class ProjectEnclose {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void projectEnclose() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] content = {"贪污受贿","证书不全","在逃嫌疑人"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};
        String[] encloseExplain = {"暂无"};




        // 创建获得 ship excel 表数据的类
        GetProject getProject = new GetProject();
        GetProjectSpecific getProjectSpecific = new GetProjectSpecific();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Project> projectList = getProject.getAllByExcel();
        List<ProjectSpecific> projectSpecificList = getProjectSpecific.getAllByExcel();

        //开始插入数据
        for (int i=0; i<40; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  l_projectEnclose(projectId,serialNumber,encloseName,encloseExplain,date)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(projectList.size());
                pstm.setString(1,projectList.get(j).projectId);
                String a = randomNumber.generate(1,3);
                pstm.setString(2,a);
                pstm.setString(3,a+"号文件");
                pstm.setString(4,encloseExplain[random.nextInt(encloseExplain.length)]);
                pstm.setObject(5,randomDate.generateRandomDate("2019-01-01","2020-06-01"));

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
