package InsertData.l_project;

import getAllByExcel.project.GetProject;
import getAllByExcel.project.GetProjectSpecific;
import random.RandomDate;
import random.RandomNumber;
import utilClass.project.Project;
import utilClass.project.ProjectSpecific;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class PerformanceGoal {

    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void performanceGoal() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] content = {"贪污受贿","证书不全","在逃嫌疑人"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};
        //3-year
        String[] year = {"2017","2018","2019","2020"};

        //4-expectedOutputQuantityTarget 预期产出数量目标
        String[] expectedOutputQuantityTarget = {"50","100","200","500"};

        //5-expectedOutputQualityTarget 预期产出质量目标
        String[] expectedOutputQualityTarget = {"优秀","良好","中等"};

        //6-expectedOutputProgressTarget 预期产出进度目标
        String[] expectedOutputProgressTarget = {"30%","50%","70%","100%"};

        //7-expectedSocialBenefit 预期社会效益
        String[] expectedSocialBenefit = {"优秀","良好","中等"};

        //8-expectedEconomicBenefit 预期经济效益
        String[] expectedEconomicBenefit = {"50","100","200","500"};

        //9-expectedEcologicalBenefit 预期生态效益
        String[] expectedEcologicalBenefit = {"优秀","良好","中等"};

        //10-sustainableDevelopment 可持续发展影响
        String[] sustainableDevelopment = {"可持续发展性好","可持续发展性一般","可持续发展性较差"};

        //11-serviceSatisfactory 服务对象获受益者满意度
        String[] serviceSatisfactory = {"非常满意","基本满意","较满意"};






        // 创建获得 ship excel 表数据的类
        GetProject getProject = new GetProject();
        GetProjectSpecific getProjectSpecific = new GetProjectSpecific();


        // 用从表里获得的数据  生成ship列表  用于填充数据库
        List<Project> projectList = getProject.getAllByExcel();
        List<ProjectSpecific> projectSpecificList = getProjectSpecific.getAllByExcel();

        //开始插入数据
        for (Project project : projectList) {

            try {
                Class.forName(mysqlMessage[0]);
conn = DriverManager.getConnection(mysqlMessage[1], mysqlMessage[2], mysqlMessage[3]);
                String sql = "INSERT INTO  l_performanceGoal(projectId,serialNumber,year,expectedOutputQuantityTarget,expectedOutputQualityTarget,expectedOutputProgressTarget,expectedSocialBenefit,expectedEconomicBenefit,expectedEcologicalBenefit,sustainableDevelopment,serviceSatisfactory)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);

                pstm.setString(1,project.projectId);
                pstm.setString(2,randomNumber.generate(1,3));
                pstm.setObject(3,year[random.nextInt(year.length)]);
                pstm.setString(4,expectedOutputQuantityTarget[random.nextInt(expectedOutputQuantityTarget.length)]);
                pstm.setObject(5,expectedOutputQualityTarget[random.nextInt(expectedOutputQualityTarget.length)]);
                pstm.setObject(6,expectedOutputProgressTarget[random.nextInt(expectedOutputProgressTarget.length)]);
                pstm.setString(7,expectedSocialBenefit[random.nextInt(expectedSocialBenefit.length)]);
                pstm.setString(8,expectedEconomicBenefit[random.nextInt(expectedEconomicBenefit.length)]);
                pstm.setString(9,expectedEcologicalBenefit[random.nextInt(expectedEcologicalBenefit.length)]);
                pstm.setString(10,sustainableDevelopment[random.nextInt(sustainableDevelopment.length)]);
                pstm.setString(11,serviceSatisfactory[random.nextInt(serviceSatisfactory.length)]);


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
