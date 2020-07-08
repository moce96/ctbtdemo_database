package InsertData.j_port;

import getAllByExcel.port.GetPortFunctionalArea;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomJson;
import random.RandomNumber;
import utilClass.port.PortFunctionalArea;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class FunctionAreaShipStop {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void functionAreaShipStop() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();





        // 创建获得 ship excel 表数据的类
        GetPortFunctionalArea getPortFunctionalArea = new GetPortFunctionalArea();
        GetShip getShip = new GetShip();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<PortFunctionalArea> functionalAreaList = getPortFunctionalArea.getAllByExcel();
        List<Ship> ships = getShip.getAllByExcel();


        //开始插入数据
        for (int i=0;i<100;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  j_functionAreaShipStop(portId,functionAreaId,shipId,mooringTime)" +
                        "VALUES(?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(functionalAreaList.size());
                pstm.setString(1,functionalAreaList.get(j).portId);
                pstm.setString(2,functionalAreaList.get(j).id);
                j = random.nextInt(ships.size());
                pstm.setString(3,ships.get(j).shipId);
                pstm.setObject(4,randomDate.generateRandomDate("2018-01-01","2019-12-12"));

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
