package gov.cnao.ao.ai.bfs.util;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

public class CommonUtil {
	
	/**
	 * 将逗号字符串转成SQL所需要的IN格式 如：1,2,3 转成 '1','2','3'
	 * @param str
	 * @return
	 */
	public static String toInValues(String str){
	    if(StringUtils.isBlank(str)) {
            return str;
        }
		return "'"+str.replaceAll(",", "','")+"'";
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String toUTF8String(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes(StandardCharsets.UTF_8);
                } catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0)
                        k += 256;
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
}
