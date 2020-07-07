package utilClass.checkout;

public class Enterprise {

    public String enterpriseId;
    public String enterpriseName;
    public String enterpriseType;
    public String locId;
    public String address;

    public Enterprise(String enterpriseId,String enterpriseName,String enterpriseType,String locId,String address) {
        this.enterpriseId = enterpriseId;
        this.enterpriseName = enterpriseName;
        this.enterpriseType = enterpriseType;
        this.locId = locId;
        this.address = address;
    }
}
