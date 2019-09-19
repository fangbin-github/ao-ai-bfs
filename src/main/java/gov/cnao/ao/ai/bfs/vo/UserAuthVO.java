package gov.cnao.ao.ai.bfs.vo;

import java.io.Serializable;
import java.util.List;

import gov.cnao.ao.ai.bfs.entity.UserAuth;

public class UserAuthVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String userId;
	private List<UserAuth> userAuths;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<UserAuth> getUserAuths() {
		return userAuths;
	}
	public void setUserAuths(List<UserAuth> userAuths) {
		this.userAuths = userAuths;
	}
}
