package gov.cnao.ao.ai.bfs.vo;

import java.io.Serializable;
import java.util.List;

public class AuditGroupVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String auditGroupId;
    private String name;
    private List<UsersVO> usersVOs;
    
	public String getAuditGroupId() {
		return auditGroupId;
	}
	public void setAuditGroupId(String auditGroupId) {
		this.auditGroupId = auditGroupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<UsersVO> getUsersVOs() {
		return usersVOs;
	}
	public void setUsersVOs(List<UsersVO> usersVOs) {
		this.usersVOs = usersVOs;
	}
    
}
