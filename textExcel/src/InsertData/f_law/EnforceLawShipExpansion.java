package InsertData.f_law;

import getAllByExcel.ship.GetShipAttribute;
import getAllByExcel.ship.GetShip;
import getAllByExcel.ship.GetShipState;
import getAllByExcel.ship.GetWorkType;
import random.*;
import random.RandomNumber;
import utilClass.ship.Ship;
import utilClass.ship.ShipAttribute;
import utilClass.ship.ShipState;
import utilClass.ship.WorkType;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class EnforceLawShipExpansion {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";





    public static void  enforceLawShipExpansion() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] type = {"国外执法","国内执法","省内执法"};




        // 创建获得 ship excel 表数据的类
        GetShip getShipExcel = new GetShip();
        GetShipState getShipState = new GetShipState();
        GetShipAttribute getShipAttribute = new GetShipAttribute();
        GetWorkType getWorkType = new GetWorkType();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<ShipState> shipStates = getShipState.getAllByExcel();
        List<ShipAttribute> shipAttributes = getShipAttribute.getAllByExcel();
        List<WorkType> workTypes = getWorkType.getAllByExcel();

        //开始插入数据
        for (Ship ship : ships ) {

            if (ship.shipTypeId.equals("4")) {

                try {
                    Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                    String sql = "INSERT INTO f_enforceLawShipExpansion(shipId,shipName,type,camouflageLicence,camouflageAppearance)" +
                            "VALUES(?,?,?,?,?)";

                    pstm = conn.prepareStatement(sql);

                    pstm.setString(1,ship.shipId);
                    pstm.setString(2,ship.shipName);
                    pstm.setString(3,type[random.nextInt(type.length)]);
                    pstm.setString(4,"暂无");
                    pstm.setString(5,"暂无");



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
