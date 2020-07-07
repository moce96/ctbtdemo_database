package InsertData;

import getAllByExcel.GetRegion;
import getAllByExcel.company.GetCompany;
import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomNumber;
import utilClass.Region;
import utilClass.company.Company;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ReleaseSituation {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void releaseSituation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] listType = {"黑","白"};




        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();
        GetRegion getRegion = new GetRegion();
        GetCompany getCompany = new GetCompany();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> shipList = getShip.getAllByExcel();
        List<Region> regionList = getRegion.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();



        //开始插入数据
        for (int i=0; i<200; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO z_releaseSituation(releaseId,releaseCompanyId,releaseDate,releasePlace,releaseLoc,releaseObject,releaseNumber,releaseObjectSource)" +
                        "VALUES(?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(companyList.size());
                pstm.setString(1,randomNumber.generate(1,5));
                pstm.setString(2,companyList.get(j).id);


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
