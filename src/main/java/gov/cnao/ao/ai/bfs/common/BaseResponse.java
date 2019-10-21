package gov.cnao.ao.ai.bfs.common;


/**
 * @author:zhangguoqing
 * @version:2019年9月5日下午7:26:09
 */
public class BaseResponse<T>  extends BaseResponseHead {
    public static final String S = "S";
    public static final String F = "F";
	private static final long serialVersionUID = 1L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		return result;
	}

}
