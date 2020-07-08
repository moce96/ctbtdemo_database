package InsertData.c_company;

import getAllByExcel.company.GetCompany;
import getAllByExcel.ship.GetEquipmentType;
import getAllByExcel.crew.GetCrew;
import getAllByExcel.ship.GetShip;
import random.*;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.ship.EquipmentType;
import utilClass.crew.Crew;
import utilClass.ship.Ship;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class CompanyFoundation {



    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void companyFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] description = {"公司情况较差","公司情况良好","公司情况优秀"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};





        // 创建获得 ship excel 表数据的类
        GetShip getShipExcel = new GetShip();
        GetCrew getPersonId = new GetCrew();
        GetEquipmentType getEquipmentType = new GetEquipmentType();
        GetCompany getCompany = new GetCompany();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<Crew> personList = getPersonId.getAllByExcel();
        List<EquipmentType> equipmentTypes = getEquipmentType.getAllByExcel();
        List<Company> companies = getCompany.getAllByExcel();

        //开始插入数据
        for (Company company : companies) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO c_companyFoundation(companyId,companyName,superiorCompanyId,description,contactPhone,responsiblePerson)" +
                        "VALUES(?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,company.id);
                pstm.setString(2,company.name);
                pstm.setString(3,company.superiorId);
                pstm.setString(4,description[random.nextInt(description.length)]);
                pstm.setString(5,"1"+randomNumber.generate(1,10));
                pstm.setString(6,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);




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
