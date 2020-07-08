package utilClass.accident;

import java.util.Date;

public class Accident {


    public String accidentId;
    public String accidentLoc;
    public String accidentName;
    public String accidentReason;  //所属船舶id
    public String accidentLoss;
    public String involveShipId;
    public Date accidentDate;  //上船时间


    public Accident(String accidentId, String accidentLoc, String accidentName, String accidentReason, String accidentLoss, String involveShipId, Date accidentDate) {
        this.accidentId = accidentId;
        this.accidentLoc = accidentLoc;
        this.accidentName = accidentName;
        this.accidentReason = accidentReason;
        this.accidentLoss = accidentLoss;
        this.involveShipId = involveShipId;
        this.accidentDate = accidentDate;
    }
}
