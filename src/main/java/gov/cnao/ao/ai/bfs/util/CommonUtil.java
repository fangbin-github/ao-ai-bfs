package gov.cnao.ao.ai.bfs.util;

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
}
