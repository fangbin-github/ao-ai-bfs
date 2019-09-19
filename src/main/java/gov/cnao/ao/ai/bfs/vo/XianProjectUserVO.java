package gov.cnao.ao.ai.bfs.vo;

import java.io.Serializable;
import java.util.List;

public class XianProjectUserVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int resultCode;
    private String resultMsg;
    private boolean success;
    private List<DataVO> dataVOs;
    
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<DataVO> getDataVOs() {
		return dataVOs;
	}
	public void setDataVOs(List<DataVO> dataVOs) {
		this.dataVOs = dataVOs;
	}
    
}
