package utilClass;

import java.util.Date;

public class Person {

    public String name;
    public String id;
    public String bloodType;
    public String shipId;  //所属船舶id
    public Date startTime;  //上船时间
    public Date endTime;  //离船时间

    public Person(String name, String id,String bloodType,String shipId,Date startTime,Date endTime) {
        this.name = name;
        this.id = id;
        this.bloodType = bloodType;
        this.shipId = shipId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
