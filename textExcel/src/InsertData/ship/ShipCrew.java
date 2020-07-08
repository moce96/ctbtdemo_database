package InsertData.ship;

import getAllByExcel.GetPersonId;
import getAllByExcel.GetShipExcel;
import random.MysqlRead;
import random.*;
import random.RandomNumber;
import random.StringToDate;
import utilClass.Person;
import utilClass.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipCrew {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void shipCrew() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] position = {"副船长","舵手","水手","清洁工"};



        // 创建获得 ship excel 表数据的类
        GetShipExcel getShipExcel = new GetShipExcel();
        GetPersonId getPersonId = new GetPersonId();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<Person> personList = getPersonId.getAllByExcel();

        //开始插入数据
        for (Person person : personList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO z_shipCrew(shipId,personId,position,photo,inputLoc,boardingTime,departureTime)" +
                        "VALUES(?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,person.shipId);
                pstm.setString(2,person.id);
                pstm.setString(3,position[random.nextInt(position.length)]);
                pstm.setString(4,"暂无");
                pstm.setString(5,"暂无");
                pstm.setObject(6,person.startTime);
                pstm.setObject(7,person.endTime);



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
