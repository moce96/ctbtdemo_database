package InsertData.s_ship;

import getAllByExcel.crew.GetCrew;
import getAllByExcel.ship.GetShip;
import getAllByExcel.ship.GetShipIncident;
import random.*;
import random.RandomNumber;
import utilClass.crew.Crew;
import utilClass.ship.Ship;
import utilClass.ship.ShipIncident;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipRenovation {




    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void shipRenovation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();


        String[] renovationContent  = {"加装制冷系统","主机升级","船体加固","船体材质升级"};
        String[] renovationCompany = {"舟山沥港船舶修理公司","舟山永邦船舶修理公司","浙江东邦修造船有限公司","舟山金平船舶修造有限公司"};





        // 创建获得 excel 表数据的类
        GetShip getShipExcel = new GetShip();
        GetCrew getPersonId = new GetCrew();
        GetShipIncident getShipIncident = new GetShipIncident();

        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<Crew> personList = getPersonId.getAllByExcel();
        List<ShipIncident> shipIncidents = getShipIncident.getAllByExcel();

        //开始插入数据
        for (int i=0;i<=3000;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO s_shipRenovation(shipId,renovationDate,renovationContent,renovationCompany,responsiblePerson,auditor,auditorDate)" +
                        "VALUES(?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                int j = random.nextInt(ships.size());
                pstm.setString(1,ships.get(j).shipId);
                pstm.setObject(2,randomDate.generateRandomDate("2018-01-01","2018-12-12"));
                pstm.setString(3,renovationContent[random.nextInt(renovationContent.length)]);
                pstm.setString(4,renovationCompany[random.nextInt(renovationCompany.length)]);
                j = random.nextInt(personList.size());
                pstm.setString(5,personList.get(j).name);
                j = random.nextInt(personList.size());
                pstm.setString(6,personList.get(j).name);
                pstm.setObject(7,randomDate.generateRandomDate("2019-01-01","2019-05-12"));



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
