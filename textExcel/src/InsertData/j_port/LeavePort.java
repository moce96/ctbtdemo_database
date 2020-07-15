package InsertData.j_port;

import getAllByExcel.crew.GetCrew;
import getAllByExcel.port.GetArrivePort;
import getAllByExcel.port.GetPort;
import getAllByExcel.port.GetPortRegion;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomJson;
import random.RandomNumber;
import utilClass.crew.Crew;
import utilClass.port.ArrivePort;
import utilClass.port.Port;
import utilClass.port.PortRegion;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class LeavePort {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static final String[] mysqlMessage = MysqlRead.message;


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
        GetArrivePort getArrivePort = new GetArrivePort();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShip.getAllByExcel();
        List<Crew> crewList = getCrew.getAllByExcel();
        List<Port> portList = getPort.getAllByExcel();
        List<ArrivePort> arrivePortList = getArrivePort.getAllByExcel();

        //开始插入数据
        for (ArrivePort arrive:arrivePortList) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  j_leavePort(shipId,portId,leaveTime,crewId,cerwPhoto,id)" +
                        "VALUES(?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,arrive.shipid);
                pstm.setString(2,arrive.portId);

                pstm.setObject(3,DateCalculate.add(arrive.arrivalTime,30));
                pstm.setString(4,arrive.crewId);
                pstm.setObject(5,arrive.crewPhoto);
                pstm.setString(6,arrive.id);;

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
