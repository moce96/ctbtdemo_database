package getAllByExcel.checkout;

import jxl.Sheet;
import jxl.Workbook;
import utilClass.LawEnforcement;
import utilClass.checkout.InspectItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetInspectItem {

    public List<InspectItem> getAllByExcel() {

        List<InspectItem> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\inspectitem.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String id=rs.getCell(j++, i).getContents();
                    String name=rs.getCell(j++, i).getContents();
                    String content = rs.getCell(j++, i).getContents();
                    String charge =  rs.getCell(j++, i).getContents();


                    list.add(new InspectItem(id,name,charge,content));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }




}
