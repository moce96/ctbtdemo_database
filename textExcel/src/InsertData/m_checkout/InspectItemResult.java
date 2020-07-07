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
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InspectItemResult {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void inspectItemResult() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] content = {"贪污受贿","证书不全","在逃嫌疑人"};




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
                String sql = "INSERT INTO m_inspectItemResult(shipId,inspectItemId,inspectType,fisheryProductId,inspectiResult,conformity,inspectDate)" +
                        "VALUES(?,?,?,?,?,?,?)";



                //将以;分隔的字符串转换为List
                List<String> inspectItemIdList = Arrays.asList(inspectApply.inspectItemIdList.split(";"));

                for (String inspectItemId : inspectItemIdList) {
                    pstm = conn.prepareStatement(sql);

                    pstm.setString(1,inspectApply.shipId);
                    pstm.setString(2,inspectItemId);
                    pstm.setString(3,inspectApply.inspectType);
                    pstm.setString(4,inspectApply.fisheryProductId);
                    pstm.setString(5,"a");
                    pstm.setString(6,"b");
                    pstm.setObject(7,randomDate.generateRandomDate("2018-07-01","2019-07-01"));

                    pstm.executeUpdate();
                }



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
