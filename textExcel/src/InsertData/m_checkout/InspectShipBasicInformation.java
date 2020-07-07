package InsertData.m_checkout;

import getAllByExcel.checkout.GetInspectApply;
import getAllByExcel.checkout.GetInspectItem;
import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomJson;
import random.RandomNumber;
import utilClass.checkout.InspectItem;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class InspectShipBasicInformation {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void inspectShipBasicInformation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();






        // 创建获得 ship excel 表数据的类
        GetInspectItem getInspectItem = new GetInspectItem();
        GetShip getShip = new GetShip();
        GetInspectApply getInspectApply = new GetInspectApply();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<InspectItem> inspectItems = getInspectItem.getAllByExcel();
        List<Ship> shipList = getShip.getAllByExcel();
        List<utilClass.checkout.InspectApply> inspectApplyList = getInspectApply.getAllByExcel();

        //开始插入数据
        for (utilClass.checkout.InspectApply inspectApply : inspectApplyList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO m_inspectShipBasicInformation(shipId,shipType,shipSafeEquipment,tons,fishing,liftEquipment,shipLoadLine,hull,equipment,electromechanical,refrigerationDevice,radioEquipment)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,inspectApply.shipId);
                pstm.setString(2,inspectApply.shipType);
                pstm.setString(3,"a");
                pstm.setString(4,randomNumber.generate(1,3));
                pstm.setString(5,"b");
                pstm.setString(6,"c");
                pstm.setString(7,"d");
                pstm.setString(8,"e");
                pstm.setString(9,"a");
                pstm.setString(10,"a");
                pstm.setString(11,"a");
                pstm.setString(12,"a");
                pstm.setString(13,"a");


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
