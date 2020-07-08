package InsertData.ship;

import getAllByExcel.GetPersonId;
import getAllByExcel.GetShipExcel;
import getAllByExcel.ship.GetShip;
import random.MysqlRead;
import random.RandomDate;
import random.RandomNumber;
import utilClass.Person;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipStakeholder {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void shipStakeholder() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};


        // 创建获得 ship excel 表数据的类
        GetShip getShip = new GetShip();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<utilClass.ship.Ship> ships = getShip.getAllByExcel();

        //开始插入数据
        for (Ship ship : ships) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO s_shipStakeholder(shipId,shipOwnerId,emergContact,emergContactId,emergPhone,guardianName,guardianId,guardianPhone)" +
                        "VALUES(?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,ship.shipId);
                pstm.setString(2,randomNumber.generate(1,18));
                pstm.setString(3,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(4,randomNumber.generate(1,18));
                pstm.setString(5,"1"+randomNumber.generate(1,10));
                pstm.setString(6,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(7,randomNumber.generate(1,18));
                pstm.setString(8,"1"+randomNumber.generate(1,10));

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
