package InsertData.e_accident;

import getAllByExcel.accident.GetAccident;
import getAllByExcel.crew.GetCrew;
import random.MysqlRead;
import random.RandomDate;
import random.RandomNumber;
import utilClass.GenerateName;
import utilClass.accident.Accident;
import utilClass.crew.Crew;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class e_accidentPerson {
    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void accidentPerson(){
        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] state = {"优秀","良好","较差"};
        String[] heathState = {"健康状态优秀","健康状态良好","健康状态较差"};

        GetAccident getAccident = new GetAccident();
        GetCrew getCrew = new GetCrew();

        List<Accident> accidentList= getAccident.getAllByExcel();
        List<Crew> crewList=getCrew.getAllByExcel();

        //设置添加数据条数i
        for (int i = 0; i < 100; i++) {

            Accident accident = accidentList.get(random.nextInt(accidentList.size()));
            System.out.println(accident.accidentLoss);//只有人员伤亡的事故事件才会激发
            System.out.println(accident.accidentLoss.equals("人员伤亡"));
            if(accident.accidentLoss.equals("人员伤亡")) {
                try {
                    Class.forName(mysqlMessage[0]);
                    conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                    String sql = "INSERT INTO     e_accidentPerson(accidentId,personId,personName,emergContact,personState,healthState)" +
                            "VALUES(?,?,?,?,?,?)";

                    pstm = conn.prepareStatement(sql);

                    Crew crew = crewList.get(random.nextInt(crewList.size()));

                    pstm.setString(1, accident.accidentId);
                    pstm.setString(2, crew.id);
                    pstm.setString(3, crew.name);
                    pstm.setString(4, GenerateName.generate());
                    pstm.setString(5, state[random.nextInt(state.length)]);
                    pstm.setString(6, heathState[random.nextInt(heathState.length)]);



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

}
