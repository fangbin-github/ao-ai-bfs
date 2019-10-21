package gov.cnao.ao.ai.bfs.vo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OperLogVO extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	/**
     * 日志编号
     */
    private String logId;

    /**
     * 项目编号
     */
    private String projId;
    
    /**
     * 用户标识
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userNm;

    /**
     * 机构代码
     */
    private String orgId;

    /**
     * 机构名称
     */
    private String orgNm;

    /**
     * 登录IP 
     */
    private String loginIp;

    /**
     * 操作时间
     */
    private String operTm;

    /**
     * 日志类型
     */
    private String logType;

    /**
     * 功能标识
     */
    private String funFlg;

    /**
     * 日志内容
     */
    private String logCont;

    /**
     * 访问微服务名称
     */
    private String visitMicr;

    /**
     * 访问菜单
     */
    private String visitMenu;
    
    public OperLogVO() {
    	super();
    }
    
    public OperLogVO(String json) throws JsonParseException, JsonMappingException, IOException {
    	OperLogVO operLogVO = new ObjectMapper().readValue(json, OperLogVO.class);
    	super.setHead(operLogVO.getHead()); 
		this.logId = operLogVO.logId;
		this.projId = operLogVO.projId;
		this.userId = operLogVO.userId;
		this.userNm = operLogVO.userNm;
		this.orgId = operLogVO.orgId;
		this.orgNm = operLogVO.orgNm;
		this.loginIp = operLogVO.loginIp;
		this.operTm = operLogVO.operTm;
		this.logType = operLogVO.logType;
		this.funFlg = operLogVO.funFlg;
		this.logCont = operLogVO.logCont;
		this.visitMicr = operLogVO.visitMicr;
		this.visitMenu = operLogVO.visitMenu;
	}



	public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgNm() {
        return orgNm;
    }

    public void setOrgNm(String orgNm) {
        this.orgNm = orgNm;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getOperTm() {
        return operTm;
    }

    public void setOperTm(String operTm) {
        this.operTm = operTm;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getFunFlg() {
        return funFlg;
    }

    public void setFunFlg(String funFlg) {
        this.funFlg = funFlg;
    }

    public String getLogCont() {
        return logCont;
    }

    public void setLogCont(String logCont) {
        this.logCont = logCont;
    }

    public String getVisitMicr() {
        return visitMicr;
    }

    public void setVisitMicr(String visitMicr) {
        this.visitMicr = visitMicr;
    }

    public String getVisitMenu() {
        return visitMenu;
    }

    public void setVisitMenu(String visitMenu) {
        this.visitMenu = visitMenu;
    }
}