package getAllByExcel.project;

import jxl.Sheet;
import jxl.Workbook;
import random.StringToDate;
import utilClass.Investigation;
import utilClass.project.Project;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetProject {



    public List<Project> getAllByExcel() {

        List<Project> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\project.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String id1=rs.getCell(j++, i).getContents();
                    String name=rs.getCell(j++, i).getContents();
                    String id2 = rs.getCell(j++, i).getContents();
                    Date reportTime = StringToDate.function(rs.getCell(j++, i).getContents());


                    list.add(new Project(id1,name,id2,reportTime));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }



}
