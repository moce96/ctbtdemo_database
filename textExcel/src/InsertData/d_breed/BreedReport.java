package InsertData.d_breed;

import getAllByExcel.breed.GetAquaBase;
import random.RandomDate;
import random.RandomNumber;
import utilClass.breed.AquaBase;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class BreedReport {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void breedReport() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] breedType = {"大黄鱼","小黄鱼","乌贼","带鱼"};
        String[] breedModel = {"散养","集中养"};
        String[] auditSituation = {"已审批","未审批"};
        String[] situation = {"推广效果优秀","推广效果一般","推广效果较差"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};



        // 创建获得  excel 表数据的类
        GetAquaBase getAquaBase = new GetAquaBase();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<AquaBase> aquaBaseList = getAquaBase.getAllByExcel();




        //开始插入数据
        for (int i=0; i<=50; i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_breedReport(reportId,baseId,breedType,breedNumber,breedModel,reportDate,reportPerson,contact,auditSituation,situation)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(aquaBaseList.size());
                while ((!aquaBaseList.get(j).baseType.equals("养殖场")) && (!aquaBaseList.get(j).baseType.equals("苗种场"))) {
                    j = random.nextInt(aquaBaseList.size());
                }
                pstm.setString(1,randomNumber.generate(1,5));
                pstm.setString(2,aquaBaseList.get(j).id);
                pstm.setString(3,breedType[random.nextInt(breedType.length)]);
                pstm.setString(4,randomNumber.generate(1,5));
                pstm.setString(5,breedModel[random.nextInt(breedModel.length)]);
                pstm.setObject(6,randomDate.generateRandomDate("2019-01-01","2019-06-06"));
                pstm.setString(7,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(8,"1"+randomNumber.generate(1,10));
                pstm.setString(9,auditSituation[random.nextInt(auditSituation.length)]);
                pstm.setString(10,situation[random.nextInt(situation.length)]);


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
