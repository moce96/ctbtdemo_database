package getAllByExcel.port;

import jxl.Sheet;
import jxl.Workbook;
import random.StringToDate;
import utilClass.port.ArrivePort;
import utilClass.port.Port;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetArrivePort {
    public List<ArrivePort> getAllByExcel() {

        List<ArrivePort> list = new ArrayList<>();
        try {
            Workbook rwb = Workbook.getWorkbook(new File("src\\excel\\j_arrivePort.xls"));
            Sheet rs = rwb.getSheet(0);//表
            int clos = rs.getColumns();//得到所有的列
            int rows = rs.getRows();//得到所有的行


            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数

                    String shipid = rs.getCell(j++, i).getContents();
                    ;
                    String portId = rs.getCell(j++, i).getContents();
                    ;
                    Date arrivalTime = StringToDate.function(rs.getCell(j++, i).getContents());
                    ;
                    ;
                    String crewId = rs.getCell(j++, i).getContents();
                    ;
                    String crewPhoto = rs.getCell(j++, i).getContents();
                    ;
                    String id = rs.getCell(j++, i).getContents();
                    ;


                    list.add(new ArrivePort(shipid, portId, arrivalTime, crewId, crewPhoto, id));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }
}
