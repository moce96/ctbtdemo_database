package test;

import getAllByExcel.crew.GetCrew;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomNumber;
import utilClass.EquipmentType;
import utilClass.Person;
import utilClass.crew.Crew;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;
/*
* 用于生成excel表格“crew”所需的数据
* */
public class insertSample {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://localhost:3306/myk?serverTimezone=GMT%2B8&useSSL=false";
    private static String user = "root";
    private static String password = "923099122";

    public static void insert() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        // 创建获得 ship excel 表数据的类
        GetShip getShipExcel = new GetShip();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        System.out.println(ships.size());

        String[] gender = {"男","女"};
        String[] nationality = {"中国","中国","中国","韩国"};
        String[] city = {"舟山","宁波","台州","连云港","盐城","海口","烟台","威海","大连","葫芦岛","上海","厦门","福州","泉州"};
        String[] county = {"普陀","定海","江东","江北","路桥","黄岩","连云","海州","盐都","大丰","龙华","美兰","芝罘","莱山","文登","环翠","中山","金州","连山","龙岗","浦东","","连山","龙岗","浦东"};
        String[] village = {"华西","南山","长江","九星","西王","航民","永联","红嘴","南金","西杨","富山","水磨","杜杉","坊巷"};
        String[] familyname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
                "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
                "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
                "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
                "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
                "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","秋白", "南风", "醉山", "初彤", "凝海", "紫文", "凌晴", "香卉", "雅琴", "傲安",
                "傲之", "初蝶", "寻桃", "代芹", "诗霜", "春柏", "绿夏", "碧灵", "诗柳", "夏柳",
                "采白", "慕梅", "乐安", "冬菱", "紫安", "宛凝", "雨雪", "易真", "安荷", "静竹",
                "飞雪", "雪兰", "雅霜", "从蓉", "冷雪", "靖巧", "翠丝", "觅翠", "凡白", "乐蓉",
                "迎波", "丹烟", "梦旋", "书双", "念桃", "夜天", "海桃", "青香", "恨风", "安筠",
                "觅柔", "初南", "秋蝶", "千易", "安露", "诗蕊", "山雁", "友菱", "香露", "晓兰",
                "涵瑶", "秋柔", "思菱", "醉柳", "以寒", "迎夏", "向雪", "香莲", "以丹", "依凝",
                "如柏", "雁菱", "凝竹", "宛白", "初柔", "南蕾", "书萱", "梦槐", "香芹", "南琴",
                "绿海", "沛儿", "晓瑶", "听春", "易巧", "念云", "晓灵", "静枫", "夏蓉", "如南",
                "幼丝", "秋白", "冰安", "凝蝶", "紫雪", "念双", "念真", "曼寒", "凡霜", "白卉",
                "语山", "冷珍", "秋翠", "夏柳", "如之", "忆南", "书易", "翠桃", "寄瑶", "如曼",
                "问柳", "香梅", "幻桃", "又菡", "春绿", "醉蝶", "亦绿", "诗珊", "听芹", "新之",
                "博瀚", "博超", "才哲", "才俊", "成和", "成弘", "昊苍", "昊昊", "昊空", "昊乾",
                "昊然", "昊然", "昊天", "昊焱", "昊英", "浩波", "浩博", "浩初", "浩大", "浩宕",
                "浩荡", "浩歌", "浩广", "浩涆", "浩瀚", "浩浩", "浩慨", "浩旷", "浩阔", "浩漫",
                "浩淼", "浩渺", "浩邈", "浩气", "浩然", "浩穰", "浩壤", "浩思", "浩言", "皓轩",
                "和蔼", "和安", "和昶", "翔东", "昊伟", "楚桥", "智霖", "浩杰", "炎承", "思哲"};
        String[] bloodType = {"A", "B", "AB", "O"};





        //开始插入数据
        for (int i = 0;i<9;i++) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
                String sql = "INSERT INTO crew(personName,personId,bloodType,shipId,startTime)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                pstm.setString(1,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(2,randomNumber.generate(1,18));
                pstm.setString(3,bloodType[random.nextInt(bloodType.length)]);
                pstm.setString(4,ships.get(random.nextInt(ships.size())).shipId);
                pstm.setObject(5,randomDate.generateRandomDate( "2010-01-01","2020-06-11"));



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
