package gov.cnao.ao.ai.bfs.common;

/**
 * @author:zhangguoqing 
 * @version:2019年9月5日下午7:27:12
*/
public class ResponseHead {
	public final static String SUCCESS_RET_STATUS = "S";
	public final static String FAIL_RET_STATUS = "F";
 	private String stat ;
    private String rscd;
    private String trno;
    private String code;
    private String msg;
    private String rpts;
    
    public ResponseHead() {}
	
	public String getRscd() {
		return rscd;
	}
	public void setRscd(String rscd) {
		this.rscd = rscd;
	}
	public String getTrno() {
		return trno;
	}
	public void setTrno(String trno) {
		this.trno = trno;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getRpts() {
		return rpts;
	}
	public void setRpts(String rpts) {
		this.rpts = rpts;
	}

	@Override
	public String toString() {
		return "ResponseHead [rscd=" + rscd + ", trno=" + trno + ", code=" + code + ", stat=" + stat + ", msg="
				+ msg + ", rpts=" + rpts + "]";
	}
    
	public ResponseHead(String stat, String rscd, String trno, String code, String msg, String rpts) {
        this.stat = stat;
        this.rscd = rscd;
        this.trno = trno;
        this.code = code;
        this.msg = msg;
        this.rpts = rpts;
	}
}
