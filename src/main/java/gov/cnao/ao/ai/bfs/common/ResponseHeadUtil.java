package gov.cnao.ao.ai.bfs.common;

import java.time.LocalDateTime;

import gov.cnao.ao.ai.bfs.vo.BaseRequest;

/**
 * @author:zhangguoqing 
 * @version:2019年10月9日下午5:14:17
*/
public class ResponseHeadUtil {
    private ResponseHeadUtil() {
    }

    public static ResponseHead buildSuccessHead(BaseRequest request) {
        return new ResponseHead("S","aoai",request.getHead().getTrno(),RetCodeEnum.SUCCESS.code,RetCodeEnum.SUCCESS.msg, LocalDateTime.now().format(DateTimeFormatterConstants.DATE_TIME_FMT_2));
    }

    public static ResponseHead buildFailHead(BaseRequest request, RetCodeEnum retCodeEnum) {
        return new ResponseHead("F","aoai",request.getHead().getTrno(),retCodeEnum.code,retCodeEnum.msg, LocalDateTime.now().format(DateTimeFormatterConstants.DATE_TIME_FMT_2));
    }
}
