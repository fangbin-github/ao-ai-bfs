package gov.cnao.ao.ai.bfs.vo;
import java.io.Serializable;
import java.util.List;

/**
 * @author fangbin
 */
public class XianProjectUser implements Serializable{

	private static final long serialVersionUID = 1L;
	private int resultCode;
    private String resultMsg;
    private boolean success;
    private List<Data> data;
    public void setResultCode(int resultCode) {
         this.resultCode = resultCode;
     }
     public int getResultCode() {
         return resultCode;
     }

    public void setResultMsg(String resultMsg) {
         this.resultMsg = resultMsg;
     }
     public String getResultMsg() {
         return resultMsg;
     }

    public void setSuccess(boolean success) {
         this.success = success;
     }
     public boolean getSuccess() {
         return success;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

}