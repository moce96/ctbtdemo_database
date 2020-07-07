package InsertData.b_Crew;

import getAllByExcel.company.GetCompany;
import getAllByExcel.crew.GetJobType;
import getAllByExcel.ship.GetEquipmentType;
import getAllByExcel.crew.GetCrew;
import getAllByExcel.ship.GetShip;
import random.RandomDate;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.crew.JobType;
import utilClass.ship.EquipmentType;
import utilClass.crew.Crew;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class CrewResume {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void crewResume() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[]jobNature = {"正式员工","临时工","实习生"};



        // 创建获得 ship excel 表数据的类
        GetShip getShipExcel = new GetShip();
        GetCrew getPersonId = new GetCrew();
        GetEquipmentType getEquipmentType = new GetEquipmentType();
        GetCompany getCompany = new GetCompany();
        GetJobType getJobType = new GetJobType();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<Crew> personList = getPersonId.getAllByExcel();
        List<EquipmentType> equipmentTypes = getEquipmentType.getAllByExcel();
        List<Company> companies = getCompany.getAllByExcel();
        List<JobType> jobTypes = getJobType.getAllByExcel();

        //开始插入数据
        for (Crew person : personList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO b_crewResume(personId,shipId,jobTypeId,companyId,jobNature,startDate,endDate)" +
                        "VALUES(?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(ships.size());
                pstm.setString(1,person.id);
                pstm.setString(2,person.shipId);
                j = random.nextInt(jobTypes.size());
                pstm.setString(3,jobTypes.get(j).jobTypeId);
                j = random.nextInt(companies.size());
                pstm.setString(4,companies.get(j).id);
                pstm.setString(5,jobNature[random.nextInt(jobNature.length)]);
                pstm.setObject(6,person.startTime);
                pstm.setObject(7,null);


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
