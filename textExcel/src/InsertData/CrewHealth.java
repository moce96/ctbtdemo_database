package InsertData;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import getAllByExcel.GetEquipmentType;
import getAllByExcel.GetPersonId;
import getAllByExcel.GetShipExcel;
import random.MysqlRead;
import random.*;
import random.RandomNumber;
import utilClass.EquipmentType;
import utilClass.Person;
import utilClass.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;
import random.MysqlRead;

public class CrewHealth {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void crewHealth() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] medicalHistory = {"糖尿病","甲亢","无"};




        // 创建获得 ship excel 表数据的类
        GetShipExcel getShipExcel = new GetShipExcel();
        GetPersonId getPersonId = new GetPersonId();
        GetEquipmentType getEquipmentType = new GetEquipmentType();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<Person> personList = getPersonId.getAllByExcel();
        List<EquipmentType> equipmentTypes = getEquipmentType.getAllByExcel();

        //开始插入数据
        for (int i=0; i<=30; i++) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO z_crewHealth(personId,medicalHistory,treatDate,bloodType)" +
                        "VALUES(?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(personList.size());
                pstm.setString(1,personList.get(j).id);
                pstm.setString(2,medicalHistory[random.nextInt(medicalHistory.length)]);
                pstm.setObject(3,randomDate.generateRandomDate("2017-01-01","2017-12-12"));
                pstm.setString(4,personList.get(j).bloodType);

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
