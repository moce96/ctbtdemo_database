import getAllByExcel.checkout.GetInspectApply;
import getAllByExcel.company.GetCompany;
import getAllByExcel.engineering.GetAudit;
import random.MysqlRead;
import random.*;
import random.RandomNumber;
import utilClass.checkout.InspectApply;
import utilClass.company.Company;
import utilClass.engineering.Audit;

import java.sql.*;
import java.util.List;
import java.util.Random;

/**
 * 生成身份证和人名
 */

public class aa {


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

        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};
        String[] booldType = {"A","AB","O","B"};

        // 创建获得  excel 表数据的类
        GetAudit getAudit = new GetAudit();
        GetCompany getCompany = new GetCompany();
        GetInspectApply getInspectApply = new GetInspectApply();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Audit> auditList = getAudit.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();
        List<InspectApply> inspectApplies = getInspectApply.getAllByExcel();




        //开始插入数据
        for (InspectApply inspectApply : inspectApplies) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO a_aaaa(id,type) SELECT shipId,shipTypeId FROM s_shipFoundation WHERE shipId = ?";


                pstm = conn.prepareStatement(sql);

                pstm.setString(1,inspectApply.shipId);



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
