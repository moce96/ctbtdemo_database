package utilClass.user;

public class User {

    public String userName;
    public String userId;
    public String departmentId;
    public String announcementReading;   //是否阅读公告，1则表示阅读，0则表示未阅读。对所有公告都一样

    public User(String userName, String userId,String departmentId,String announcementReading) {
        this.userName = userName;
        this.userId = userId;
        this.departmentId = departmentId;
        this.announcementReading = announcementReading;
    }
}
