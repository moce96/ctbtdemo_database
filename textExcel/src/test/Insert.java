package test;

import java.sql.*;
import java.util.List;

public class Insert {



        private static Connection conn = null;
        private static PreparedStatement pstm = null;
        private static ResultSet rt = null;
        private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
        private static final String[] mysqlMessage = MysqlRead.message;
        private static String password = "123456";

        public void insert(List<Student> students) {

            for (Student student : students ) {
                try {
                    Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                    String sql = "INSERT INTO student(id,s_name,age,address)" +
                            "VALUES(?,?,?,?)";


                    pstm = conn.prepareStatement(sql);

                    pstm.setInt(1,student.id);
                    pstm.setString(2,student.s_name);
                    pstm.setString(3,student.age);
                    pstm.setString(4,student.address);



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

