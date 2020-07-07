package test;

import java.util.Arrays;
import java.util.List;

public class ToList {
    public void fun() {
        String str1 = "801;802";
        String str2 = "801";

        List<String> str11 = Arrays.asList(str1.split(";"));
        List<String> str22 = Arrays.asList(str2.split(";"));

        System.out.println(str11);
        System.out.println(str22);

    }
}
