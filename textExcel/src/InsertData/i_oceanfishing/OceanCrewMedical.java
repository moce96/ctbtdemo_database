package InsertData.i_oceanfishing;

import getAllByExcel.GetInvestigation;
import getAllByExcel.crew.GetCrew;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomNumber;
import utilClass.crew.Crew;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class OceanCrewMedical {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static final String[] mysqlMessage = MysqlRead.message;


    public static void oceanCrewMedical() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] regionId = {"10007"};
        String[] resourceType = {"燃料","鱼类"};
        String[] fuel = {"可燃冰","煤炭","石油"};
        String[] fish = {"黄鱼","乌贼","海蜇","带鱼"};

        String[] medicalType  = {"心理治疗","远程紧急医疗"};
        String[] description1  = {"心情郁结","思乡心切"};
        String[] description2  = {"呕吐不止","身体不适","发烧咳嗽"};
        String[] approach1 = {"心理谈话"};
        String[] approach2 = {"恢复建议","医药指导"};
        String[] effect  = {"优秀","良好","较差"};






        // 创建获得 ship excel 表数据的类
        GetCrew getPersonId = new GetCrew();
        GetShip getShip = new GetShip();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Crew> crewList = getPersonId.getAllByExcel();
        List<Ship> shipList = getShip.getAllByExcel();



        //开始插入数据
        for (int i=0;i<=100;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  i_oceanCrewMedical(crewId,date,shipId,medicalType,description,approach,effect)" +
                        "VALUES(?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(crewList.size());
                pstm.setString(1,crewList.get(j).id);
                pstm.setObject(2,randomDate.generateRandomDate("2019-01-01","2020-07-12"));
                pstm.setString(3,crewList.get(j).shipId);
                j = random.nextInt(medicalType.length);
                pstm.setString(4,medicalType[j]);
                if (medicalType[j].equals("心理治疗")) {
                    pstm.setString(5,description1[random.nextInt(description1.length)]);
                    pstm.setString(6,approach1[random.nextInt(approach1.length)]);
                } else  {
                    pstm.setString(5,description2[random.nextInt(description2.length)]);
                    pstm.setString(6,approach2[random.nextInt(approach2.length)]);
                }

                pstm.setString(7,effect[random.nextInt(effect.length)]);


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
