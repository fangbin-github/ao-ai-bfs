package gov.cnao.ao.ai.bfs.vo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * redis临时接口
 * @author fangbin
 */
public class RedisVO extends BaseRequest{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	private String usno;
	
	/**
	 * 项目ID
	 */
	private String auditPrjId;
	
	public RedisVO() {
		super();
	}

	public RedisVO(String json) throws JsonParseException, JsonMappingException, IOException {
		RedisVO redisVO = new ObjectMapper().readValue(json, RedisVO.class);
//		super.setHead(redisVO.getHead()); 
		this.usno = redisVO.usno;
		this.auditPrjId = redisVO.auditPrjId;
	}

	public String getUsno() {
		return usno;
	}

	public void setUsno(String usno) {
		this.usno = usno;
	}

	public String getAuditPrjId() {
		return auditPrjId;
	}

	public void setAuditPrjId(String auditPrjId) {
		this.auditPrjId = auditPrjId;
	}
	
}
