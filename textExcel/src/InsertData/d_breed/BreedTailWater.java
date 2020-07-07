package InsertData.d_breed;

import getAllByExcel.breed.GetAquaBase;
import getAllByExcel.breed.GetBreedPerson;
import getAllByExcel.company.GetCompany;
import random.RandomDate;
import random.RandomNumber;
import utilClass.breed.AquaBase;
import utilClass.breed.BreedPerson;
import utilClass.company.Company;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class BreedTailWater {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void breedTailWater() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] tailWaterDischarge = {"20","30","40","50"};
        String[] excessiveSituation = {"未超标","超标"};


        // 创建获得  excel 表数据的类
        GetBreedPerson getBreedPerson = new GetBreedPerson();
        GetCompany getCompany = new GetCompany();
        GetAquaBase getAquaBase = new GetAquaBase();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<BreedPerson> breedPersonList = getBreedPerson.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();
        List<AquaBase> aquaBaseList = getAquaBase.getAllByExcel();




        //开始插入数据
        for (int i=0; i<=25; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_breedTailWater(baseId,date,tailWaterDischarge,excessiveSituation)" +
                        "VALUES(?,?,?,?)";


                pstm = conn.prepareStatement(sql);


                int j = random.nextInt(aquaBaseList.size());
                pstm.setString(1,aquaBaseList.get(j).id);
                pstm.setObject(2,randomDate.generateRandomDate("2019-01-01","2019-12-12"));
                pstm.setString(3,tailWaterDischarge[random.nextInt(tailWaterDischarge.length)]);
                pstm.setString(4,excessiveSituation[random.nextInt(excessiveSituation.length)]);

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
