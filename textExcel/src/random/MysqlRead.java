package random;



import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class MysqlRead {

    public static final String [] message=readurl();

    private static String[] readurl() {
        Properties prop = new Properties();
        String[] message = new String[4];
        int i = 0;
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("src/mysql.properties"));
            prop.load(in);
            message[0] = prop.getProperty("name");
            message[1] = prop.getProperty("url");
            message[2] = prop.getProperty("user");
            message[3] = prop.getProperty("password");
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return message;
    }

}
