package InsertData.j_port;

import getAllByExcel.port.GetPortFunctionalArea;
import random.*;
import random.RandomJson;
import random.RandomNumber;
import utilClass.port.PortFunctionalArea;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class PortFunctionArea {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void portFunctionArea() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

        String[] regionId = {"10012","10013"};
        String[] company = {"川聚力","云非","川福","流宁","网可"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};
        String[] description = {"此功能区为卸货区","此功能区为修理区","此功能区为停靠区","此功能区为装货区"};



        // 创建获得 ship excel 表数据的类
        GetPortFunctionalArea getPortFunctionalArea = new GetPortFunctionalArea();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<PortFunctionalArea> portFunctionAreaList = getPortFunctionalArea.getAllByExcel();



        //开始插入数据
        for (PortFunctionalArea portFunctionalArea : portFunctionAreaList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  j_portFunctionalArea(portId,functionalAreaId,regionId,description,capacity)" +
                        "VALUES(?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,portFunctionalArea.portId);
                pstm.setString(2,portFunctionalArea.id);
                pstm.setString(3,portFunctionalArea.regionId);
                pstm.setString(4,description[random.nextInt(description.length)]);
                pstm.setString(5,randomNumber.generate(1,2)+"艘船");

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
