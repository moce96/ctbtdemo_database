package getAllByExcel.ship;

import jxl.Sheet;
import jxl.Workbook;
import utilClass.ship.ShipAttribute;
import utilClass.ship.ShipState;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetShipAttribute {




    public List<ShipAttribute> getAllByExcel() {

        List<ShipAttribute> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\shipattribute.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String id=rs.getCell(j++, i).getContents();
                    String description=rs.getCell(j++, i).getContents();

//                    System.out.println(name+id);

                    list.add(new ShipAttribute(id,description));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }




}
