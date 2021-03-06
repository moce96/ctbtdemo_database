package InsertData.l_project;

import getAllByExcel.project.GetProject;
import getAllByExcel.project.GetProjectSpecific;
import random.*;
import random.RandomNumber;
import utilClass.project.Project;
import utilClass.project.ProjectSpecific;

import java.sql.*;
import java.util.List;
import java.util.Random;

public class ProjectDetail {


    private static Connection conn = null;
    private static PreparedStatement pstm = null;
    private static ResultSet rt = null;
    private static String url = "jdbc:mysql://192.168.105.197:3306/ctbtdemo?serverTimezone=GMT%2B8&useSSL=false";
    private static final String[] mysqlMessage = MysqlRead.message;
    private static String password = "123456";

    public static void projectDetail() {

        Random random = new Random();
        RandomDate randomDate = new RandomDate();
        RandomNumber randomNumber = new RandomNumber();

        String[] content = {"贪污受贿","证书不全","在逃嫌疑人"};
        String[] familyname = {"赵","钱","孙","李","周","吴","郑","王","宋","陈","白","黄","梁","燕","邱","仇","牟","潘"};
        String[] firstname = {"伟","芳","秀英","娜","敏","静","立","丽","强","军","磊","刚","平","子良","建国","建军","国林","国梁","鸿蒙","浩宇","轩","艳","枫","鸣","浮萍","二狗","爱国"};
        //2-establishBasis 立项依据
        String[] establishBasis = {"《2020年渔业渔政工作要点》","《人工鱼礁建设项目验收工作规范》","《国家级海洋牧场示范区管理工作规范》"};

        //3-projectContent 项目内容
        String[] projectContent = {"渔业工作","人工鱼礁建设","海洋保护区建设"};

        //4-goal 绩效目标
        String[] goal = {"快速取得盈利","争取短时间内实现收支平衡","争取长期回报"};

        //5-plan
        String[] plan = {"见附件"};

        //6-calculationBasis
        String[] calculationBasis = {"暂无"};




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
                String sql = "INSERT INTO  l_projectDetail(projectId,establishBasis,projectContent,goal,plan,calculationBasis)" +
                        "VALUES(?,?,?,?,?,?)";



                pstm = conn.prepareStatement(sql);


                pstm.setString(1,project.projectId);
                pstm.setString(2,establishBasis[random.nextInt(establishBasis.length)]);
                pstm.setString(3,projectContent[random.nextInt(projectContent.length)]);
                pstm.setString(4,goal[random.nextInt(goal.length)]);
                pstm.setString(5,plan[random.nextInt(plan.length)]);
                pstm.setString(6,calculationBasis[random.nextInt(calculationBasis.length)]);


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
