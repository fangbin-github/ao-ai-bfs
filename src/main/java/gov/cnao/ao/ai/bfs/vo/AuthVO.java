package gov.cnao.ao.ai.bfs.vo;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthVO extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 用户编号
	 */
	private String userId;

	 /**
	  * 审计项目编号
	  */
	 private String auditPrjId;

	 /**
	  * 授权访问表
	  */
	 private String visitTabNm;
	 
	 /**
	  * 创建人
	  */
	 private String createUser;
	 /**
	  * 创建人名称
	  */
	 private String createUserNm;

	 /**
	  * 创建时间
	  */
	 private String createTms;
	 
	 /**
	  * 实体外属性
	  * @return
	  */
	 private List<String> visitTabNms;
	 private List<UserAuthsVO> authVOs;
	public List<UserAuthsVO> getAuthVOs() {
		return authVOs;
	}
	public void setAuthVOs(List<UserAuthsVO> authVOs) {
		this.authVOs = authVOs;
	}

	public AuthVO() {
		super();
	 }
	 
	public AuthVO(String json) throws JsonParseException, JsonMappingException, IOException {
		AuthVO authVO = new ObjectMapper().readValue(json, AuthVO.class);
		this.userId = authVO.userId;
		this.auditPrjId = authVO.auditPrjId;
		this.visitTabNm = authVO.visitTabNm;
		this.createUser = authVO.createUser;
		this.createUserNm = authVO.createUserNm;
		this.createTms = authVO.createTms;
		this.visitTabNms = authVO.visitTabNms;
		this.authVOs = authVO.authVOs;
	}


	public List<String> getVisitTabNms() {
		return visitTabNms;
	}

	public void setVisitTabNms(List<String> visitTabNms) {
		this.visitTabNms = visitTabNms;
	}

	public String getUserId() {
		 return userId;
	 }

	 public void setUserId(String userId) {
		 this.userId = userId;
	 }

	 public String getAuditPrjId() {
		 return auditPrjId;
	 }

	public void setAuditPrjId(String auditPrjId) {
		this.auditPrjId = auditPrjId;
	}

	public String getVisitTabNm() {
		return visitTabNm;
	}

	public void setVisitTabNm(String visitTabNm) {
		this.visitTabNm = visitTabNm;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	 
	public String getCreateUserNm() {
		return createUserNm;
	}

	public void setCreateUserNm(String createUserNm) {
		this.createUserNm = createUserNm;
	}

	public String getCreateTms() {
		return createTms;
	}

	public void setCreateTms(String createTms) {
		this.createTms = createTms;
	}
}