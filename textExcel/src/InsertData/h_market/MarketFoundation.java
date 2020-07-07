package InsertData.h_market;

import getAllByExcel.market.GetMarket;
import random.RandomDate;
import random.RandomNumber;
import utilClass.market.Market;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class MarketFoundation {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void marketFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};
        String[] marketSize = {"小型","中型","大型"};


        // 创建获得 ship excel 表数据的类
        GetMarket getMarket = new GetMarket();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Market> marketList = getMarket.getAllByExcel();



        //开始插入数据
        for (Market market : marketList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  h_marketFoundation(marketId,principal,regionId,marketSize,openingHours,Date)" +
                        "VALUES(?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,market.marketId);
                pstm.setString(2,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(3,market.regionId);
                pstm.setString(4,marketSize[random.nextInt(marketSize.length)]);
                pstm.setString(5,randomNumber.generate(1,1)+"am到"+randomNumber.generate(1,1)+"pm");
                pstm.setObject(6,randomDate.generateRandomDate("2018-01-01","2019-01-01"));

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
