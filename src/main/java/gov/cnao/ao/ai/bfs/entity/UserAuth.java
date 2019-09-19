package gov.cnao.ao.ai.bfs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserAuth implements Serializable {
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