package InsertData.g_engineering;

import getAllByExcel.engineering.GetMiningArea;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomNumber;
import utilClass.engineering.MiningArea;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ReclamationRecord {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void reclamationRecord() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        //demo 直接采用读数组的形式，后期可能要读围填海的excel表来对呀围填海id。
        String[] reclamationId = {"1001","1002"};


        // 创建获得  excel 表数据的类
        GetShip getShip = new GetShip();
        GetMiningArea getMiningArea = new GetMiningArea();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<MiningArea> miningAreaList = getMiningArea.getAllByExcel();
        List<Ship> shipList = getShip.getAllByExcel();




        //开始插入数据
        for (int i=0; i<=100; i++) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO g_reclamationRecord(reclamationId,date,shipId,area)" +
                        "VALUES(?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                pstm.setString(1,reclamationId[random.nextInt(reclamationId.length)]);
                pstm.setObject(2,randomDate.generateRandomDate("2018-06-06","2020-03-04"));
                //找到船舶基础表中  工程船的id 才可以插入该数据表，因为只有工程船才能进行工程。
                int j = random.nextInt(shipList.size());
                while (!shipList.get(j).shipTypeId.equals("2")) {
                    j = random.nextInt(shipList.size());
                }
                pstm.setString(3,shipList.get(j).shipId);
                pstm.setString(4,randomNumber.generate(1,2));


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
