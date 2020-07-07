package random;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import getAllByExcel.announce.GetAnnounce;
import getAllByExcel.user.GetUser;
import utilClass.announce.Announce;
import utilClass.user.User;

import java.util.List;
import java.util.Random;

/**
 * 生成类似这样的json数据
 * [
 *     {
 *         "announceId":"10001",
 *         "element":[
 *             {
 *                 "departmentId":"4",
 *                 "personId":"7873711869255110"
 *             },
 *             {
 *                 "departmentId":"2",
 *                 "personId":"64910531386329200"
 *             },
 *             {
 *                 "departmentId":"2",
 *                 "personId":"21374304748690600"
 *             }
 *         ]
 *     },
 *     Object{...},
 *     Object{...},
 *     Object{...},
 *     Object{...}
 * ]
 */

public class Json2 {

    public static JsonArray generate() {

        Random random = new Random();


        GetAnnounce getAnnounce = new GetAnnounce();
        GetUser getUser = new GetUser();

        List<Announce> announces = getAnnounce.getAllByExcel();
        List<User> users = getUser.getAllByExcel();


        /**
         * 开始
         */
        JsonArray arrayPlayer = new JsonArray();

        for (Announce announce : announces) {

            //构建json对象
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("announceId",announce.announceId);


            //构建json数组，数组里面也是json
            JsonArray jsonArray = new JsonArray();

            //length 表示数组长度
            int length = 3;

            //构造一个数组，存放已经选取的userId，避免userId重复
            int[] x = new int[length+1];
            int k = 0;

            //为数组填充json对象
            for (int i=0; i<length; i++) {
                JsonObject object = new JsonObject();



                int j = random.nextInt(users.size());
                while (Json2.f(x,j)) {
                    j = random.nextInt(users.size());
                }
                x[k++] = j;

                object.addProperty("departmentId",users.get(j).departmentId);
                object.addProperty("personId",users.get(j).userId);

                jsonArray.add(object);
            }

            //会报错 是因为需要的参数是字符串
            //jsonObject.addProperty("element", jsonArray);
            jsonObject.add("element", jsonArray);


            //将生成的json对象放入json数组中
            arrayPlayer.add(jsonObject);
        }

//        System.out.println(arrayPlayer);
        return arrayPlayer;

    }

    /**
     * 判断某个数是否在数组内，为了避免取到重复的userId
     * @return
     */
    static boolean f(int[] x, int j) {

        for (int i : x) {

            if (i == j) {
                return true;
            }
        }

        return false;

    }

}
