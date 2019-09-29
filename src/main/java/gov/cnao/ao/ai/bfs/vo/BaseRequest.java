package gov.cnao.ao.ai.bfs.vo;

import java.io.Serializable;

/**
 * BaseRequest
 * 
 * @author:zhangguoqing
 * @version:2019年8月29日下午4:01:13
 */

public class BaseRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private RequestHead head;

	public BaseRequest() {
	}

	public RequestHead getHead() {
		return this.head;
	}

	public void setHead(RequestHead head) {
		this.head = head;
	}

	@Override
	public String toString() {
		return "BaseRequest [head=" + head + ", getHead()=" + getHead() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
