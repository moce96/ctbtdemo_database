package InsertData.d_breed;

import getAllByExcel.breed.GetAquaBase;
import getAllByExcel.breed.GetBreedPerson;
import getAllByExcel.company.GetCompany;
import getAllByExcel.k_oceanpasture.GetOceanPasture;
import random.*;
import random.RandomNumber;
import utilClass.breed.AquaBase;
import utilClass.breed.BreedPerson;
import utilClass.company.Company;
import utilClass.k_oceanpasture.OceanPasture;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class AquaBaseFoundation {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void aquaBaseFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] first = {"米","尤","拉","国","萨","利","曼","阿","南","飒"};
        String[] second = {"巴","马","拜","勒","科","法","云","莱","沙","霍"};
        String[] baseType = {"个人","单位"};
        String[] functionType = {"苗种场","源种场","养殖场"};
        String[] cultureType = {"大黄鱼","小黄鱼","带鱼","乌贼"};


        // 创建获得  excel 表数据的类
        GetOceanPasture getOceanPasture = new GetOceanPasture();
        GetAquaBase getAquaBase = new GetAquaBase();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<OceanPasture> oceanPastureList = getOceanPasture.getAllByExcel();
        List<AquaBase> aquaBaseList = getAquaBase.getAllByExcel();



        //开始插入数据
        for (AquaBase aquaBase : aquaBaseList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_aquaBaseFoundation(baseId,baseName,baseType,attributionObjectId,functionType,cultureType,startDate,regionId)" +
                        "VALUES(?,?,?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                pstm.setString(1,aquaBase.id);
                pstm.setString(2,first[random.nextInt(first.length)]+second[random.nextInt(second.length)]+"基地");
                pstm.setString(3,baseType[random.nextInt(baseType.length)]);
                int j = random.nextInt(oceanPastureList.size());
                pstm.setString(4,oceanPastureList.get(j).oceanPastureId);
                pstm.setString(5,aquaBase.baseType);
                pstm.setString(6,aquaBase.cultureType);
                pstm.setObject(7,randomDate.generateRandomDate("2019-01-01","2019-06-06"));
                pstm.setString(8,aquaBase.regionId);


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
