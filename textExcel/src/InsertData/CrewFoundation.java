package InsertData;

import getAllByExcel.GetEquipmentType;
import getAllByExcel.GetPersonId;
import getAllByExcel.GetShipExcel;
import random.RandomDate;
import random.RandomNumber;
import utilClass.EquipmentType;
import utilClass.Person;
import utilClass.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class CrewFoundation {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void crewFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] gender = {"男","女"};
        String[] nationality = {"中国","中国","中国","韩国"};
        String[] city = {"舟山","宁波","台州","连云港","盐城","海口","烟台","威海","大连","葫芦岛","上海","厦门","福州","泉州"};
        String[] county = {"普陀","定海","江东","江北","路桥","黄岩","连云","海州","盐都","大丰","龙华","美兰","芝罘","莱山","文登","环翠","中山","金州","连山","龙岗","浦东","","连山","龙岗","浦东"};
        String[] village = {"华西","南山","长江","九星","西王","航民","永联","红嘴","南金","西杨","富山","水磨","杜杉","坊巷"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};





        // 创建获得 ship excel 表数据的类
        GetShipExcel getShipExcel = new GetShipExcel();
        GetPersonId getPersonId = new GetPersonId();
        GetEquipmentType getEquipmentType = new GetEquipmentType();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<Person> personList = getPersonId.getAllByExcel();
        List<EquipmentType> equipmentTypes = getEquipmentType.getAllByExcel();

        //开始插入数据
        for (Person person : personList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO z_crewFoundation(personId,personName,gender,birthday,nationality,contactPhone,city,county,village,emergContact,emergContactPhone)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,person.id);
                pstm.setString(2,person.name);
                pstm.setString(3,gender[random.nextInt(gender.length)]);
                pstm.setObject(4,randomDate.generateRandomDate("1970-01-01","1997-06-11"));
                pstm.setString(5,nationality[random.nextInt(nationality.length)]);
                pstm.setString(6,"1"+randomNumber.generate(1,10));
                pstm.setString(7,city[random.nextInt(city.length)]);
                pstm.setString(8,county[random.nextInt(county.length)]);
                pstm.setString(9,village[random.nextInt(village.length)]);
                pstm.setString(10,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(11,"1"+randomNumber.generate(1,10));


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
