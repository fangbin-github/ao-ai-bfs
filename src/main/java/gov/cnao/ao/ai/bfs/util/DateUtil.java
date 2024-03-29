package gov.cnao.ao.ai.bfs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;

public class DateUtil {

	private final static org.slf4j.Logger log = LoggerFactory.getLogger(DateUtil.class);
	 
	/**
	 * 将date转换为string
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateString = sdf.format(date);
		return dateString;
	}

	/**
	 * 将string转换为date
	 * 
	 * @param dateStr
	 *            日期
	 * @param pattern
	 * @return
	 */
	public static Date stringToDate(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return date;
	}
}
