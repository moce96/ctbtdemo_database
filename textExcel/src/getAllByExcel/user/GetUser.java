package getAllByExcel.user;

import jxl.Sheet;
import jxl.Workbook;
import utilClass.user.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetUser {



    public List<User> getAllByExcel() {

        List<User> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\user.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String userName=rs.getCell(j++, i).getContents();
                    String userId=rs.getCell(j++, i).getContents();
                    String departmentId = rs.getCell(j++, i).getContents();
                    String announcementReading = rs.getCell(j++, i).getContents();

                    list.add(new User(userName,userId,departmentId,announcementReading));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }



}
