package InsertData.i_oceanfishing;

import getAllByExcel.GetInvestigation;
import getAllByExcel.crew.GetCrew;
import random.*;
import random.RandomNumber;
import utilClass.crew.Crew;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class OceanResources {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void oceanResources() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] regionId = {"10007"};
        String[] resourceType = {"燃料","鱼类"};
        String[] fuel = {"可燃冰","煤炭","石油"};
        String[] fish = {"黄鱼","乌贼","海蜇","带鱼"};




        // 创建获得 ship excel 表数据的类
        GetCrew getPersonId = new GetCrew();
        GetInvestigation getInvestigation = new GetInvestigation();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Crew> personList = getPersonId.getAllByExcel();
        List<utilClass.Investigation> investigations = getInvestigation.getAllByExcel();


        //开始插入数据
        for (int i=0;i<=10;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  i_oceanResources(regionId,resourceName,resourceType,description,workTime,upDataTime)" +
                        "VALUES(?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,regionId[random.nextInt(regionId.length)]);
                int j = random.nextInt(resourceType.length);
                if (resourceType[j].equals("燃料")) {
                    pstm.setString(2,fuel[random.nextInt(fuel.length)]);
                    pstm.setString(3,resourceType[j]);
                    pstm.setString(4,"一种燃料");
                } else {
                    pstm.setString(2,fish[random.nextInt(fish.length)]);
                    pstm.setString(3,resourceType[j]);
                    pstm.setString(4,"一种鱼类");
                }

                pstm.setString(5,randomNumber.generate(1,1)+"到"+randomNumber.generate(1,1)+"月");
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
