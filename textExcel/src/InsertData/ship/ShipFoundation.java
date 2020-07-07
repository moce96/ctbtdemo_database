package InsertData.ship;

import getAllByExcel.GetShipExcel;
import random.RandomDate;
import random.RandomNumber;
import test.Student;
import utilClass.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipFoundation {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";





    public static void  shipFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();


        String[] city = {"舟山","宁波","台州","连云港","盐城","海口","烟台","威海","大连","葫芦岛","上海","厦门","福州","泉州"};
        String[] county = {"普陀","定海","江东","江北","路桥","黄岩","连云","海州","盐都","大丰","龙华","美兰","芝罘","莱山","文登","环翠","中山","金州","连山","龙岗","浦东","","连山","龙岗","浦东"};
        String[] town = {"玉山","狮山","杨舍","金港","长安","虎门","花桥","锦丰","柳市","石排","张浦","龙湖","滨江","华士"};
        String[] village = {"华西","南山","长江","九星","西王","航民","永联","红嘴","南金","西杨","富山","水磨","杜杉","坊巷"};
        String[] shipyard = {"大宇造船","三星重工","现代重工","三湖重工","大岛造船","新时代造船","扬子江船业","江南造船"};
        String[] shipyardLoc = {"烟台市青岛大街2-1","宁波市北仑区港口路18号","上海市浦东新区郭守敬路498号上海浦东软件园10号楼1楼102","上海市静安区延平路121号","上海市浦东大道2198号附近","泰州市靖江市新港大道南侧","江阴市东兴镇联谊路1号","上海市兴奔路66号"};
        String[] material = {"碳素钢","合金钢","铸铁","船用钢"};
        String[] hostModel = {"ABT-51","CC-900","WD-55","WD-68"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};
        String[] shipPhoto = {"https://blog.csdn.net/java20131115/article/details/23759427/","https://blog.csdn.net/cao812755156/article/details/89598410","https://blog.csdn.net/bigfacemiao/article/details/83830201","https://blog.csdn.net/sinat_36454672/article/details/97100707"};



        // 创建获得 ship excel 表数据的类
        GetShipExcel getShipExcel = new GetShipExcel();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();

        //开始插入数据
        for (Ship ship : ships ) {
            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO z_shipFoundation(shipId,shipTypeId,shipAttributeId,shipName,department,province,city,county,town,village,buildYear,shipyard,shipyardLoc,material,shipLength,shipWidth,shipDepth,windResistRate,tons,power,hostModel,emergencyContact,emergPhone,emergMobilePhone,emergSatellitePhone,shipPhoto,shipStateId,shipLicence)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                String sql1 = "INSERT INTO z_shipFoundation(shipId,shipName) VALUES(?,?)";

                pstm = conn.prepareStatement(sql1);

                pstm.setString(1,ship.shipId);
//                pstm.setString(2,String.valueOf(random.nextInt(6)+1));
//                pstm.setString(3,"shipAttributeId");
                pstm.setString(2,ship.shipName);
//                pstm.setString(5,"department");
//                pstm.setString(6,"浙江省");
//                pstm.setString(7,city[random.nextInt(city.length)]);
//                pstm.setString(8,county[random.nextInt(county.length)]);
//                pstm.setString(9,town[random.nextInt(town.length)]);
//                pstm.setString(10,village[random.nextInt(village.length)]);
//                pstm.setObject(11,randomDate.generateRandomDate("2016-01-01","2016-06-01"));
//                pstm.setString(12,shipyard[random.nextInt(shipyard.length)]);
//                pstm.setString(13,shipyardLoc[random.nextInt(shipyardLoc.length)]);
//                pstm.setString(14,material[random.nextInt(material.length)]);
//                pstm.setDouble(15,Double.valueOf(random.nextInt(17)+1));
//                pstm.setDouble(16,Double.valueOf(random.nextInt(12000-400+1)+400));
//                pstm.setDouble(17,Double.valueOf(random.nextInt(3000-400+1)+1));
//                pstm.setDouble(18,Double.valueOf(random.nextInt(17)+1));
//                pstm.setDouble(19,Double.valueOf(random.nextInt(12000-400+1)+400));
//                pstm.setDouble(20,Double.valueOf(random.nextInt(3000-400+1)+1));
//                pstm.setString(21,hostModel[random.nextInt(hostModel.length)]);
//                pstm.setString(22,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
//                pstm.setString(23,"1"+randomNumber.generate(1,10));
//                pstm.setString(24,"1"+randomNumber.generate(1,10));
//                pstm.setString(25,"1"+randomNumber.generate(1,10));
//                pstm.setString(26,shipPhoto[random.nextInt(shipPhoto.length)]);
//                pstm.setString(27,"shipsateid");
//                pstm.setString(28,randomNumber.generate(2,3)+randomNumber.generate(1,4));




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


