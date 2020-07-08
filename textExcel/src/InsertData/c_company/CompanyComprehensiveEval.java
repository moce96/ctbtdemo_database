package InsertData.c_company;

import getAllByExcel.company.GetCompany;
import getAllByExcel.GetInvestigation;
import random.MysqlRead;
import random.RandomDate;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.Investigation;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CompanyComprehensiveEval {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void companyComprehensiveEval() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] evalLevel = {"甲等","乙等","丙等","丁等"};
        String[] evalContent = {"设备安全评估","电气安全评估","消防安全评估","化学品安全评估","厂房建筑安全评估"};
        String[] enclosure = {"http://pic.5tu.cn/tuku/201611/zhanbanmoban-1590308.html","https://www.16pic.com/zhanban/pic_5241287.html","http://pic.5tu.cn/tuku/201806/zhanbanmoban-1641479.html","http://pic.5tu.cn/tuku/201806/zhanbanmoban-1641479.html","https://www.16pic.com/zhanban/pic_5241272.html"};
        String[] description = {"设备维护情况良好","设备维护情况较差","电气维护情况良好","电气维护情况较差","消防安全维护情况良好","消防安全维护情况","化学用品维护情况良好","化学用品维护情况较差","厂房建筑维护情况良好","厂房建筑维护情况较差"};
        Map<String, Integer> evalPointMap = new HashMap<String, Integer>();
        evalPointMap.put("甲等", 90);
        evalPointMap.put("乙等", 80);
        evalPointMap.put("丙等", 70);
        evalPointMap.put("丁等", 60);
        Map<String, String> evalDesMap1 = new HashMap<String, String>();
        evalDesMap1.put("设备安全评估","设备");
        evalDesMap1.put("电气安全评估","电气");
        evalDesMap1.put("消防安全评估","消防安全");
        evalDesMap1.put("化学品安全评估","化学用品");
        evalDesMap1.put("厂房建筑安全评估","厂房建筑");
        Map<String, String> evalDesMap2 = new HashMap<String, String>();
        evalDesMap2.put("甲等", "良好");
        evalDesMap2.put("乙等", "良好");
        evalDesMap2.put("丙等", "较差");
        evalDesMap2.put("丁等", "较差");



        // 创建获得 excel 表数据的类
        GetInvestigation getInvestigation = new GetInvestigation();
        GetCompany getCompany = new GetCompany();

        // 用从表里获得的数据  生成列表  用于填充数据库

        List<utilClass.Investigation> investigations = getInvestigation.getAllByExcel();
        List<Company> companies = getCompany.getAllByExcel();

        //开始插入数据
        for (int i = 0; i < 20;i++) {

            Investigation investigation = investigations.get(random.nextInt(investigations.size()));
            if (investigation.object.equals("company")) {

                try {
                    Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                    String sql = "INSERT INTO     c_companyComprehensiveEval(companyId,dangerCheckId,evalDate,evalLevel,evalContent,evalPoint,enclosure,description)" +
                            "VALUES(?,?,?,?,?,?,?,?)";

                    pstm = conn.prepareStatement(sql);

                    int j = random.nextInt(companies.size());
                    pstm.setString(1,companies.get(j).id);
                    pstm.setString(2,investigation.id);
                    pstm.setObject(3,randomDate.generateRandomDate("2018-01-01","2019-01-01"));
                    String chosenEvalLevel = evalLevel[random.nextInt(evalLevel.length)];
                    pstm.setString(4,chosenEvalLevel);
                    String chosenEvalContent = evalContent[random.nextInt(evalContent.length)];
                    pstm.setString(5,chosenEvalContent);
                    pstm.setInt(6,random.nextInt(10)+evalPointMap.get(chosenEvalLevel).intValue());
                    pstm.setString(7,enclosure[random.nextInt(enclosure.length)]);
                    pstm.setString(8,evalDesMap1.get(chosenEvalContent)+"维护情况"+evalDesMap2.get(chosenEvalLevel));


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
}
