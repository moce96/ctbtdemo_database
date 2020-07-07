package InsertData.k_oceanpasture;

import getAllByExcel.k_oceanpasture.GetOceanPasture;
import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomJson;
import random.RandomNumber;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class OceanPasture {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void  oceanPasture(){

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] regionId = {"10015"};
        String[] first = {"京","外","莲","锦","衡","新","中"};
        String[] second = {"庄","环","花","江","山","闸","山"};
        String[] pastureType = {"再建区","示范区"};
        String[] description = {"国家海洋牧场","浙江省海洋牧场"};



        // 创建获得 ship excel 表数据的类
        GetOceanPasture getOceanPasture = new GetOceanPasture();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<utilClass.k_oceanpasture.OceanPasture> oceanPastureList = getOceanPasture.getAllByExcel();



        //开始插入数据
        for (utilClass.k_oceanpasture.OceanPasture oceanPasture : oceanPastureList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  k_oceanPasture(pastureName,pastureId,regionId,pastureType,description,buildTime,finishTime)" +
                        "VALUES(?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,oceanPasture.oceanPastureName);
                pstm.setString(2,oceanPasture.oceanPastureId);
                pstm.setString(3,regionId[random.nextInt(regionId.length)]);
                pstm.setString(4,pastureType[random.nextInt(pastureType.length)]);
                pstm.setString(5,description[random.nextInt(description.length)]);
                pstm.setObject(6,randomDate.generateRandomDate("2015-01-01","2015-06-06"));
                pstm.setObject(7,randomDate.generateRandomDate("2016-01-01","2016-06-06"));


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
