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

public class ShipCredit {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void shipCredit() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] violationMark = {"+","-"};
        String[] violationLoc = {"30.19,121.94","30.22,121.55","30.33,122.34","30.22,123.11","30.36,122.56","30.17,121.93","30.25,121.65","30.12,122.25","30.25,123.09","30.26,122.44","30.39,121.94","30.22,121.25","30.13,122.34","30.22,123.21","30.16,122.56"};
        String[] violationDesc = {"证书失效出海作业","阻碍国家机关公务人员执法","违反海上安全交通规定","擅自进入国家限制水域或岛屿","使用虚假船名或船号"};
        String[] disposalSituation = {"处理成功","处理失败","处理成功","处理成功"};



        // 创建获得 excel 表数据的类
        GetShip getShipExcel = new GetShip();
        GetCrew getPersonId = new GetCrew();
        GetShipIncident getShipIncident = new GetShipIncident();

        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<Crew> personList = getPersonId.getAllByExcel();
        List<ShipIncident> shipIncidents = getShipIncident.getAllByExcel();

        //开始插入数据
        for (int i=0;i<=2500;i++) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO s_shipCredit(shipId,shipIncidentId,shipIncidentTypeId,violationMark,violationDate,violationLoc,violationDesc,disposalSituation,disposer,enclose,disposalRecord)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                int j = random.nextInt(ships.size());
                pstm.setString(1,ships.get(j).shipId);
                pstm.setString(2,randomNumber.generate(1,5));
                j = random.nextInt(shipIncidents.size());
                pstm.setString(3,shipIncidents.get(j).id);
                pstm.setString(4,violationMark[random.nextInt(violationMark.length)] + String.valueOf(random.nextInt(12)+1));
                pstm.setObject(5,randomDate.generateRandomDate("2018-01-01","2018-12-12"));
                pstm.setString(6,violationLoc[random.nextInt(violationLoc.length)]);
                pstm.setString(7,violationDesc[random.nextInt(violationDesc.length)]);
                pstm.setString(8,disposalSituation[random.nextInt(disposalSituation.length)]);
                j = random.nextInt(personList.size());
                pstm.setString(9,personList.get(j).name);
                pstm.setString(10,"暂无");
                pstm.setString(11,"暂无");


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
