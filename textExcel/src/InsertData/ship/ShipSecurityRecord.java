package InsertData.ship;

import getAllByExcel.GetPersonId;
import getAllByExcel.GetShipExcel;
import getAllByExcel.GetShipIncident;
import random.*;
import random.RandomNumber;
import utilClass.Person;
import utilClass.Ship;
import utilClass.ShipIncident;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipSecurityRecord {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void shipSecurityRecord() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] securityCheckContent = {"引擎","消防设备","救援设备","食物储藏室"};
        String[] description = {"合格","不合格"};







        // 创建获得 excel 表数据的类
        GetShipExcel getShipExcel = new GetShipExcel();
        GetPersonId getPersonId = new GetPersonId();
        GetShipIncident getShipIncident = new GetShipIncident();

        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<Person> personList = getPersonId.getAllByExcel();
        List<ShipIncident> shipIncidents = getShipIncident.getAllByExcel();

        //开始插入数据
        for (int i=0;i<=30;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO z_shipSecurityRecord(securityCheckId,shipId,securityCheckTime,securityCheckContent,description)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                pstm.setString(1,randomNumber.generate(1,4));
                int j = random.nextInt(ships.size());
                pstm.setString(2,ships.get(j).shipId);
                pstm.setObject(3,randomDate.generateRandomDate("2018-01-01","2019-01-01"));
                pstm.setString(4,securityCheckContent[random.nextInt(securityCheckContent.length)]);
                pstm.setString(5,description[random.nextInt(description.length)]);


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
