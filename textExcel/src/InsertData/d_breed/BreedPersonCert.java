package InsertData.d_breed;

import getAllByExcel.breed.GetBreedPerson;
import getAllByExcel.company.GetCompany;
import random.RandomDate;
import random.RandomNumber;
import utilClass.breed.BreedPerson;
import utilClass.company.Company;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class BreedPersonCert {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void breedPersonCert() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] certType = {"水产养殖证书","人员健康证"};
        String[] enclose = {"https://zhidao.baidu.com/question/624133961842653684.html"};


        // 创建获得  excel 表数据的类
        GetBreedPerson getBreedPerson = new GetBreedPerson();
        GetCompany getCompany = new GetCompany();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<BreedPerson> breedPersonList = getBreedPerson.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();




        //开始插入数据
        for (int i=0; i<=75; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_breedPersonCert(personId,certType,certId,startDate,endDate,enclosure,description)" +
                        "VALUES(?,?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(breedPersonList.size());
                pstm.setString(1,breedPersonList.get(j).personId);
                j = random.nextInt(certType.length);
                pstm.setString(2,certType[j]);
                pstm.setString(3,randomNumber.generate(1,5));
                pstm.setObject(4,randomDate.generateRandomDate("2017-01-01","2017-06-06"));
                pstm.setObject(5,randomDate.generateRandomDate("2019-01-01","2019-06-06"));
                pstm.setString(6,enclose[random.nextInt(enclose.length)]);
                pstm.setString(7,"时间期限内"+certType[j]+"有效");


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
