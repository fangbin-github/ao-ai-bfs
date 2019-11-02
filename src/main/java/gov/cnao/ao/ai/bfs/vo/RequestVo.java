package gov.cnao.ao.ai.bfs.vo;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

//import gov.cnao.ao.ai.oes.vo.AdtPrf.TOesAuditPrfVo;

/**
 * 封装了所有请求的参数
 * @author tianzhenchao
 *
 */
public class RequestVo implements Serializable{
	
	private static final long serialVersionUID = -4279787976276334694L;
	
	//用户编号
	private String userId;
//	//用户名字
//	private String userName;
	//审计项目ID
//	private String auditPrjId;
	//审计组ID
	private String auditGrpId;
	//文件名字
//	private String fileName;
//	//资料树id
//	private String nodeId;
//	//未知文件id
//	private String unknownFileId;
//	//未知文件类型 10模板分项 20工作底稿 30审计方案 40证据 50取证单 60文书 70报告
//	private String unknownFileType;
//	//被勾选的审计人员数组
//	private String[] auditorIds;
//	//父节点id
//	private String parentNodeCd;
	//审计项目数组
	private String[] projectIds;
//	//审计报告文件id
//	private String auditRptfileId;
//	//审计报告文件名字
//	private String auditRptfileNm;
//	//审计报告提纲id
//	private String outlnItmId;
//	//审计报告提纲描述
//	private String outlnItmDsc;
//	//二级资料树节点NodeCd
//	private String lvl2NodeCd;
//	
//	//证据vo
//	private TOesAuditPrfVo tOesAuditPrfVo;
	//分页参数：第几页
//	private int pageNum;
//	//分页参数：每页显示几条
//	private int pageSize;
//	
//	//时间
//	private String time;
	
	public RequestVo() {
		super();
	}
	public RequestVo(String json) throws IOException {
		RequestVo requestVo = new ObjectMapper().readValue(json, RequestVo.class);
		this.userId = requestVo.userId;
//		this.userName = requestVo.userName;
//		this.auditPrjId = requestVo.auditPrjId;
		this.auditGrpId = requestVo.auditGrpId;
//		this.fileName = requestVo.fileName;
//		this.nodeId = requestVo.nodeId;
//		this.unknownFileId = requestVo.unknownFileId;
//		this.unknownFileType = requestVo.unknownFileType;
//		this.auditorIds = requestVo.auditorIds;
//		this.parentNodeCd = requestVo.parentNodeCd;
		this.projectIds = requestVo.projectIds;
//		this.auditRptfileId = requestVo.auditRptfileId;
//		this.auditRptfileNm = requestVo.auditRptfileNm;
//		this.outlnItmId = requestVo.outlnItmId;
//		this.outlnItmDsc = requestVo.outlnItmDsc;
//		this.tOesAuditPrfVo = requestVo.tOesAuditPrfVo;
//		this.pageNum = requestVo.pageNum;
//		this.pageSize = requestVo.pageSize;
//		this.lvl2NodeCd=requestVo.lvl2NodeCd;
//
//		this.time=requestVo.time;

	}

//	public String getTime() {
//		return time;
//	}
//	public void setTime(String time) {
//		this.time = time;
//	}
//	public String getLvl2NodeCd() {
//		return lvl2NodeCd;
//	}
//	public void setLvl2NodeCd(String lvl2NodeCd) {
//		this.lvl2NodeCd = lvl2NodeCd;
//	}
//	public int getPageNum() {
//		return pageNum;
//	}
//	public void setPageNum(int pageNum) {
//		this.pageNum = pageNum;
//	}
//	public int getPageSize() {
//		return pageSize;
//	}
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}
//	public String getOutlnItmId() {
//		return outlnItmId;
//	}
//	public void setOutlnItmId(String outlnItmId) {
//		this.outlnItmId = outlnItmId;
//	}
//	public String getOutlnItmDsc() {
//		return outlnItmDsc;
//	}
//	public void setOutlnItmDsc(String outlnItmDsc) {
//		this.outlnItmDsc = outlnItmDsc;
//	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	public String getAuditRptfileId() {
//		return auditRptfileId;
//	}
//	public void setAuditRptfileId(String auditRptfileId) {
//		this.auditRptfileId = auditRptfileId;
//	}
//	public String getAuditRptfileNm() {
//		return auditRptfileNm;
//	}
//	public void setAuditRptfileNm(String auditRptfileNm) {
//		this.auditRptfileNm = auditRptfileNm;
//	}
	public String[] getProjectIds() {
		return projectIds;
	}
	public void setProjectIds(String[] projectIds) {
		this.projectIds = projectIds;
	}
//	public String getParentNodeCd() {
//		return parentNodeCd;
//	}
//	public void setParentNodeCd(String parentNodeCd) {
//		this.parentNodeCd = parentNodeCd;
//	}
//	public String[] getAuditorIds() {
//		return auditorIds;
//	}
//	public void setAuditorIds(String[] auditorIds) {
//		this.auditorIds = auditorIds;
//	}
//	public String getUnknownFileId() {
//		return unknownFileId;
//	}
//	public void setUnknownFileId(String unknownFileId) {
//		this.unknownFileId = unknownFileId;
//	}
//	public String getUnknownFileType() {
//		return unknownFileType;
//	}
//	public void setUnknownFileType(String unknownFileType) {
//		this.unknownFileType = unknownFileType;
//	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
//	public String getAuditPrjId() {
//		return auditPrjId;
//	}
//	public void setAuditPrjId(String auditPrjId) {
//		this.auditPrjId = auditPrjId;
//	}
	public String getAuditGrpId() {
		return auditGrpId;
	}
	public void setAuditGrpId(String auditGrpId) {
		this.auditGrpId = auditGrpId;
	}
//	public String getFileName() {
//		return fileName;
//	}
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}
//	public String getNodeId() {
//		return nodeId;
//	}
//	public void setNodeId(String nodeId) {
//		this.nodeId = nodeId;
//	}
//	public TOesAuditPrfVo gettOesAuditPrfVo() {
//		return tOesAuditPrfVo;
//	}
//	public void settOesAuditPrfVo(TOesAuditPrfVo tOesAuditPrfVo) {
//		this.tOesAuditPrfVo = tOesAuditPrfVo;
//	}
}
