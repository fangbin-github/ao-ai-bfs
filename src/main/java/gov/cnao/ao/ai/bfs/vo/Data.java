package gov.cnao.ao.ai.bfs.vo;
import java.io.Serializable;
import java.util.List;

/**
 * @author fangbin
 */
public class Data implements Serializable{

	private static final long serialVersionUID = 1L;
	private String projectId;
    private String projectName;
    private List<AuditGroups> auditGroups;
    public void setProjectId(String projectId) {
         this.projectId = projectId;
     }
     public String getProjectId() {
         return projectId;
     }

    public void setProjectName(String projectName) {
         this.projectName = projectName;
     }
     public String getProjectName() {
         return projectName;
     }

    public void setAuditGroups(List<AuditGroups> auditGroups) {
         this.auditGroups = auditGroups;
     }
     public List<AuditGroups> getAuditGroups() {
         return auditGroups;
     }

}