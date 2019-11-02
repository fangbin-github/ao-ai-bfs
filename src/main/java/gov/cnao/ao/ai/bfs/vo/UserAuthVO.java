package gov.cnao.ao.ai.bfs.vo;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserAuthVO extends BaseRequest{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 用户编号
	 */
	private String userId;
	
	private List<AuthVO> userAuths;
	
	public UserAuthVO() {
		super();
	}
	
	public UserAuthVO(String json) throws JsonParseException, JsonMappingException, IOException {
		UserAuthVO userAuthVO = new ObjectMapper().readValue(json, UserAuthVO.class);
		super.setHead(userAuthVO.getHead());
		this.userId = userAuthVO.userId;
		this.userAuths = userAuthVO.userAuths;
	}


	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<AuthVO> getUserAuths() {
		return userAuths;
	}
	public void setUserAuths(List<AuthVO> userAuths) {
		this.userAuths = userAuths;
	}
}
