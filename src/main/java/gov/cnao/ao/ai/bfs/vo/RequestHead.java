package gov.cnao.ao.ai.bfs.vo;

import java.io.Serializable;

/**
 * RequestHead
 * 
 * @author:zhangguoqing
 * @version:2019年8月29日下午3:24:35
 */
public final class RequestHead implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 发起用户编号
	 */
	private String usno;
	/**
	 * 发起方系统代码
	 */
	private String rscd;
	/**
	 * 发起方流水号
	 */
	private String trno;
	/**
	 * 交易发起时间戳
	 */
	private String rsts;
	/**
	 * 多页查询起始记录号
	 */
	private Integer pgsn;
	/**
	 * 多页查询每页条数
	 */
	private Integer pgrw;
	
	public String getUsno() {
		return usno;
	}
	public void setUsno(String usno) {
		this.usno = usno;
	}
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
	public String getRsts() {
		return rsts;
	}
	public void setRsts(String rsts) {
		this.rsts = rsts;
	}
	public Integer getPgsn() {
		return pgsn;
	}
	public void setPgsn(Integer pgsn) {
		this.pgsn = pgsn;
	}
	public Integer getPgrw() {
		return pgrw;
	}
	public void setPgrw(Integer pgrw) {
		this.pgrw = pgrw;
	}

}
