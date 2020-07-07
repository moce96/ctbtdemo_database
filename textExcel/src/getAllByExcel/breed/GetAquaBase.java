package getAllByExcel.breed;

import jxl.Sheet;
import jxl.Workbook;
import utilClass.breed.AquaBase;
import utilClass.breed.BreedPerson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetAquaBase {



    public List<AquaBase> getAllByExcel() {

        List<AquaBase> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\aquabase.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String id=rs.getCell(j++, i).getContents();
                    String fryType=rs.getCell(j++, i).getContents();
                    String baseType = rs.getCell(j++, i).getContents();
                    String regionId = rs.getCell(j++, i).getContents();


                    list.add(new AquaBase(id,fryType,baseType,regionId));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }



}
