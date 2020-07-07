package getAllByExcel.checkout;

import jxl.Sheet;
import jxl.Workbook;
import utilClass.checkout.InspectApply;
import utilClass.checkout.InspectItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetInspectApply {

    public List<InspectApply> getAllByExcel() {

        List<InspectApply> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\inspectapply.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String id=rs.getCell(j++, i).getContents();
                    String two=rs.getCell(j++, i).getContents();
                    String three = rs.getCell(j++, i).getContents();
                    String four = rs.getCell(j++, i).getContents();
                    String five = rs.getCell(j++, i).getContents();
                    String six = rs.getCell(j++, i).getContents();



                    list.add(new InspectApply(id,two,three,four,five,six));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }



}
