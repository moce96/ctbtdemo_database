package InsertData.ship;

import getAllByExcel.GetPersonId;
import getAllByExcel.GetShipExcel;
import getAllByExcel.GetShipIncident;
import random.MysqlRead;
import random.*;
import random.RandomNumber;
import utilClass.Person;
import utilClass.Ship;
import utilClass.ShipIncident;

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

    public static void shipComprehensiveEval() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] violationMark = {"+","-"};
        String[] violationLoc = {"30.19,121.94","30.22,121.55","30.33,122.34","30.22,123.11","30.36,122.56","30.17,121.93","30.25,121.65","30.12,122.25","30.25,123.09","30.26,122.44","30.39,121.94","30.22,121.25","30.13,122.34","30.22,123.21","30.16,122.56"};
        String[] violationDesc = {"证书失效出海作业","阻碍国家机关公务人员执法","违反海上安全交通规定","擅自进入国家限制水域或岛屿","使用虚假船名或船号"};
        String[] disposalSituation = {"处理成功","处理失败"};



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
                String sql = "INSERT INTO z_shipCredit(shipId,shipIncidentId,violationMark,violationDate,violationLoc,violationDesc,disposalSituation,disposer,enclose,disposalRecord)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                int j = random.nextInt(ships.size());
                pstm.setString(1,ships.get(j).shipId);
                int k = random.nextInt(shipIncidents.size());
                pstm.setString(2,shipIncidents.get(k).id);
                pstm.setString(3,violationMark[random.nextInt(violationMark.length)] + String.valueOf(random.nextInt(12)+1));
                pstm.setObject(4,randomDate.generateRandomDate("2018-01-01","2018-12-12"));
                pstm.setString(5,violationLoc[random.nextInt(violationLoc.length)]);
                pstm.setString(6,violationDesc[random.nextInt(violationDesc.length)]);
                pstm.setString(7,disposalSituation[random.nextInt(disposalSituation.length)]);
                j = random.nextInt(personList.size());
                pstm.setString(8,personList.get(j).name);
                pstm.setString(9,"暂无");
                pstm.setString(10,"暂无");


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
