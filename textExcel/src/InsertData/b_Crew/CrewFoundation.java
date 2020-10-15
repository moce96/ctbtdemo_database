package InsertData.b_Crew;

import getAllByExcel.ship.GetEquipmentType;
import getAllByExcel.crew.GetCrew;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomNumber;
import utilClass.ship.EquipmentType;
import utilClass.crew.Crew;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CrewFoundation {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static String user = "root";
    private static String password = "123456";

    public static void crewFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] gender = {"男","女"};
        String[] nationality = {"中国","中国","中国","韩国"};
        String[] city = {"舟山","宁波","台州","连云港","盐城","海口","烟台","威海","大连","葫芦岛","上海","厦门","福州","泉州"};
        String[] county = {"普陀","定海","江东","江北","路桥","黄岩","连云","海州","盐都","大丰","龙华","美兰","芝罘","莱山","文登","环翠","中山","金州","连山","龙岗","浦东","静安","思明","湖里","鼓楼","台江","仓山","马尾"};
        String[] village = {"华西","南山","长江","九星","西王","航民","永联","红嘴","南金","西杨","富山","水磨","杜杉","坊巷"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};
        Map<String, String[]> map = new HashMap<String, String[]>();
        for(int i = 0; i <14 ; i++){
            map.put(city[i], new String[]{county[2*i],county[2*i+1]});
        }



        // 创建获得 ship excel 表数据的类
        GetShip getShipExcel = new GetShip();
        GetCrew getPersonId = new GetCrew();
        GetEquipmentType getEquipmentType = new GetEquipmentType();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<Crew> personList = getPersonId.getAllByExcel();
        List<EquipmentType> equipmentTypes = getEquipmentType.getAllByExcel();
        System.out.println(personList.size());
        //开始插入数据
        for (Crew person : personList) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
                String sql = "INSERT INTO b_crewFoundation_copy1(personId,personName,gender,birthday,nationality,contactPhone,city,county,village,emergContact,emergContactPhone)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,person.id);
                pstm.setString(2,person.name);
                pstm.setString(3,gender[random.nextInt(gender.length)]);
                pstm.setObject(4,randomDate.generateRandomDate("1970-01-01","1997-06-11"));
                pstm.setString(5,nationality[random.nextInt(nationality.length)]);
                pstm.setString(6,"1"+randomNumber.generate(1,10));
                String chosenCity = city[random.nextInt(city.length)];
                pstm.setString(7, chosenCity);
//                pstm.setString(8,county[random.nextInt(county.length)]);
                pstm.setString(8,map.get(chosenCity)[random.nextInt(2)]);
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
