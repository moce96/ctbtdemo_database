package InsertData.u_user;

import getAllByExcel.announce.GetDepartment;
import getAllByExcel.company.GetCompany;
import getAllByExcel.user.GetUser;
import random.*;
import random.RandomNumber;
import utilClass.Department;
import utilClass.company.Company;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class User {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void user() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();




        // 创建获得 ship excel 表数据的类
        GetUser getUser = new GetUser();
        GetCompany getCompany = new GetCompany();
        GetDepartment getDepartment = new GetDepartment();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<utilClass.user.User> users = getUser.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();
        List<Department> departmentList = getDepartment.getAllByExcel();



        //开始插入数据
        for (utilClass.user.User user1 : users) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  u_user(userName,userId,password,contact,departmentId)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,user1.userName);
                pstm.setString(2,user1.userId);
                pstm.setString(3,randomNumber.generate(2,3)+randomNumber.generate(1,5));
                pstm.setString(4,"1"+randomNumber.generate(1,10));
                int j = random.nextInt(departmentList.size());
                pstm.setString(5,departmentList.get(j).departmentId);


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
