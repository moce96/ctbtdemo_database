package InsertData.s_ship;

import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomNumber;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class FishCatch {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

   public static void fishCatch() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] catchType = {"鲐鲹鱼","蟹类","带鱼","鳗鱼","龙头鱼","小黄鱼","头足类","梅鱼"};
        String[] photo = {"http://old.men2qing.com/uploadfile/M2Q_image_upload/N40-10-10-0006-02.jpg","https://www.nyzy.com/UploadFiles/2018-08/ycadmin/2018081914430193792.jpg","https://inews.gtimg.com/newsapp_bt/0/9327273284/641","https://img.alicdn.com/imgextra/i4/i3/T1DQ7MFXtbXXXXXXXX_!!0-item_pic.jpg"};
        String[] regionId = {"10024","10025","10026","10027"};


        // 创建获得 ship excel 表数据的类
        GetShip getShipExcel = new GetShip();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();

        //开始插入数据
        for (int i=0;i<=3000;i++) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO s_fishCatch(shipId,catchDate,catchType,catchQuantity,photo,companyId,regionId)" +
                        "VALUES(?,?,?,?,?,?,?)";

                String sql1 = "INSERT INTO z_shipFoundation(shipId,shipName) VALUES(?,?)";

                pstm = conn.prepareStatement(sql);


                int j = random.nextInt(ships.size());
                pstm.setString(1,ships.get(j).shipId);
                pstm.setObject(2,randomDate.generateRandomDate("2017-01-01","2018-01-01"));
                pstm.setString(3,catchType[random.nextInt(catchType.length)]);
                pstm.setString(4,randomNumber.generate(1,3));
                pstm.setString(5,photo[random.nextInt(photo.length)]);
                pstm.setString(6,ships.get(j).companyId);
                pstm.setString(7,regionId[random.nextInt(regionId.length)]);


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
