package gov.cnao.ao.ai.bfs.common;


/**
 * @author:zhangguoqing
 * @version:2019年9月5日下午7:26:09
 */
public class BaseResponse<T>  extends BaseResponseHead {
    public final static String S = "S";
    public final static String F = "F";
	private final static long serialVersionUID = 1L;

	private T body;
	
	public BaseResponse() {
    }

    public BaseResponse(T body) {
        this.body = body;
    }
    
	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "BaseResponse [body=" + body + "]";
	}

}
