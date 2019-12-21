package gov.cnao.ao.ai.bfs.common;

import java.time.format.DateTimeFormatter;

/**
 * @author:zhangguoqing 
 * @version:2019年10月9日下午5:16:49
*/
public class DateTimeFormatterConstants {
    public final static DateTimeFormatter DATE_FMT_0 = DateTimeFormatter.ofPattern("yyMMdd");
    public final static DateTimeFormatter DATE_FMT_1 = DateTimeFormatter.ofPattern("yyyyMMdd");
    public final static DateTimeFormatter DATE_FMT_2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final static DateTimeFormatter DATE_FMT_3 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public final static DateTimeFormatter DATE_TIME_FMT_0 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public final static DateTimeFormatter DATE_TIME_FMT_1 = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    public final static DateTimeFormatter DATE_TIME_FMT_2 = DateTimeFormatter.ofPattern("yyyyMMdd HHmmssSSS");
    public final static DateTimeFormatter TIME_FMT_0 = DateTimeFormatter.ofPattern("HHmmssSSS");
    public final static DateTimeFormatter TIME_FMT_1 = DateTimeFormatter.ofPattern("HHmmss");

    public DateTimeFormatterConstants() {
    }
}
