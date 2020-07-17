package InsertData.m_checkout;

import getAllByExcel.checkout.GetInspectApply;
import getAllByExcel.checkout.GetInspectItem;
import getAllByExcel.ship.GetShip;
import random.*;
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

        String[] lift_equipment = {"可使用","可使用","可使用","可使用","可使用","可使用","可使用","可使用","可使用","可使用","已损坏","可使用","可使用","可使用","可使用","可使用","可使用"};
        String[] ship_load_line = {"良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","良好","部分损坏","部分损坏","部分损坏","部分损坏","部分损坏","部分损坏","损坏严重","损坏严重","损坏严重","损坏严重"};






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
                String sql = "INSERT INTO m_inspect_ship_basic_information(ship_id,ship_type,ship_safe_equipment,tons,fishing,lift_equipment,ship_load_line,hull,equipment,electronic_mechanism,refrigeration_device,radio_equipment)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,inspectApply.shipId);
                if (inspectApply.shipType == "1") {
                    pstm.setString(2,"远洋渔船");
                } else if (inspectApply.shipType == "2") {
                    pstm.setString(2,"工程船");
                } else if (inspectApply.shipType == "3") {
                    pstm.setString(2,"海钓船");
                } else if (inspectApply.shipType == "4") {
                    pstm.setString(2,"执法船");
                } else if (inspectApply.shipType == "5") {
                    pstm.setString(2,"休闲渔船");
                } else if (inspectApply.shipType == "6") {
                    pstm.setString(2,"国内渔船");
                } else {
                    pstm.setString(2,"未标明");
                }
                pstm.setString(3,"暂无");
                pstm.setString(4,randomNumber.generate(1,3));
                pstm.setString(5,"暂无");
                pstm.setString(6,lift_equipment[random.nextInt(lift_equipment.length)]);
                pstm.setString(7,ship_load_line[random.nextInt(ship_load_line.length)]);
                pstm.setString(8,ship_load_line[random.nextInt(ship_load_line.length)]);
                pstm.setString(9,ship_load_line[random.nextInt(ship_load_line.length)]);
                pstm.setString(10,ship_load_line[random.nextInt(ship_load_line.length)]);
                pstm.setString(11,ship_load_line[random.nextInt(ship_load_line.length)]);
                pstm.setString(12,ship_load_line[random.nextInt(ship_load_line.length)]);
                pstm.setString(13,ship_load_line[random.nextInt(ship_load_line.length)]);


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
