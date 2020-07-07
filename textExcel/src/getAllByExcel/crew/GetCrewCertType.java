package getAllByExcel.crew;

import jxl.Sheet;
import jxl.Workbook;
import utilClass.crew.CrewCertType;
import utilClass.crew.JobType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetCrewCertType {




    public List<CrewCertType> getAllByExcel() {

        List<CrewCertType> list=new ArrayList<>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\crewcerttype.xls"));
            Sheet rs=rwb.getSheet(0);//表
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String certType=rs.getCell(j++, i).getContents();
                    String certTypeId=rs.getCell(j++, i).getContents();
                    String certName = rs.getCell(j++, i).getContents();

//                    System.out.println(name+id);

                    list.add(new CrewCertType(certType,certTypeId,certName));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }



}
