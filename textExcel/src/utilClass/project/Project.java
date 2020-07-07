package utilClass.project;

import java.util.Date;

public class Project {
    public String projectId;
    public String projectName;
    public String projectSpecificId;
    public Date reportTime;

    public Project(String projectId,String projectName,String projectSpecificId,Date reportTime) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectSpecificId = projectSpecificId;
        this.reportTime = reportTime;
    }
}
