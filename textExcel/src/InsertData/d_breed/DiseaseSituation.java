package InsertData.d_breed;

import getAllByExcel.breed.GetMonitorPoint;
import random.*;
import random.RandomNumber;
import utilClass.breed.MonitorPoint;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class DiseaseSituation {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void diseaseSituation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] healthCondition = {"健康","健康","健康","健康","不健康"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};
        String[] diseaseType = {"链球菌感染","镰刀菌感染","气单胞菌感染"};
        String[] fishType = {"大黄鱼","小黄鱼","乌贼","带鱼"};

        // 创建获得  excel 表数据的类
        GetMonitorPoint getMonitorPoint = new GetMonitorPoint();


        // 用从表里获得的数据  生成列表  用于填充数据库
        List<MonitorPoint> monitorPointList = getMonitorPoint.getAllByExcel();




        //开始插入数据
        for (int i=0; i<100;i++) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO d_diseaseSituation(monitorPointId,diseaseId,diseaseName,diseaseType,diseaseRate,shipSpeed,fishType,fishNumber,reportDate,reportPerson,contact)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(monitorPointList.size());
                pstm.setString(1,monitorPointList.get(j).id);
                pstm.setString(2,randomNumber.generate(1,5));
                j = random.nextInt(diseaseType.length);
                pstm.setString(3,diseaseType[j]+randomNumber.generate(2,3));
                pstm.setString(4,diseaseType[j]);
                pstm.setString(5,randomNumber.generate(1,1)+"%");
                pstm.setString(6,randomNumber.generate(1,2));
                pstm.setString(7,fishType[random.nextInt(fishType.length)]);
                pstm.setString(8,randomNumber.generate(1,4));
                pstm.setObject(9,randomDate.generateRandomDate("2019-01-01","2019-12-12"));
                pstm.setString(10,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
                pstm.setString(11,"1"+randomNumber.generate(1,10));


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
