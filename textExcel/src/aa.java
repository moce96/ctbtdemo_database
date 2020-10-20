import getAllByExcel.checkout.GetInspectApply;
import getAllByExcel.company.GetCompany;
import getAllByExcel.crew.GetCrew;
import getAllByExcel.engineering.GetAudit;
import getAllByExcel.ship.GetShipCredit;
import random.MysqlRead;
import random.*;
import random.RandomNumber;
import utilClass.checkout.InspectApply;
import utilClass.company.Company;
import utilClass.crew.Crew;
import utilClass.engineering.Audit;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    //把图片转为二进制存入数据库
    public static byte[] getImgStr(String path) throws IOException, IOException {
        FileInputStream fis = new FileInputStream(path);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        int len = 0;
        byte[] b = new byte[1024];
        while ((len = fis.read(b))!= -1){
            out.write(b,0,len);
        }

        //接收out
        byte[] array = out.toByteArray();
        fis.close();
        out.close();

        return array;
    }

    public static void AA() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};
        String[] age = {"50-60","50-60","50-60","50-60","50-60","50-60","50-60","50-60","50-60","50-60",
                               "40-50","40-50","40-50","40-50","40-50","40-50",
                                "30-40","30-40","30-40","30-40"};
        String[] workYear ={"30-40","30-40","30-40","30-40","30-40","30-40","30-40","30-40","30-40","30-40","30-40","30-40","30-40","30-40",
                            "1-10","1-10",
                            "10-20","10-20","10-20","10-20","10-20","10-20","10-20","10-20",
                            "20-30","20-30","20-30","20-30","20-30","20-30","20-30","20-30","20-30","20-30",
                            "40以上"};
        String[] territorial = {"普陀区","普陀区","普陀区","普陀区","普陀区","普陀区","普陀区","普陀区","普陀区","普陀区","普陀区",
                                "岱山县","岱山县","岱山县","岱山县","岱山县","岱山县","岱山县","岱山县","岱山县","岱山县",
                                "嵊泗县","嵊泗县","嵊泗县","嵊泗县","嵊泗县","嵊泗县","嵊泗县",
                                "定海区"};
        String[] creditStatus = {"正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常","正常",
                                "良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好"};


        String[] catchType = {"带鱼","大黄鱼","小黄鱼","三疣梭子蟹","青占鱼","海蜇","鲳鱼"};

                // 创建获得  excel 表数据的类
        GetAudit getAudit = new GetAudit();
        GetCompany getCompany = new GetCompany();
        GetInspectApply getInspectApply = new GetInspectApply();
        GetShipCredit getShipCredit = new GetShipCredit();
        GetCrew getPersonId = new GetCrew();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Audit> auditList = getAudit.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();
        List<InspectApply> inspectApplies = getInspectApply.getAllByExcel();
        List<utilClass.ship.ShipCredit> shipCreditList = getShipCredit.getAllByExcel();
        List<Crew> personList = getPersonId.getAllByExcel();




        //开始插入数据
        for (int i=3899; i<=3899; i++) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "UPDATE b_crewComprehensiveEval set enclosure=?";

                byte[] arr = getImgStr("src\\photo\\person.jpg");
                Blob blob = conn.createBlob();
                blob.setBytes(1,arr);

                pstm = conn.prepareStatement(sql);



                int j = random.nextInt(shipCreditList.size());
                pstm.setBlob(1,blob);





                pstm.executeUpdate();


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
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
