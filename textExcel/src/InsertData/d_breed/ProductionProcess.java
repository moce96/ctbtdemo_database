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

public class ProductionProcess {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void productionProcess() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};
        String[] growSituation = {"优秀","良好","较差"};
        String[] habit = {"爱吃水草","喜爱嫩草","喜欢夜间活动"};


        // 创建获得  excel 表数据的类
        GetBreedPerson getBreedPerson = new GetBreedPerson();
        GetCompany getCompany = new GetCompany();
        GetAquaBase getAquaBase = new GetAquaBase();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<BreedPerson> breedPersonList = getBreedPerson.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();
        List<AquaBase> aquaBaseList = getAquaBase.getAllByExcel();




        //开始插入数据
        for (int i=0; i<=50; i++) {

            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_productionProcess(baseId,fryType,fryNumber,fryWeight,growSituation,sickRate,habit,checkDate,recordPerson)" +
                        "VALUES(?,?,?,?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(aquaBaseList.size());
                pstm.setString(1,aquaBaseList.get(j).id);
                pstm.setString(2,aquaBaseList.get(j).cultureType);
                pstm.setString(3,randomNumber.generate(1,5));
                pstm.setObject(4,randomNumber.generate(1,4));
                pstm.setString(5,growSituation[random.nextInt(growSituation.length)]);
                pstm.setString(6,randomNumber.generate(1,1)+"%");
                pstm.setString(7,habit[random.nextInt(habit.length)]);
                pstm.setObject(8,randomDate.generateRandomDate("2019-01-01","2019-12-12"));
                pstm.setString(9,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);

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
