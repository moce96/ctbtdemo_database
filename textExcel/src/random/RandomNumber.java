package random;

/**
 * 生成随机编号,length表示编号的长度
 *
 * 若type=1纯数字编号   若type=2纯字母编号
 */

import java.util.Random;

public class RandomNumber {


    public String generate(int type, int length) {
        String str1 = "123456789";
        String str2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        if (type == 1) {
            for (int i = 0; i < length; i++) {
                int number = random.nextInt(str1.length());
                sb.append(str1.charAt(number));
            }

        } else if (type == 2) {
            for (int i = 0; i < length; i++) {
                int number = random.nextInt(str2.length());
                sb.append(str2.charAt(number));
            }


        }

        return sb.toString();
    }
}
