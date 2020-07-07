package InsertData.i_oceanfishing;

import getAllByExcel.GetInvestigation;
import getAllByExcel.crew.GetCrew;
import random.RandomDate;
import random.RandomNumber;
import utilClass.crew.Crew;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class FisheryData {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void fisheryData() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] regionId = {"10010"};
        String[] resourceName = {"黄鱼","乌贼","海蜇","带鱼"};
        String[] resourceType = {"鱼类"};
        String[] description = {"一种鱼类"};




        // 创建获得 ship excel 表数据的类
        GetCrew getPersonId = new GetCrew();
        GetInvestigation getInvestigation = new GetInvestigation();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Crew> personList = getPersonId.getAllByExcel();
        List<utilClass.Investigation> investigations = getInvestigation.getAllByExcel();


        //开始插入数据
        for (int i=0;i<=20;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  i_fisheryData(regionId,resourceName,resourceType,description,estimatedCatch,upDataTime)" +
                        "VALUES(?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,regionId[random.nextInt(regionId.length)]);
                pstm.setString(2,resourceName[random.nextInt(resourceName.length)]);
                pstm.setString(3,resourceType[random.nextInt(resourceType.length)]);
                pstm.setString(4,"一种鱼类");
                pstm.setString(5,randomNumber.generate(1,6));
                pstm.setObject(6,randomDate.generateRandomDate("2019-01-01","2019-06-06"));


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
