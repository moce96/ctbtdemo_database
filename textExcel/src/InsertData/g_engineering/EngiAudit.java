package InsertData.g_engineering;

import getAllByExcel.company.GetCompany;
import getAllByExcel.engineering.GetAudit;
import random.RandomDate;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.engineering.Audit;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class EngiAudit {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void engiAudit() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] auditContent = {"允许开采","允许倾废","允许工程建设"};
        String[] auditType = {"开采","倾废","工程建设"};
        String[] aduitWorkScope = {"122.31,30.23;122.33,30.22;122.32,30.21","122.12;30.14;122.23,30.14;122.24,30.17","122.41,30.26;122.42,30.25;122.41,30.25"};
        String[] enclosure = {"https://www.16pic.com/vector/pic_6127268.html"};

        // 创建获得  excel 表数据的类
        GetAudit getAudit = new GetAudit();
        GetCompany getCompany = new GetCompany();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Audit> auditList = getAudit.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();




        //开始插入数据
        for (Audit audit : auditList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO g_engiAudit(auditId,auditContent,auditDate,auditType,aduitWorkScope,expireDate,auditCompanyId,enclosure)" +
                        "VALUES(?,?,?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                pstm.setString(1,audit.auditId);
                pstm.setString(2,auditContent[random.nextInt(auditContent.length)]);
                pstm.setObject(3,randomDate.generateRandomDate("2019-01-01","2019-06-06"));
                pstm.setString(4,auditType[random.nextInt(auditType.length)]);
                pstm.setString(5,aduitWorkScope[random.nextInt(aduitWorkScope.length)]);
                pstm.setObject(6,randomDate.generateRandomDate("2022-01-01","2022-06-01"));
                int j = random.nextInt(companyList.size());
                pstm.setString(7,companyList.get(j).id);
                pstm.setString(8,enclosure[random.nextInt(enclosure.length)]);



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
