package InsertData.b_Crew;

import getAllByExcel.company.GetCompany;
import getAllByExcel.GetInvestigation;
import getAllByExcel.GetLawEnforcement;
import getAllByExcel.crew.GetCrew;
import random.RandomDate;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.Investigation;
import utilClass.LawEnforcement;
import utilClass.crew.Crew;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class CrewLawResult {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void crewCredit() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] scoreChange = {"+","-"};
        String[] eventLoc = {"30.19,121.94","30.22,121.55","30.33,122.34","30.22,123.11","30.36,122.56","30.17,121.93","30.25,121.65","30.12,122.25","30.25,123.09","30.26,122.44","30.39,121.94","30.22,121.25","30.13,122.34","30.22,123.21","30.16,122.56"};
        String[] description = {"偷税漏税","强制员工加班","强制员工冒险作业","非法集资"};
        String[] handleResult = {"成功处理","成功处理","成功处理","未完全处理"};






        // 创建获得 excel 表数据的类
        GetInvestigation getInvestigation = new GetInvestigation();
        GetLawEnforcement getLawEnforcement = new GetLawEnforcement();
        GetCompany getCompany = new GetCompany();
        GetCrew getPersonId = new GetCrew();

        // 用从表里获得的数据  生成列表  用于填充数据库
        List<Investigation> investigations = getInvestigation.getAllByExcel();
        List<LawEnforcement> lawEnforcements = getLawEnforcement.getAllByExcel();
        List<Company> companies = getCompany.getAllByExcel();
        List<Crew> personList = getPersonId.getAllByExcel();

        //开始插入数据
        for (LawEnforcement lawEnforcement : lawEnforcements) {


            if (lawEnforcement.object.equals("company")) {

                try {
                    Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                    String sql = "INSERT INTO  b_crewLawResult(personId,enforceLawId,scoreChange,eventDate,eventLoc,description,handleResult,enforceLawPerson,enclosure,enforceLawPhoto)" +
                            "VALUES(?,?,?,?,?,?,?,?,?,?)";

                    pstm = conn.prepareStatement(sql);

                    int j = random.nextInt(personList.size());
                    pstm.setString(1,personList.get(j).id);
                    pstm.setString(2,lawEnforcement.id);
                    pstm.setString(3,scoreChange[random.nextInt(scoreChange.length)]+randomNumber.generate(1,1));
                    pstm.setObject(4,randomDate.generateRandomDate("2018-01-01","2019-01-01"));
                    pstm.setString(5,eventLoc[random.nextInt(eventLoc.length)]);
                    pstm.setString(6,description[random.nextInt(description.length)]);
                    pstm.setString(7,handleResult[random.nextInt(handleResult.length)]);
                    j = random.nextInt(personList.size());
                    pstm.setString(8,personList.get(j).name);
                    pstm.setString(9,"暂无");
                    pstm.setString(10,"暂无");

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


}
