package InsertData.i_oceanfishing;

import getAllByExcel.company.GetCompany;
import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class PolicyPublish {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void policyPublish() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();


        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};

        String[] policyName  = {"开海","开海","禁渔期","禁渔期","禁渔期","禁渔期","禁渔期"};
        String[] policyContent  = {"即日起，禁止捕捞2个月","即日起，禁止捕捞3个月","即日起，禁止捕捞4个月","即日起，禁止捕捞5个月","即日起，禁止捕捞1个月","即日起，禁止捕捞6个月"};
        String[] enclose  = {"http://mts.jk51.com/tushuo/5271778.html"};

        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();
        GetCompany getCompany = new GetCompany();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> shipList = getShip.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();


        //开始插入数据
        for (int i=0;i<=30;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  i_policyPublish(policyId,policyName,policyContent,enclose,publishTime,whetherPublish)" +
                        "VALUES(?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                pstm.setString(1,randomNumber.generate(1,4));

                int j = random.nextInt(policyName.length);
                if (policyName[j].equals("禁渔期")) {
                    pstm.setString(2,policyName[j]);
                    pstm.setString(3,policyContent[random.nextInt(policyContent.length)]);
                } else  if (policyName[j].equals("开海")) {
                    pstm.setString(2,policyName[j]);
                    pstm.setString(3,"即日起允许捕捞");
                }

                pstm.setString(4,enclose[random.nextInt(enclose.length)]);
                pstm.setObject(5,randomDate.generateRandomDate("2016-01-01","2019-12-12"));
                pstm.setString(6,"是");

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
