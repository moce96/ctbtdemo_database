package getAllByExcel.port;

import jxl.Sheet;
import jxl.Workbook;
import utilClass.port.Beacon;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetBeacon {

    public List<Beacon> getAllByExcel() {

        List<Beacon> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\beacon.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String id=rs.getCell(j++, i).getContents();
                    String type = rs.getCell(j++, i).getContents();
                    String regionId = rs.getCell(j++, i).getContents();


                    list.add(new Beacon(id,type,regionId));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }

}
