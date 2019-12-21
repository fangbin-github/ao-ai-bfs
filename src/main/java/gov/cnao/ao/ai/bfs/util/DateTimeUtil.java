package gov.cnao.ao.ai.bfs.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	/**
	 * 日期格式化
	 */
	public final static String TIME_PATTER = "yyyy-MM-dd HH:mm:ss";
	
	public static String getFormatTime() {
		return format(getCurrentTime(), TIME_PATTER);
	}
	//输出字符串类型时间
	public static String format(Date dt, String pattern ) {
		String sDate;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sDate = sdf.format(dt);
		return sDate;
	}
	/**
	 * 获取当前系统时间
	 */
	public static Date getCurrentTime() {
		return new Date();
		
	}
	/**
	 * 获取当前系统日期
	 */
	public static Date getCurrentDate() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.clear(Calendar.HOUR);
		c.clear(Calendar.MINUTE);
		c.clear(Calendar.SECOND);
		c.clear(Calendar.MILLISECOND);
		date = c.getTime();
		return date;
	}
}
