package InsertData.j_port;

import getAllByExcel.port.GetPort;
import getAllByExcel.port.GetPortRegion;
import random.RandomDate;
import random.RandomJson;
import random.RandomNumber;
import utilClass.port.Port;
import utilClass.port.PortRegion;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class PortFoundation {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void portFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();


        String[] first = {"京","外","莲","锦","衡","新","中"};
        String[] second = {"庄","环","花","江","山","闸","山"};
        String[] portType = {"商业港口","军用港口","渔用港口","工业港口","避风港口"};
        String[] company = {"川聚力","云非","川福","流宁","网可"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};




        // 创建获得 ship excel 表数据的类
        GetPort getPort = new GetPort();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Port> portList = getPort.getAllByExcel();



        //开始插入数据
        for (Port port : portList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  j_portFoundation(portId,portName,portType,regionId,buildDate,capacity,windResistance,company,principal,contact,shelteredWaters)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,port.portId);
                pstm.setString(2,port.name);
                pstm.setString(3,portType[random.nextInt(portType.length)]);
                pstm.setString(4,port.regionId);
                pstm.setObject(5,randomDate.generateRandomDate("2017-01-01","2017-06-01"));
                pstm.setString(6,randomNumber.generate(1,3)+"艘船");
                pstm.setString(7,randomNumber.generate(1,1)+"级");
                pstm.setString(8,company[random.nextInt(company.length)]);
                pstm.setString(9,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(10,"1"+randomNumber.generate(1,10));
                pstm.setString(11,"暂无");

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
