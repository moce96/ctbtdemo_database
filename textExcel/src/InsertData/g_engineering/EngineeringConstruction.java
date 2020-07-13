package InsertData.g_engineering;

import getAllByExcel.GetRegion;
import getAllByExcel.engineering.GetEngineering;
import random.*;
import random.RandomNumber;
import utilClass.Region;
import utilClass.engineering.Engineering;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class EngineeringConstruction {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void engineeringConstruction() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] description = {"工期内如期完成","延迟工期完成","提前完成任务"};
        String[] eia = {"优","良","中","差"};
        String[] engiState = {"建设期","运营期"};


        // 创建获得  excel 表数据的类
        GetEngineering getEngineering = new GetEngineering();
        GetRegion getRegion = new GetRegion();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Engineering> engineeringList = getEngineering.getAllByExcel();
        List<Region> regionList = getRegion.getAllByExcel();




        //开始插入数据
        for (Engineering engineering : engineeringList) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  g_engineeringConstruction(engiName,engiType,engiId,regionId,description,eia,engiState,startDate,endDate,photo)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?)";





                pstm = conn.prepareStatement(sql);

                pstm.setString(1,engineering.engineeringName);
                pstm.setString(2,engineering.engineeringType);
                pstm.setString(3,engineering.engineeringId);
                pstm.setString(4,engineering.regionId);
                pstm.setString(5,description[random.nextInt(description.length)]);
                pstm.setString(6,eia[random.nextInt(eia.length)]);
                pstm.setString(7,engiState[random.nextInt(engiState.length)]);
                pstm.setObject(8,randomDate.generateRandomDate("2018-01-01","2018-06-06"));
                pstm.setObject(9,randomDate.generateRandomDate("2020-01-01","2020-06-06"));
                pstm.setString(10,"http://699pic.com/tupian-501224087.html");




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
