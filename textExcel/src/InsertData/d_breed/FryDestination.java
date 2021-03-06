package InsertData.d_breed;

import getAllByExcel.breed.GetAquaBase;
import getAllByExcel.breed.GetBreedPerson;
import getAllByExcel.company.GetCompany;
import random.*;
import random.RandomNumber;
import utilClass.breed.AquaBase;
import utilClass.breed.BreedPerson;
import utilClass.company.Company;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class FryDestination {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void fryDestination() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] healthCondition = {"健康","健康","健康","健康","不健康"};
        String[] destinationArea = {"浙江","江苏"};
        String[] sourceCompany = {"川聚力","全安","云非","流宁"};



        // 创建获得  excel 表数据的类
        GetBreedPerson getBreedPerson = new GetBreedPerson();
        GetCompany getCompany = new GetCompany();
        GetAquaBase getAquaBase = new GetAquaBase();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<BreedPerson> breedPersonList = getBreedPerson.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();
        List<AquaBase> aquaBaseList = getAquaBase.getAllByExcel();




        //开始插入数据
        for (int i=0; i<=30; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_fryDestination(baseId,fryType,fryNumber,destinationArea,destinationCompany,contact,Date)" +
                        "VALUES(?,?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                //获得苗种场的id
                int j = random.nextInt(aquaBaseList.size());
                while (!aquaBaseList.get(j).baseType.equals("苗种场")) {
                    j = random.nextInt(aquaBaseList.size());
                }

                pstm.setString(1,aquaBaseList.get(j).id);
                pstm.setString(2,aquaBaseList.get(j).cultureType);
                pstm.setString(3,randomNumber.generate(1,4));
                pstm.setString(4,destinationArea[random.nextInt(destinationArea.length)]);
                j = random.nextInt(companyList.size());
                pstm.setString(5,companyList.get(j).name);
                pstm.setString(6,"1"+randomNumber.generate(1,10));
                pstm.setObject(7,randomDate.generateRandomDate("2019-01-01","2019-12-12"));

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
