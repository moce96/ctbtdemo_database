package getAllByExcel;

import jxl.Sheet;
import jxl.Workbook;
import utilClass.Region;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetRegion {


    public List<Region> getAllByExcel() {

        List<Region> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\region.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String regionId=rs.getCell(j++, i).getContents();
                    String regionName=rs.getCell(j++, i).getContents();
                    String regionTypeId = rs.getCell(j++, i).getContents();


                    list.add(new Region(regionId,regionName,regionTypeId));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }



}
