package random;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateCalculate {
    public static   Date add(Date sourceDate,int day){
        /*
        * 第一个参数输入日期对象，第二个参数输入希望增加的天数
        * 比如说2018.01.02 加40天 为 2019.02.11
        * */
        Random random = new Random();
        Calendar date = Calendar.getInstance();
        date.setTime(sourceDate);
        date.add(Calendar.DAY_OF_YEAR,random.nextInt(day) );

        return  date.getTime();
    }
}
