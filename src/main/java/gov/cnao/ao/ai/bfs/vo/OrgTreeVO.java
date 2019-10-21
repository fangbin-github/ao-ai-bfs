package gov.cnao.ao.ai.bfs.vo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrgTreeVO extends BaseRequest{

	private static final long serialVersionUID = 1L;
	private String userId;
	private String projectIds;
	
	public OrgTreeVO() {
		super();
	}
	
	public OrgTreeVO(String json) throws JsonParseException, JsonMappingException, IOException {
		OrgTreeVO orgTreeVO = new ObjectMapper().readValue(json, OrgTreeVO.class);
		super.setHead(orgTreeVO.getHead());
		this.userId = orgTreeVO.userId;
		this.projectIds = orgTreeVO.projectIds;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProjectIds() {
		return projectIds;
	}
	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}
	

}
