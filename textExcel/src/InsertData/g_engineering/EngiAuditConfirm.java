package InsertData.g_engineering;

import getAllByExcel.engineering.GetAudit;
import getAllByExcel.engineering.GetEngineering;
import random.RandomDate;
import random.RandomNumber;
import utilClass.engineering.Audit;
import utilClass.engineering.Engineering;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class EngiAuditConfirm {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void engiAuditConfirm() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};


        // 创建获得  excel 表数据的类
        GetAudit getAudit = new GetAudit();
        GetEngineering getEngineering = new GetEngineering();



        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Audit> auditList = getAudit.getAllByExcel();
        List<Engineering> engineeringList = getEngineering.getAllByExcel();





        //开始插入数据
        for (Audit audit : auditList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO g_engiAuditConfirm(auditId,engiName,engiType,engiId,auditPerson,auditDate)" +
                        "VALUES(?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                pstm.setString(1,audit.auditId);
                int j = random.nextInt(engineeringList.size());
                pstm.setString(2,engineeringList.get(j).engineeringName);
                pstm.setString(3,engineeringList.get(j).engineeringType);
                pstm.setString(4,engineeringList.get(j).engineeringId);
                pstm.setString(5,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setObject(6,randomDate.generateRandomDate("2019-06-06","2019-11-11"));



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
