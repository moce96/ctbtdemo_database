package InsertData.i_oceanfishing;

import getAllByExcel.company.GetCompany;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class CompanyTalk {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static final String[] mysqlMessage = MysqlRead.message;


    public static void companyTalk() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();


        String[] shipSate = {"优秀","良好","较差"};
        String[] crewSate = {"优秀","良好","较差"};
        String[] fish = {"大黄鱼","小黄鱼","带鱼","乌贼"};



        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();
        GetCompany getCompany = new GetCompany();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> shipList = getShip.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();


        //开始插入数据
        for (int i=0;i<=20;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  i_companyTalk(shipId,date,companyId,shipSate,crewSate,fishType,fishWeigth)" +
                        "VALUES(?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                int j = random.nextInt(shipList.size());
                while (!shipList.get(j).shipTypeId.equals("1")) {
                    j = random.nextInt(shipList.size());
                }
                pstm.setString(1,shipList.get(j).shipId);
                pstm.setObject(2,randomDate.generateRandomDate("2019-01-01","2020-07-01"));
                j = random.nextInt(companyList.size());
                pstm.setString(3,companyList.get(j).id);
                pstm.setString(4,shipSate[random.nextInt(shipSate.length)]);
                pstm.setString(5,crewSate[random.nextInt(crewSate.length)]);
                pstm.setString(6,fish[random.nextInt(fish.length)]);
                pstm.setString(7,randomNumber.generate(1,4));

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
