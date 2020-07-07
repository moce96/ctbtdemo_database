package InsertData.s_ship;

import getAllByExcel.company.GetCompany;
import getAllByExcel.ship.*;
import random.MysqlRead;
import random.RandomDate;
import random.RandomNumber;
import utilClass.company.Company;
import utilClass.ship.*;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ShipFoundation {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static String password = "123456";

    private static final String[] mysqlMessage = MysqlRead.message;





    public static void  shipFoundation() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] shipTypeId = {"1","2","3","4","5","6"};
        String[] department = {"远洋部","工程部","海钓部","执法部","休闲渔船部门","国内渔船部门"};
        String[] county = {"普陀","定海","江东","江北","路桥","黄岩","连云","海州","盐都","大丰","龙华","美兰","芝罘","莱山","文登","环翠","中山","金州","连山","龙岗","浦东","","连山","龙岗","浦东"};
        String[] town = {"玉山","狮山","杨舍","金港","长安","虎门","花桥","锦丰","柳市","石排","张浦","龙湖","滨江","华士"};
        String[] village = {"华西","南山","长江","九星","西王","航民","永联","红嘴","南金","西杨","富山","水磨","杜杉","坊巷"};
        String[] shipyard = {"大宇造船","三星重工","现代重工","三湖重工","大岛造船","新时代造船","扬子江船业","江南造船"};
        String[] shipyardLoc = {"烟台市青岛大街2-1","宁波市北仑区港口路18号","上海市浦东新区郭守敬路498号上海浦东软件园10号楼1楼102","上海市静安区延平路121号","上海市浦东大道2198号附近","泰州市靖江市新港大道南侧","江阴市东兴镇联谊路1号","上海市兴奔路66号"};
        String[] material = {"碳素钢","合金钢","铸铁","船用钢"};
        String[] hostModel = {"ABT-51","CC-900","WD-55","WD-68"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍"};
        String[] shipPhoto = {"https://blog.csdn.net/java20131115/article/details/23759427/","https://blog.csdn.net/cao812755156/article/details/89598410","https://blog.csdn.net/bigfacemiao/article/details/83830201","https://blog.csdn.net/sinat_36454672/article/details/97100707"};



        // 创建获得 ship excel 表数据的类
        GetShip getShipExcel = new GetShip();
        GetShipState getShipState = new GetShipState();
        GetShipAttribute getShipAttribute = new GetShipAttribute();
        GetWorkType getWorkType = new GetWorkType();
        GetShipType getShipType = new GetShipType();
        GetCompany getCompany = new GetCompany();

        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Ship> ships = getShipExcel.getAllByExcel();
        List<ShipState> shipStates = getShipState.getAllByExcel();
        List<ShipAttribute> shipAttributes = getShipAttribute.getAllByExcel();
        List<WorkType> workTypes = getWorkType.getAllByExcel();
        List<ShipType> shipTypes = getShipType.getAllByExcel();
        List<Company> companyList = getCompany.getAllByExcel();

        //开始插入数据
        for (Ship ship : ships ) {
            try {
                Class.forName(mysqlMessage[0]);
                conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
//                String sql1 = "INSERT INTO  s_shipFoundation(shipTypeId,shipAttributeId,workTypeId,department,province,city,buildYear,shipyard,shipyardLoc,material,shipLength,shipWidth,shipDepth,windResistRate,tons,power,hostModel,emergencyContact,emergPhone,emergMobilePhone,emergSatellitePhone,shipPhoto,shipStateId,shipLicence)" +
//                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) where shipId = ship.shipId";
                String sql2 = "UPDATE s_shipFoundation set shipTypeId=?,shipAttributeId=?,workTypeId=?,department=?,province=?,city=?,buildYear=?,shipyard=?,shipyardLoc=?,material=?,shipLength=?,shipWidth=?,shipDepth=?,windResistRate=?,tons=?,power=?,hostModel=?,emergencyContact=?,emergPhone=?,emergMobilePhone=?,emergSatellitePhone=?,shipPhoto=?,shipStateId=?,shipLicence=?" +
                        "where shipId = ?";

                String sql3 = "UPDATE s_shipFoundation set companyId = ? WHERE shipId = ?";



                pstm = conn.prepareStatement(sql3);

                //  sql1
//                pstm.setString(1,ship.shipId);
//                int j = random.nextInt(shipTypes.size());
//                pstm.setString(2,shipTypes.get(j).id);
//                j = random.nextInt(shipAttributes.size());
//                pstm.setString(3,shipAttributes.get(j).id);
//                j = random.nextInt(workTypes.size());
//                pstm.setString(4,workTypes.get(j).id);
//                pstm.setString(5,ship.shipName);
//                pstm.setString(6,department[random.nextInt(department.length)]);
//                pstm.setString(7,"浙江省");
//                pstm.setString(8,"舟山市");
//                pstm.setString(9,county[random.nextInt(county.length)]);
//                pstm.setString(10,town[random.nextInt(town.length)]);
//                pstm.setString(11,village[random.nextInt(village.length)]);
//                pstm.setObject(12,randomDate.generateRandomDate("2016-01-01","2016-06-01"));
//                pstm.setString(13,shipyard[random.nextInt(shipyard.length)]);
//                pstm.setString(14,shipyardLoc[random.nextInt(shipyardLoc.length)]);
//                pstm.setString(15,material[random.nextInt(material.length)]);
//                pstm.setDouble(16,Double.valueOf(random.nextInt(17)+1));
//                pstm.setDouble(17,Double.valueOf(random.nextInt(12000-400+1)+400));
//                pstm.setDouble(18,Double.valueOf(random.nextInt(3000-400+1)+1));
//                pstm.setDouble(19,Double.valueOf(random.nextInt(17)+1));
//                pstm.setDouble(20,Double.valueOf(random.nextInt(12000-400+1)+400));
//                pstm.setDouble(21,Double.valueOf(random.nextInt(3000-400+1)+1));
//                pstm.setString(22,hostModel[random.nextInt(hostModel.length)]);
//                pstm.setString(23,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
//                pstm.setString(24,"1"+randomNumber.generate(1,10));
//                pstm.setString(25,"1"+randomNumber.generate(1,10));
//                pstm.setString(26,"1"+randomNumber.generate(1,10));
//                pstm.setString(27,shipPhoto[random.nextInt(shipPhoto.length)]);
//                j = random.nextInt(shipStates.size());
//                pstm.setString(28,shipStates.get(j).shipStateId);
//                pstm.setString(29,randomNumber.generate(2,3)+randomNumber.generate(1,4));

                //sql2
//                int j = random.nextInt(shipTypeId.length);
//                pstm.setString(1,shipTypeId[j]);
//                int k = random.nextInt(shipAttributes.size());
//                pstm.setString(2,shipAttributes.get(k).id);
//                k = random.nextInt(workTypes.size());
//                pstm.setString(3,workTypes.get(k).id);
//                pstm.setString(4,department[j]);
//                pstm.setString(5,"浙江省");
//                pstm.setString(6,"舟山市");
//                pstm.setObject(7,randomDate.generateRandomDate("2015-01-01","2020-01-01"));
//                pstm.setString(8,shipyard[random.nextInt(shipyard.length)]);
//                pstm.setString(9,shipyardLoc[random.nextInt(shipyardLoc.length)]);
//                pstm.setString(10,material[random.nextInt(material.length)]);
//                pstm.setDouble(11,Double.valueOf(random.nextInt(17)+1));
//                pstm.setDouble(12,Double.valueOf(random.nextInt(12000-400+1)+400));
//                pstm.setDouble(13,Double.valueOf(random.nextInt(3000-400+1)+1));
//                pstm.setDouble(14,Double.valueOf(random.nextInt(17)+1));
//                pstm.setDouble(15,Double.valueOf(random.nextInt(12000-400+1)+400));
//                pstm.setDouble(16,Double.valueOf(random.nextInt(3000-400+1)+1));
//                pstm.setString(17,hostModel[random.nextInt(hostModel.length)]);
//                pstm.setString(18,familyname[random.nextInt(familyname.length)]+firstname[random.nextInt(firstname.length)]);
//                pstm.setString(19,"1"+randomNumber.generate(1,10));
//                pstm.setString(20,"1"+randomNumber.generate(1,10));
//                pstm.setString(21,"1"+randomNumber.generate(1,10));
//                pstm.setString(22,shipPhoto[random.nextInt(shipPhoto.length)]);
//                j = random.nextInt(shipStates.size());
//                pstm.setString(23,shipStates.get(j).shipStateId);
//                pstm.setString(24,randomNumber.generate(2,3)+randomNumber.generate(1,4));
//
//                // 设置where shipId=?
//                pstm.setString(25,ship.shipId);


                //sql3
                int j = random.nextInt(companyList.size());
                pstm.setString(1,companyList.get(j).id);
                pstm.setString(2,ship.shipId);


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


