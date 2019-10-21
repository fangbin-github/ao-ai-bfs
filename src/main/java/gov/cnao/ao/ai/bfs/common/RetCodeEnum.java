package gov.cnao.ao.ai.bfs.common;

/**
 * @author:zhangguoqing 
 * @version:2019年9月5日下午7:36:46
*/
public enum RetCodeEnum implements EnumType<RetCodeEnum, String> {
	SYS_ERROR("OP999001", "服务不可用"),
    SUCCESS("00000000", "成功");

	public final String code;
    public final String msg;

    public String getMsg(String errorMsg) {
        return this.msg + ":" + errorMsg;
    }

    public static RetCodeEnum valueOfCode(String code) {
        RetCodeEnum[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            RetCodeEnum b = var4[var2];
            if (b.code.equals(code)) {
                return b;
            }
        }

        return null;
    }

    public String getType() {
        return this.code;
    }

    private RetCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
