package gov.cnao.ao.ai.bfs.entity;

import gov.cnao.ao.ai.bfs.vo.BaseRequest;

public class SchemaState extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
    /**
     * 项目ID
     */
    private String auditPrjId;

    /**
     * 状态
     */
    private String state;

    /**
     * 创建人ID
     */
    private String createUser;

    /**
     * 创建人
     */
    private String createUserNm;

    /**
     * 创建时间
     */
    private String createTms;

    public String getAuditPrjId() {
        return auditPrjId;
    }

    public void setAuditPrjId(String auditPrjId) {
        this.auditPrjId = auditPrjId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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