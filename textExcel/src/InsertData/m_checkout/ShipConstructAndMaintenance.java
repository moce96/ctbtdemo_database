package InsertData.m_checkout;

import getAllByExcel.checkout.GetEnterprise;
import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomJson;
import random.RandomNumber;
import utilClass.checkout.Enterprise;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipConstructAndMaintenance {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void shipConstructAndMaintenance() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] inspectType = {"船舶","渔用产品"};
        String[] currentStatus = {"申请","检验","签发","结束"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};





        // 创建获得 ship excel 表数据的类
        GetEnterprise getEnterprise = new GetEnterprise();
        GetShip getShip = new GetShip();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Enterprise> enterpriseList = getEnterprise.getAllByExcel();
        List<Ship> shipList = getShip.getAllByExcel();

        //开始插入数据
        for (int i=0; i<60; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO m_shipConstructAndMaintenance(companyId,workType,shipId,startDate,endDate,contactPerson)" +
                        "VALUES(?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(enterpriseList.size());
                while ((!enterpriseList.get(j).enterpriseType.equals("造船")) && (!enterpriseList.get(j).enterpriseType.equals("修船")))  {
                    j = random.nextInt();
                }
                pstm.setString(1,enterpriseList.get(j).enterpriseId);
                if (enterpriseList.get(j).enterpriseType.equals("造船")) {
                    pstm.setString(2,"造船");
                } else if (enterpriseList.get(j).enterpriseType.equals("修船")) {
                    pstm.setString(2,"修船");
                }
                j = random.nextInt(shipList.size());
                pstm.setString(3,shipList.get(j).shipId);
                pstm.setObject(4,randomDate.generateRandomDate("2018-01-01","2018-06-01"));
                pstm.setObject(5,randomDate.generateRandomDate("2018-06-02","2018-09-01"));
                pstm.setString(6,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);

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
