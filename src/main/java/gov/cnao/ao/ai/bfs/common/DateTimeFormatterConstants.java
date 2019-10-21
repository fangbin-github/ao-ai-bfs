package gov.cnao.ao.ai.bfs.common;

import java.time.format.DateTimeFormatter;

/**
 * @author:zhangguoqing 
 * @version:2019年10月9日下午5:16:49
*/
public class DateTimeFormatterConstants {
    public static final DateTimeFormatter DATE_FMT_0 = DateTimeFormatter.ofPattern("yyMMdd");
    public static final DateTimeFormatter DATE_FMT_1 = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter DATE_FMT_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_FMT_3 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static final DateTimeFormatter DATE_TIME_FMT_0 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter DATE_TIME_FMT_1 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    public static final DateTimeFormatter DATE_TIME_FMT_2 = DateTimeFormatter.ofPattern("yyyyMMdd HHmmssSSS");
    public static final DateTimeFormatter TIME_FMT_0 = DateTimeFormatter.ofPattern("HHmmssSSS");
    public static final DateTimeFormatter TIME_FMT_1 = DateTimeFormatter.ofPattern("HHmmss");

    public DateTimeFormatterConstants() {
    }
}
