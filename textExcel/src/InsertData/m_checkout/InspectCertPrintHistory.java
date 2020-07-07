package InsertData.m_checkout;

import getAllByExcel.checkout.GetInspectApply;
import getAllByExcel.checkout.GetInspectItem;
import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomJson;
import random.RandomNumber;
import utilClass.checkout.InspectItem;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class InspectCertPrintHistory {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void inspectCertPrintHistory() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};





        // 创建获得 ship excel 表数据的类
        GetInspectItem getInspectItem = new GetInspectItem();
        GetShip getShip = new GetShip();
        GetInspectApply getInspectApply = new GetInspectApply();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<InspectItem> inspectItems = getInspectItem.getAllByExcel();
        List<Ship> shipList = getShip.getAllByExcel();
        List<utilClass.checkout.InspectApply> inspectApplyList = getInspectApply.getAllByExcel();

        //开始插入数据
        for (int i=0; i<110; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO m_inspectCertPrintHistory(inspectCertificateId,printDate,printPerson)" +
                        "VALUES(?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(inspectApplyList.size());
                pstm.setString(1,inspectApplyList.get(j).inspectCertificateId);
                pstm.setObject(2,randomDate.generateRandomDate("2019-06-01","2019-12-01"));
                pstm.setString(3,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);

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
