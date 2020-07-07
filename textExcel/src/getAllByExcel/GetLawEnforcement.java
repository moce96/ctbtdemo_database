package getAllByExcel;

import jxl.Sheet;
import jxl.Workbook;
import utilClass.Investigation;
import utilClass.LawEnforcement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetLawEnforcement {



    public List<LawEnforcement> getAllByExcel() {

        List<LawEnforcement> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\lawenforcement.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String id=rs.getCell(j++, i).getContents();
                    String object=rs.getCell(j++, i).getContents();


                    list.add(new LawEnforcement(id,object));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }



}
