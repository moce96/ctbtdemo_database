package InsertData.i_oceanfishing;

import getAllByExcel.company.GetCompany;
import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class EmergencyDrills {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void emergencyDrills() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();


        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};
        String[] description = {"为了防止因故着火，进行防火演习","为了确保台风天安全，进行演习","为了确保大浪来袭时的安全，进行演习"};
        String[] content = {"防火演习","台风演习","大浪演习"};
        String[] video = {"https://news.tezhongzhuangbei.com/zbkx_date_1041.html"};
        String[] existQuestion = {"船员重视程度不足","演习比较生疏","船员行动太慢"};
        String[] solution = {"加强安全教育","批评教育队员","重新演习"};



        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();
        GetCompany getCompany = new GetCompany();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> shipList = getShip.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();


        //开始插入数据
        for (int i=0;i<=30;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  i_emergencyDrills(shipId,date,description,content,video,existQuestion,solution)" +
                        "VALUES(?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                int j = random.nextInt(shipList.size());
                while (!shipList.get(j).shipTypeId.equals("1")) {
                    j = random.nextInt(shipList.size());
                }
                pstm.setString(1,shipList.get(j).shipId);
                pstm.setObject(2,randomDate.generateRandomDate("2019-01-01","2019-12-12"));
                j = random.nextInt(companyList.size());
                pstm.setString(3,description[random.nextInt(description.length)]);
                pstm.setString(4,content[random.nextInt(content.length)]);
                pstm.setString(5,video[random.nextInt(video.length)]);
                pstm.setString(6,existQuestion[random.nextInt(existQuestion.length)]);
                pstm.setString(7,solution[random.nextInt(solution.length)]);

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
