package gov.cnao.ao.ai.bfs.vo;

import java.io.Serializable;
import java.util.List;

public class DataVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String projectId;
    private String projectName;
    private List<AuditGroupVO> auditGroupVOs;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public List<AuditGroupVO> getAuditGroupVOs() {
		return auditGroupVOs;
	}
	public void setAuditGroupVOs(List<AuditGroupVO> auditGroupVOs) {
		this.auditGroupVOs = auditGroupVOs;
	}
    
}
