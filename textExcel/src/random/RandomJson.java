package random;

/**
 * 生成一个指定长度指定对象内容的json数据
 *              格式:  [{"1":231312,"2":15312312}......]
 * length 表示指定长度
 * list  表示指定对象
 */

import utilClass.checkout.InspectItem;
import utilClass.crew.Crew;
import utilClass.law.LawMan;
import utilClass.law.LawShip;
import utilClass.ship.Ship;

import java.util.List;
import java.util.Random;

public class RandomJson {

    Random random = new Random();

    /**
     * 获取shipId的json数据
     */
    public String generateShip(int length, List<Ship> list) {

        String json = new String();
        json = "";

        for (int i=1; i<=length; i++) {
            int j = random.nextInt(list.size());
            json = json+list.get(j).shipId+";";
        }

        json = json.substring(0,json.length()-1);

        return json;
    }


    /**
     * 获取lawManId的json数据
     */
    public String generateLawman(int length, List<LawMan> list) {

        String json = new String();
        json = "";

        for (int i=1; i<=length; i++) {
            int j = random.nextInt(list.size());
            json = json+list.get(j).id+";";
        }

        json = json.substring(0,json.length()-1);

        return json;


    }



    /**
     * 获取lawShipId的json数据
     */
    public String generateLawShip(int length, List<LawShip> list) {

        String json = new String();
        json = "";

        for (int i=1; i<=length; i++) {
            int j = random.nextInt(list.size());
            json = json+list.get(j).shipId+";";
        }

        json = json.substring(0,json.length()-1);

        return json;

    }

    /**
     * 获取crew的json数据
     */
    public String generateCrew(int length, List<Crew> list) {

        String json = new String();

        for (int i=1; i<=length; i++) {
            int j = random.nextInt(list.size());
            json = json+list.get(j).id+";";
        }

        json = json.substring(0,json.length()-1);

        return json;

    }

    /**
     * 获取inspectitem的json数据
     */
    public String generateInspectItem(int length, List<InspectItem> list) {

        String json = new String();

        for (int i=1; i<=length; i++) {
            int j = random.nextInt(list.size());
            json = json+list.get(j).id+";";
        }

        json = json.substring(0,json.length()-1);

        return json;

    }

}
