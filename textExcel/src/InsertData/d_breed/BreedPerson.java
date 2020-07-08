package InsertData.d_breed;

import getAllByExcel.breed.GetBreedPerson;
import getAllByExcel.company.GetCompany;
import random.*;
import random.RandomNumber;
import utilClass.company.Company;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class BreedPerson {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void breedPerson() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();


        // 创建获得  excel 表数据的类
        GetBreedPerson getBreedPerson = new GetBreedPerson();
        GetCompany getCompany = new GetCompany();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<utilClass.breed.BreedPerson> breedPersonList = getBreedPerson.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();




        //开始插入数据
        for (utilClass.breed.BreedPerson breedPerson : breedPersonList) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_breedPerson(personId,personName,contact,photo,companyId)" +
                        "VALUES(?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                pstm.setString(1,breedPerson.personId);
                pstm.setString(2,breedPerson.personName);
                pstm.setString(3,"1"+randomNumber.generate(1,10));
                pstm.setString(4,"暂无");
                int j = random.nextInt(companyList.size());
                pstm.setString(5,companyList.get(j).id);


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
