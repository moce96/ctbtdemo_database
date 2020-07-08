package InsertData.h_market;

import getAllByExcel.market.GetMarket;
import random.*;
import random.RandomNumber;
import utilClass.market.Market;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class MarketTransaction {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void marketTransaction() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] fishType = {"鲐鲹鱼","蟹类","带鱼","鳗鱼","龙头鱼","小黄鱼","头足类","梅鱼"};

        // 创建获得 ship excel 表数据的类
        GetMarket getMarket = new GetMarket();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Market> marketList = getMarket.getAllByExcel();



        //开始插入数据
        for (int i=0; i<=100; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  h_marketTransaction(marketId,transactionDate,fishType,tradeVolume,tradeMoney)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(marketList.size());
                pstm.setString(1,marketList.get(j).marketId);
                pstm.setObject(2,randomDate.generateRandomDate("2019-01-01","2020-01-01"));
                pstm.setString(3,fishType[random.nextInt(fishType.length)]);
                pstm.setString(4,randomNumber.generate(1,5));
                pstm.setString(5,randomNumber.generate(1,6));

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
