package InsertData.k_oceanpasture;

import getAllByExcel.company.GetCompany;
import getAllByExcel.k_oceanpasture.GetOceanPasture;
import random.RandomDate;
import random.RandomJson;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.k_oceanpasture.OceanPasture;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class OceanPastureEvent {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void oceanPastureEvent() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();
        RandomJson randomJson = new RandomJson();

       String[] content = {"资产投入","撤资","入股"};


        // 创建获得 ship excel 表数据的类
        GetOceanPasture getOceanPasture = new GetOceanPasture();
        GetCompany getCompany = new GetCompany();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<OceanPasture> oceanPastureList = getOceanPasture.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();


        //开始插入数据
        for (int i = 0; i <= 20; i++) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(url,user,password);
                String sql = "INSERT INTO  k_oceanPastureEvent(pastureId,eventId,companyName,content,date,veido)" +
                        "VALUES(?,?,?,?,?,?)";


                pstm = conn.prepareStatement(sql);

                int j = random.nextInt(oceanPastureList.size());
                pstm.setString(1,oceanPastureList.get(j).oceanPastureId);
                pstm.setString(2,randomNumber.generate(1,4));
                j = random.nextInt(companyList.size());
                pstm.setString(3,companyList.get(j).name);
                pstm.setString(4,content[random.nextInt(content.length)]);
                pstm.setObject(5,randomDate.generateRandomDate("2018-01-01","2019-12-12"));
                pstm.setString(6,"ahttps://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583766288777&di=b66d856c712162a700d9ed0d818c525b&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180928%2Fed8b6ff24fca4b0893f53eb277527789.jpg");


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