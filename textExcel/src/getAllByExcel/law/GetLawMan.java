package getAllByExcel.law;

import utilClass.law.LawMan;
import jxl.Sheet;
import jxl.Workbook;
import utilClass.law.LawShip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetLawMan {



    public List<LawMan> getAllByExcel() {

        List<LawMan> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\lawman.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String id=rs.getCell(j++, i).getContents();
                    String name=rs.getCell(j++, i).getContents();
                    String personId = rs.getCell(j++, i).getContents();

                    list.add(new LawMan(id,name,personId));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }


}
