package InsertData.l_project;

import getAllByExcel.project.GetDeclarePerson;
import getAllByExcel.project.GetProject;
import getAllByExcel.project.GetProjectCampany;
import getAllByExcel.project.GetProjectSpecific;
import random.RandomDate;
import random.RandomNumber;
import utilClass.project.DeclarePerson;
import utilClass.project.Project;
import utilClass.project.ProjectCompany;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ProjectStructCompany {
    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void  projectStructCompany() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] gender = {"男","女"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};





        // 创建获得 ship excel 表数据的类
        GetProjectCampany getProjectCampany = new GetProjectCampany();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<ProjectCompany> projectCompanyList = getProjectCampany.getAllByExcel();

        //开始插入数据
        for (ProjectCompany projectCompany : projectCompanyList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO   l_projectStructCompany(companyID,companyName,manager,managerPhone)" +
                        "VALUES(?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,projectCompany.companyId);
                pstm.setString(2,projectCompany.companyName);
                pstm.setString(3,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(4,"1"+randomNumber.generate(1,10));


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
