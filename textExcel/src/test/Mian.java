package test;

import test.Insert;
import test.getAllByExcel;

import java.util.ArrayList;
import java.util.List;

public class Mian {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        getAllByExcel getAllByExcel = new getAllByExcel();
        students = getAllByExcel.getAllByExcel();

        Insert insert = new Insert();
        insert.insert(students);
    }
}
