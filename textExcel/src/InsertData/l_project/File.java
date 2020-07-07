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

public class File {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void file() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] content = {"贪污受贿","证书不全","在逃嫌疑人"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};





        // 创建获得 ship excel 表数据的类
        GetProject getProject = new GetProject();
        GetProjectSpecific getProjectSpecific = new GetProjectSpecific();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Project> projectList = getProject.getAllByExcel();
        List<ProjectSpecific> projectSpecificList = getProjectSpecific.getAllByExcel();

        //开始插入数据
        for (int i=0; i<20; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  l_file(fileId,name,content,encloseList,creatTime,publishTime,modifyTime,administrators,whetherIssue)" +
                        "VALUES(?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                String a = randomNumber.generate(1,3);
                pstm.setString(1,randomNumber.generate(1,5));
                pstm.setString(2,"a");
                pstm.setString(3,"b");
                pstm.setString(4,"c");
                pstm.setObject(5,randomDate.generateRandomDate("2019-01-01","2019-03-01"));
                pstm.setObject(6,randomDate.generateRandomDate("2019-03-01","2019-05-01"));
                pstm.setObject(7,randomDate.generateRandomDate("2019-01-01","2019-05-01"));
                pstm.setString(8,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(9,"是");

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
