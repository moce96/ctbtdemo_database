package test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class getAllByExcel {

   public List<Student> getAllByExcel() {

       List<Student> list=new ArrayList<Student>();
       try {
           Workbook rwb=Workbook.getWorkbook(new File("src\\excel\\bb.xls"));
           Sheet rs=rwb.getSheet(0);//表
           int clos=rs.getColumns();//得到所有的列
           int rows=rs.getRows();//得到所有的行

           System.out.println("表的列数："+clos+" 表的行数:"+rows);
           for (int i = 1; i < rows; i++) {
               for (int j = 0; j < clos; j++) {
                   //第一个是列数，第二个是行数
                   String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++

                   String s_name=rs.getCell(j++, i).getContents();
                   String age=rs.getCell(j++, i).getContents();
                   String address=rs.getCell(j++, i).getContents();

                   System.out.println("id:"+id+" name:"+s_name+" sex:"+age+" address:"+address);
                   list.add(new Student(Integer.parseInt(id), s_name,age,address));
               }
           }
       } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       return list;

   }
 }


