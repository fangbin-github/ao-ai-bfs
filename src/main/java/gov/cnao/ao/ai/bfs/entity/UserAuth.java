package gov.cnao.ao.ai.bfs.entity;

import java.io.Serializable;
import java.util.Date;

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
     * 授权访问表名称
     */
    private String visitTabNm;
    
    /**
     * 创建人编号
     */
    private String createUserId;

    /**
     * 创建人名称
     */
    private String createUserNm;

    /**
     * 创建时间
     */
    private String createTm;
    
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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserNm() {
        return createUserNm;
    }

    public void setCreateUserNm(String createUserNm) {
        this.createUserNm = createUserNm;
    }

	public String getCreateTm() {
		return createTm;
	}

	public void setCreateTm(String createTm) {
		this.createTm = createTm;
	}
    
}