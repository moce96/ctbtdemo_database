package InsertData.s_ship;

import getAllByExcel.GetInvestigation;
import getAllByExcel.crew.GetCrew;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomNumber;
import utilClass.Investigation;
import utilClass.crew.Crew;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipComprehensiveEval {



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

        String[] evalLevel = {"甲等","乙等","丙等","丁等"};
        String[] evalContent = {"设备安全评估","电气安全评估","消防安全评估","化学品安全评估","厂房建筑安全评估"};
        String[] enclosure = {"http://pic.5tu.cn/tuku/201611/zhanbanmoban-1590308.html","https://www.16pic.com/zhanban/pic_5241287.html","http://pic.5tu.cn/tuku/201806/zhanbanmoban-1641479.html","http://pic.5tu.cn/tuku/201806/zhanbanmoban-1641479.html","https://www.16pic.com/zhanban/pic_5241272.html"};
        String[] description = {"设备维护情况良好","设备维护情况较差","电气维护情况良好","电气维护情况较差","消防安全维护情况良好","消防安全维护情况","化学用品维护情况良好","化学用品维护情况较差","厂房建筑维护情况良好","厂房建筑维护情况较差"};





        // 创建获得 excel 表数据的类
        GetShip getShipExcel = new GetShip();
        GetCrew getPersonId = new GetCrew();
        GetInvestigation getInvestigation = new GetInvestigation();

        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<Crew> personList = getPersonId.getAllByExcel();
        List<Investigation> investigations = getInvestigation.getAllByExcel();

        //开始插入数据
        for (Investigation investigation : investigations) {


            if (investigation.object.equals("ship")) {

                try {
                    Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                    String sql = "INSERT INTO s_shipComprehensiveEval(shipId,dangerCheckId,evalTime,evalLevel,evalContent,evalPoint,enclosure,description,checkPerson)" +
                            "VALUES(?,?,?,?,?,?,?,?,?)";

                    pstm = conn.prepareStatement(sql);

                    int j = random.nextInt(ships.size());
                    pstm.setString(1,ships.get(j).shipId);
                    pstm.setString(2,investigation.id);
                    pstm.setObject(3,randomDate.generateRandomDate("2018-01-01","2019-01-01"));
                    pstm.setString(4,evalLevel[random.nextInt(evalLevel.length)]);
                    pstm.setString(5,evalContent[random.nextInt(evalContent.length)]);
                    pstm.setInt(6,random.nextInt(50)+50);
                    pstm.setString(7,enclosure[random.nextInt(enclosure.length)]);
                    pstm.setString(8,description[random.nextInt(description.length)]);
                    j = random.nextInt(personList.size());
                    pstm.setString(9,personList.get(j).name);

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
}


