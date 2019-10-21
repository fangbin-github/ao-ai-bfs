package gov.cnao.ao.ai.bfs.vo;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MethodStatisticalVO extends BaseRequest{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 方法名
	 */
	private String methodName;
	
	/**
	 * 方法数量
	 */
	private Integer methodCount;
	
	public MethodStatisticalVO() {
		super();
	}
	
	public MethodStatisticalVO(String json) throws JsonParseException, JsonMappingException, IOException {
		MethodStatisticalVO methodStatisticalVO = new ObjectMapper().readValue(json, MethodStatisticalVO.class);
		super.setHead(methodStatisticalVO.getHead());
		this.methodName = methodStatisticalVO.methodName;
		this.methodCount = methodStatisticalVO.methodCount;
	}

	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Integer getMethodCount() {
		return methodCount;
	}
	public void setMethodCount(Integer methodCount) {
		this.methodCount = methodCount;
	}
	
}
