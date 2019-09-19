package gov.cnao.ao.ai.bfs.vo;

import java.io.Serializable;

public class UsersVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String userId;
    private String name;
    private String roleCode;
    private String roleName;
    
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
}
