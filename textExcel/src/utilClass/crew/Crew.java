package utilClass.crew;

import java.util.Date;

public class Crew {

    public String name;
    public String id;
    public String bloodType;
    public String shipId;  //所属船舶id
    public Date startTime;  //上船时间

    public Crew(String name, String id, String bloodType, String shipId, Date startTime) {
        this.name = name;
        this.id = id;
        this.bloodType = bloodType;
        this.shipId = shipId;
        this.startTime = startTime;
    }
}
