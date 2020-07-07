package utilClass.company;

public class Company {
    public String id;
    public String name;
    public String superiorId;

    public Company(String id, String name, String superiorId) {
        this.id = id;
        this.name = name;
        this.superiorId = superiorId;
    }
}
