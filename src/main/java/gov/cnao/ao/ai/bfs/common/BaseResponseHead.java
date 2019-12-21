package gov.cnao.ao.ai.bfs.common;

import java.io.Serializable;

/**
 * @author:zhangguoqing 
 * @version:2019年9月5日下午7:26:40
*/
public class BaseResponseHead implements Serializable {
    private final static long serialVersionUID = 1L;
    private ResponseHead head;

    public BaseResponseHead() {
    }

	public ResponseHead getHead() {
		return head;
	}

	public void setHead(ResponseHead head) {
		this.head = head;
	}

	public BaseResponseHead(ResponseHead head) {
		this.head = head;
	}

	

    
}