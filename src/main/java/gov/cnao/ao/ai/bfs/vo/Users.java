package gov.cnao.ao.ai.bfs.vo;

import java.io.Serializable;

/**
 * @author fangbin
 */
public class Users implements Serializable{

	private static final long serialVersionUID = 1L;
	private String userId;
    private String userName;
    private String roleCode;
    private String roleName;
    public void setUserId(String userId) {
         this.userId = userId;
     }
     public String getUserId() {
         return userId;
     }

    public void setUserName(String userName) {
         this.userName = userName;
     }
     public String getUserName() {
         return userName;
     }

    public void setRoleCode(String roleCode) {
         this.roleCode = roleCode;
     }
     public String getRoleCode() {
         return roleCode;
     }

    public void setRoleName(String roleName) {
         this.roleName = roleName;
     }
     public String getRoleName() {
         return roleName;
     }

}