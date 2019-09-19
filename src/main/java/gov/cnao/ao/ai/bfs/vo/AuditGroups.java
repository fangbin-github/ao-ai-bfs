package gov.cnao.ao.ai.bfs.vo;
import java.io.Serializable;
import java.util.List;

/**
 * @author fangbin
 */
public class AuditGroups implements Serializable{

	private static final long serialVersionUID = 1L;
	private String auditGroupId;
    private String auditGroupName;
    private List<Users> users;
    public void setAuditGroupId(String auditGroupId) {
         this.auditGroupId = auditGroupId;
     }
     public String getAuditGroupId() {
         return auditGroupId;
     }

    public void setAuditGroupName(String auditGroupName) {
         this.auditGroupName = auditGroupName;
     }
     public String getAuditGroupName() {
         return auditGroupName;
     }

    public void setUsers(List<Users> users) {
         this.users = users;
     }
     public List<Users> getUsers() {
         return users;
     }

}