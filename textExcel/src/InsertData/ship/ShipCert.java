package InsertData.ship;

import getAllByExcel.GetPersonId;
import getAllByExcel.GetShipExcel;
import random.*;
import random.RandomNumber;
import utilClass.Person;
import utilClass.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipCert {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void shipCert() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] certType = {"渔船安全证书","渔船营运检验报告","国内海洋渔船检验记录","船舶证书"};
        String[] certName = {"浙江省渔船安全证书2019注","浙江省渔船安全证书2020注","浙江省渔船营运检验报告2019注","浙江省渔船营运检验报告2020注","浙江省国内海洋渔船检验记录","浙江省船舶证书"};
        String[] city2 = {"舟山","宁波","台州","连云港","盐城","海口","烟台","威海","大连","葫芦岛","上海","厦门","福州","泉州"};
        String[] county2 = {"普陀","定海","江东","江北","路桥","黄岩","连云","海州","盐都","大丰","龙华","美兰","芝罘","莱山","文登","环翠","中山","金州","连山","龙岗","浦东","静安","思明","海沧 ","台江","仓山","丰泽","洛江"};
        String[] town2= {"玉山","狮山","杨舍","金港","长安","虎门","花桥","锦丰","柳市","石排","张浦","龙湖","滨江","华士"};
        String[] comunity = {"碧桂园","绿城小区","鑫宇嘉苑","万科新都汇","龙湖春江天地","四季都会","保利时代公馆","万科启辰","康博名邸","中海建国里","保利玲珑公馆","龙湖天空城","保利象屿悦府","绿城金山府"};


        // 创建获得 ship excel 表数据的类
        GetShipExcel getShipExcel = new GetShipExcel();
        GetPersonId getPersonId = new GetPersonId();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<Person> personList = getPersonId.getAllByExcel();

        //开始插入数据
        for (int i=0;i<=30;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO z_shipCert(certId,certType,shipId,certName,holder,holderId,holderPhone,holderMobilePhone,holderSatellitePhone,holderLoc,certEnclosure,certAppTime,certEndTime,others)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,randomNumber.generate(1,4));
                pstm.setString(2,certType[random.nextInt(certType.length)]);
                int j = random.nextInt(ships.size());
                pstm.setString(3,ships.get(j).shipId);
                pstm.setString(4,certName[random.nextInt(certName.length)]);
                int k = random.nextInt(personList.size());
                pstm.setString(5,personList.get(k).name);
                pstm.setString(6,personList.get(k).id);
                pstm.setString(7,"1"+randomNumber.generate(1,10));
                pstm.setString(8,"1"+randomNumber.generate(1,10));
                pstm.setString(9,"1"+randomNumber.generate(1,10));
                pstm.setString(10,city2[random.nextInt(city2.length)]+county2[random.nextInt(county2.length)]+town2[random.nextInt(town2.length)]+String.valueOf(random.nextInt(20)+1)+"单元"+String.valueOf(random.nextInt(5)+1)+"号楼");
                pstm.setString(11,"certenclose");
                pstm.setObject(12,randomDate.generateRandomDate("2018-01-01","2018-06-01"));
                pstm.setObject(13,randomDate.generateRandomDate("2019-01-01","2019-06-01"));
                pstm.setString(14,"other");

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
