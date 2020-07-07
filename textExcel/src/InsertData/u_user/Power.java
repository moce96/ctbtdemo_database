package InsertData.u_user;

import getAllByExcel.user.GetPower;
import getAllByExcel.user.GetRole;
import random.RandomDate;
import random.RandomNumber;
import utilClass.user.Role;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class Power {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void power() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();




        // 创建获得 ship excel 表数据的类
        GetRole getRole = new GetRole();
        GetPower getPower = new GetPower();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Role> roles = getRole.getAllByExcel();
        List<utilClass.user.Power> powerList = getPower.getAllByExcel();




        //开始插入数据
        for (utilClass.user.Power power : powerList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  u_power(powerId,powerName,function,powerURl)" +
                        "VALUES(?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,power.powerId);
                pstm.setString(2,power.powerName);
                pstm.setString(3,"aa");
                pstm.setString(4,"bb");

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
