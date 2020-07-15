package utilClass.port;

import java.util.Date;

public class ArrivePort {
    public String shipid;
    public String portId;
    public Date arrivalTime;
    public String crewId;
    public String crewPhoto;
    public String id;

    public ArrivePort(String shipid, String portId, Date arrivalTime, String crewId, String crewPhoto, String id) {
        this.shipid = shipid;
        this.portId = portId;
        this.arrivalTime = arrivalTime;
        this.crewId = crewId;
        this.crewPhoto = crewPhoto;
        this.id = id;
    }
}
