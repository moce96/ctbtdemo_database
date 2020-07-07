package InsertData.i_oceanfishing;

import getAllByExcel.GetInvestigation;
import getAllByExcel.company.GetCompany;
import getAllByExcel.crew.GetCrew;
import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.crew.Crew;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class OceanSpecificDeclaration {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void oceanSpecificDeclaration() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();


        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};

        String[] declareName  = {"申请出航补助","申请出航许可","申请远洋捕鱼"};
        String[] declareContent  = {"由于出航距离较远，申请补助","由于要进行远航，申请出行许可","由于要进行远洋捕捞，申请许可"};
        String[] enclose  = {"https://www.mianfeiwendang.com/doc/187f35310ab3e32a816660d4"};
        String[] reviewSituation  = {"已审核","未审核"};


        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();
        GetCompany getCompany = new GetCompany();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> shipList = getShip.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();


        //开始插入数据
        for (int i=0;i<=50;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  i_oceanSpecificDeclaration(companyId,companyName,shipId,declareName,declareContent,enclose,reviewer,nextReviewer,reviewSituation,reviewDate)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(companyList.size());
                pstm.setString(1,companyList.get(j).id);
                pstm.setString(2,companyList.get(j).name);
                j = random.nextInt(shipList.size());
                while (!shipList.get(j).shipTypeId.equals("1")) {
                    j = random.nextInt(shipList.size());
                }
                pstm.setString(3,shipList.get(j).shipId);
                pstm.setString(4,declareName[random.nextInt(declareName.length)]);
                pstm.setString(5,declareContent[random.nextInt(declareContent.length)]);
                pstm.setString(6,enclose[random.nextInt(enclose.length)]);
                pstm.setString(7,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(8,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(9,reviewSituation[random.nextInt(reviewSituation.length)]);
                pstm.setObject(10,randomDate.generateRandomDate("2019-01-01","2019-12-12"));

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
