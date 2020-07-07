package InsertData.m_checkout;

import getAllByExcel.checkout.GetEnterprise;
import random.RandomDate;
import random.RandomJson;
import random.RandomNumber;
import utilClass.checkout.Enterprise;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipyardCredit {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void  shipyardCredit() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] inspectType = {"船舶","渔用产品"};
        String[] currentStatus = {"申请","检验","签发","结束"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};
        String[] pointCharge = {"+","-"};



        // 创建获得 ship excel 表数据的类
        GetEnterprise getEnterprise = new GetEnterprise();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Enterprise> enterpriseList = getEnterprise.getAllByExcel();

        //开始插入数据
        for (int i=0; i<30; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  m_shipyardCredit(shipyardId,eventId,eventTypeId,pointChange,disposalDescription,disposer,enclosure,disposalProcess)" +
                        "VALUES(?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(enterpriseList.size());
                pstm.setString(1,enterpriseList.get(j).enterpriseId);
                pstm.setString(2,randomNumber.generate(1,5));
                pstm.setString(3,"a");
                pstm.setString(4,pointCharge[random.nextInt(pointCharge.length)]+randomNumber.generate(1,1));
                pstm.setString(5,"a");
                pstm.setString(6,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(7,"a");
                pstm.setString(8,"a");


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
