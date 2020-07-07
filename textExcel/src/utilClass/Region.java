package utilClass;

/**
 * 区域数据   用于工程建设表中和渔业保护区表
 */

public class Region {

    public String regionId;
    public String regionName;
    public String regionTypeId;

    public Region(String regionId,String regionName,String regionTypeId) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.regionTypeId = regionTypeId;
    }
}
