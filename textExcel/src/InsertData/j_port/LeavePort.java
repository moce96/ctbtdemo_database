package InsertData.j_port;

import getAllByExcel.crew.GetCrew;
import getAllByExcel.port.GetPort;
import getAllByExcel.port.GetPortRegion;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomJson;
import random.RandomNumber;
import utilClass.crew.Crew;
import utilClass.port.Port;
import utilClass.port.PortRegion;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class LeavePort {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void leavePort() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();


        String[] crewPhoto = {"http://tupian.baike.com/ipad/a0_83_21_01300000631262130905215894888_jpg.html"};



        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();
        GetCrew getCrew = new GetCrew();
        GetPort getPort = new GetPort();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShip.getAllByExcel();
        List<Crew> crewList = getCrew.getAllByExcel();
        List<Port> portList = getPort.getAllByExcel();

        //开始插入数据
        for (int i=0;i<150;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  j_leavePort(shipId,portId,leaveTime,crewId,cerwPhoto)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(ships.size());
                pstm.setString(1,ships.get(j).shipId);
                j = random.nextInt(portList.size());
                pstm.setString(2,portList.get(j).portId);
                pstm.setObject(3,randomDate.generateRandomDate("2018-01-01","2019-12-12"));
                j = random.nextInt(crewList.size());
                pstm.setString(4,randomJson.generateCrew(4,crewList));
                pstm.setObject(5,crewPhoto[random.nextInt(crewPhoto.length)]);

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
