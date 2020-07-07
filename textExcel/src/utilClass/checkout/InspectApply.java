package utilClass.checkout;

public class InspectApply {

    public String shipId;
    public String inspectItemIdList;
    public String inspectCertificateId;
    public String inspectType;
    public String shipType;
    public String fisheryProductId;

    public InspectApply(String shipId,String inspectItemIdList,String inspectCertificateId,String inspectType,String shipType,String fisheryProductId) {
        this.shipId = shipId;
        this.inspectItemIdList = inspectItemIdList;
        this.inspectCertificateId = inspectCertificateId;
        this.inspectType = inspectType;
        this.shipType = shipType;
        this.fisheryProductId = fisheryProductId;
    }
}
