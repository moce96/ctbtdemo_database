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

public class AquaBaseCert {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void aquaBaseCert() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        String[] certType = {"生产经营取可证","养殖用地证明","环评手续"};
        String[] enclosure = {"https://m.quanjing.com/imgbuy/QJ9108236314.html"};


        // 创建获得  excel 表数据的类
        GetAquaBase getAquaBase = new GetAquaBase();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<AquaBase> aquaBaseCertList = getAquaBase.getAllByExcel();




        //开始插入数据
        for (int i=0; i<=10; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_aquaBaseCert(baseId,certType,certId,startDate,endDate,enclosure,description)" +
                        "VALUES(?,?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(aquaBaseCertList.size());
                pstm.setString(1,aquaBaseCertList.get(j).id);
                j = random.nextInt(certType.length);
                pstm.setString(2,certType[j]);
                pstm.setString(3,randomNumber.generate(1,5));
                pstm.setObject(4,randomDate.generateRandomDate("2017-01-01","2017-06-06"));
                pstm.setObject(5,randomDate.generateRandomDate("2019-01-01","2019-06-06"));
                pstm.setString(6,enclosure[random.nextInt(enclosure.length)]);
                pstm.setString(7,certType[j]+"可在有效期内使用");


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
