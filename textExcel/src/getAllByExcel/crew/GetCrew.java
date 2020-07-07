package getAllByExcel.crew;

import jxl.Sheet;
import jxl.Workbook;
import random.StringToDate;
import utilClass.crew.Crew;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetCrew {


    public List<Crew> getAllByExcel() {

        List<Crew> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\crew.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String name=rs.getCell(j++, i).getContents();
                    String id=rs.getCell(j++, i).getContents();
                    String bloodType = rs.getCell(j++, i).getContents();
                    String shipId = rs.getCell(j++, i).getContents();
                    Date startTime = StringToDate.function(rs.getCell(j++, i).getContents());

//                    System.out.println(name+id);

                    list.add(new Crew(name,id,bloodType,shipId,startTime));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }

}
