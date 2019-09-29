package gov.cnao.ao.ai.bfs.vo;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SchemVO extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 项目schema
	 */
	private String tableSchem;
	
	/**
	 * 审计项目ID
	 */
	private String auditPrjId;
	
	/**
	 * 审计项目名称
	 */
	private String auditPrjNm;
	
	/**
	 * 审计项目编码
	 */
	private String auditPrjCd;
	
	public SchemVO() {
		super();
	}
	
	public SchemVO(String json) throws JsonParseException, JsonMappingException, IOException {
		SchemVO schemVO = new ObjectMapper().readValue(json, SchemVO.class);
		this.tableSchem = schemVO.tableSchem;
		this.auditPrjId = schemVO.auditPrjId;
		this.auditPrjNm = schemVO.auditPrjNm;
		this.auditPrjCd = schemVO.auditPrjCd;
	}




	public String getTableSchem() {
		return tableSchem;
	}

	public void setTableSchem(String tableSchem) {
		this.tableSchem = tableSchem;
	}

	public String getAuditPrjId() {
		return auditPrjId;
	}

	public void setAuditPrjId(String auditPrjId) {
		this.auditPrjId = auditPrjId;
	}

	public String getAuditPrjNm() {
		return auditPrjNm;
	}

	public void setAuditPrjNm(String auditPrjNm) {
		this.auditPrjNm = auditPrjNm;
	}

	public String getAuditPrjCd() {
		return auditPrjCd;
	}

	public void setAuditPrjCd(String auditPrjCd) {
		this.auditPrjCd = auditPrjCd;
	}
	
}
