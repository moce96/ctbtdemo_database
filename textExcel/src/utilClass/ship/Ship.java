package utilClass.ship;

public class Ship {

    public String shipId;
    public String shipName;
    public String shipTypeId;
    public String county;
    public String town;
    public String village;
    public String companyId;

    public Ship(String shipId, String shipName, String shipTypeId,String county,String town,String village,String companyId) {
        this.shipId = shipId;
        this.shipName = shipName;
        this.shipTypeId = shipTypeId;
        this.county = county;
        this.town = town;
        this.village = village;
        this.companyId = companyId;


    }
}
