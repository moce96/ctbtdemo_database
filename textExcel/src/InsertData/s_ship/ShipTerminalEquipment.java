package InsertData.s_ship;

import getAllByExcel.ship.GetEquipmentType;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomNumber;
import utilClass.ship.EquipmentType;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipTerminalEquipment {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void shipTerminalEquipment() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] deviceState = {"较差","良好","优秀"};



        // 创建获得 ship excel 表数据的类
        GetShip getShipExcel = new GetShip();
        GetEquipmentType getEquipmentType = new GetEquipmentType();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<EquipmentType> equipmentTypes = getEquipmentType.getAllByExcel();

        //开始插入数据
        for (Ship ship : ships) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO s_shipTerminalEquipment(deviceId,deviceType,deviceTypeId,shipId,deviceState,remarks)" +
                        "VALUES(?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,randomNumber.generate(1,5));
                int j = random.nextInt(equipmentTypes.size());
                pstm.setString(2,equipmentTypes.get(j).equipmentType);
                pstm.setString(3,equipmentTypes.get(j).equipmentTypeId);
                j = random.nextInt(ships.size());
                pstm.setString(4,ship.shipId);
                pstm.setString(5,deviceState[random.nextInt(deviceState.length)]);
                pstm.setString(6,"暂无");






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
