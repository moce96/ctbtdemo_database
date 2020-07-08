package InsertData.i_oceanfishing;

import getAllByExcel.GetInvestigation;
import getAllByExcel.company.GetCompany;
import getAllByExcel.crew.GetCrew;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.crew.Crew;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class OceanShipFoundation {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void oceanShipFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] regionId = {"10007"};
        String[] resourceType = {"燃料","鱼类"};
        String[] fuel = {"可燃冰","煤炭","石油"};
        String[] fish = {"黄鱼","乌贼","海蜇","带鱼"};

        String[] destination  = {"浙江","山东","江苏","福建","广东"};



        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();
        GetCompany getCompany = new GetCompany();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> shipList = getShip.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();


        //开始插入数据
        for (int i=0;i<=70;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  i_oceanShipFoundation(shipId,sailingTime,returnTime,supplyResource,destination,updateDate,companyId)" +
                        "VALUES(?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                //找到船舶基础表里的远洋渔船
                int j = random.nextInt(shipList.size());
                while (!shipList.get(j).shipTypeId.equals("1")) {
                    j = random.nextInt(shipList.size());
                }
                pstm.setString(1,shipList.get(j).shipId);
                pstm.setObject(2,randomDate.generateRandomDate("2018-01-01","2018-06-06"));
                pstm.setObject(3,randomDate.generateRandomDate("2018-07-07","2019-02-02"));
                pstm.setString(4,randomNumber.generate(1,2));
                pstm.setString(5,destination[random.nextInt(destination.length)]);
                pstm.setObject(6,randomDate.generateRandomDate("2019-01-01","2019-06-06"));
                pstm.setString(7,"a");


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
