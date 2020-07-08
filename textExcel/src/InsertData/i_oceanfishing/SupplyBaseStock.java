package InsertData.i_oceanfishing;

import getAllByExcel.GetInvestigation;
import getAllByExcel.crew.GetCrew;
import getAllByExcel.oceanfishing.GetSupplyBase;
import random.*;
import random.RandomNumber;
import utilClass.crew.Crew;
import utilClass.oceanfishing.SupplyBase;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class SupplyBaseStock {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void supplyBaseStock() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] regionId = {"10008","10009"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};
        String[] baseName1 = {"哎","哦啊","阿萨","的撒","发的","爱放"};
        String[] baseName2 = {"士大夫","广泛的","的","地方","和发"};
        String[] resourceName = {"大米","蔬菜","水果"};



        // 创建获得 ship excel 表数据的类
        GetSupplyBase getSupplyBase = new GetSupplyBase();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<SupplyBase> supplyBaseList = getSupplyBase.getAllByExcel();


        //开始插入数据
        for (int i=0;i<=100;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO i_supplyBaseStock(baseId,resourceName,existingStock,date)" +
                        "VALUES(?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(supplyBaseList.size());
                pstm.setString(1,supplyBaseList.get(j).supplyBaseId);
                pstm.setString(2,resourceName[random.nextInt(resourceName.length)]);
                pstm.setString(3,randomNumber.generate(1,5));
                pstm.setObject(4,randomDate.generateRandomDate("2019-01-01","2019-12-12"));



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
