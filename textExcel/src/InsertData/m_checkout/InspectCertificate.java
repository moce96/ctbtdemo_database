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

public class InspectCertificate {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void inspectCertificate() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] inspectCertificateType1 = {"船舶耐久度检验证书","船舶消防检验证书","船舶抗风能力检验证书"};
        String[] inspectCertificateType2 = {"渔用产品耐久度检验证书","渔用产品安全检验证书"};




        // 创建获得 ship excel 表数据的类
        GetInspectItem getInspectItem = new GetInspectItem();
        GetShip getShip = new GetShip();
        GetInspectApply getInspectApply = new GetInspectApply();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<InspectItem> inspectItems = getInspectItem.getAllByExcel();
        List<Ship> shipList = getShip.getAllByExcel();
        List<utilClass.checkout.InspectApply> inspectApplyList = getInspectApply.getAllByExcel();

        //开始插入数据
        for (utilClass.checkout.InspectApply inspectApply : inspectApplyList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO m_inspectCertificate(inspectCertificateId,inspectiCertificateType,shipId,startDate,expireDate)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,inspectApply.inspectCertificateId);
                if (inspectApply.inspectType.equals("船舶")) {
                    pstm.setString(2,inspectCertificateType1[random.nextInt(inspectCertificateType1.length)]);

                } else if (inspectApply.inspectType.equals("渔用产品")) {
                    pstm.setString(2,inspectCertificateType2[random.nextInt(inspectCertificateType2.length)]);

                }
                pstm.setString(3,inspectApply.shipId);
                pstm.setObject(4,randomDate.generateRandomDate("2019-01-01","2019-05-01"));
                pstm.setObject(5,randomDate.generateRandomDate("2022-01-01","2022-06-01"));

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
