package InsertData.b_Crew;

import getAllByExcel.crew.GetCrewCertType;
import getAllByExcel.crew.GetCrew;
import random.RandomDate;
import random.RandomNumber;
import utilClass.crew.CrewCertType;
import utilClass.crew.Crew;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class CrewCert {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void crewCert() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();




        // 创建获得 ship excel 表数据的类
        GetCrew getPersonId = new GetCrew();
        GetCrewCertType getCrewCertType = new GetCrewCertType();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Crew> personList = getPersonId.getAllByExcel();
        List<CrewCertType> crewCertTypes = getCrewCertType.getAllByExcel();

        //开始插入数据
        for (int i = 0; i<=700; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO b_crewCert(personId,certType,certTypeId,certName,certId,certAppDate,certEndDate,enclosure)" +
                        "VALUES(?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(personList.size());
                pstm.setString(1,personList.get(j).id);
                j = random.nextInt(crewCertTypes.size());
                pstm.setString(2,crewCertTypes.get(j).certType);
                pstm.setString(3,crewCertTypes.get(j).certTypeId);
                pstm.setString(4,crewCertTypes.get(j).certName);
                pstm.setString(5,randomNumber.generate(1,5));
                pstm.setObject(6,randomDate.generateRandomDate("2018-01-01","2018-06-06"));
                pstm.setObject(7,randomDate.generateRandomDate("2019-01-01","2019-06-06"));
                pstm.setString(8,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583735402185&di=43f6aac05b701b5605edcfd877d431d0&imgtype=0&src=http%3A%2F%2Fpic30.nipic.com%2F20130616%2F12826599_115647497000_2.jpg");

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
