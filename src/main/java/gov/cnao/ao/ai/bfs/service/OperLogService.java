package gov.cnao.ao.ai.bfs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.common.ResponseHeadUtil;
import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.entity.OperLog;
import gov.cnao.ao.ai.bfs.mapper.DictInfoMapper;
import gov.cnao.ao.ai.bfs.mapper.OperLogMapper;
import gov.cnao.ao.ai.bfs.util.CommonUtil;
import gov.cnao.ao.ai.bfs.util.DateUtil;
import gov.cnao.ao.ai.bfs.vo.DictInfoVO;
import gov.cnao.ao.ai.bfs.vo.OperLogVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;

/**
 * @author fangbin
 */

@Service
public class OperLogService {
	
	@Autowired
	private OperLogMapper operLogMapper;
	
	@Autowired
	private DictInfoMapper dictInfoMapper;
	
	private final static String LOG_TYPE = "RZLX"; 
	
	/**
	 * 操作日志查询
	 * @param operLog
	 * @return
	 */
	public List<OperLog> queryOperLog(OperLogVO operLogVO) {
		return operLogMapper.queryOperLog(operLogVO);
	}

	/**
	 * 操作日志新增
	 * @param operLog
	 * @return
	 */
	public OperLogVO insertOperLog(OperLogVO operLogVO) {
		operLogVO.setOperTm(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		operLogVO.setLogId(CommonUtil.getSeqNum());
		operLogMapper.insertOperLog(operLogVO);
		return operLogVO;
	}

	/**
	 * 操作日志导出
	 * @param operLog
	 */
	public BaseResponse<List<OperLog>> exportOperLog(OperLogVO operLogVO) {
		BaseResponse<List<OperLog>> baseResponse = 
				new BaseResponse<List<OperLog>>();
		List<OperLog> operLogs = new ArrayList<OperLog>();
		List<OperLog> list = operLogMapper.queryOperLog(operLogVO);
		for (OperLog operLog : list) {
			DictInfoVO dict = new DictInfoVO();
			dict.setDictCd(operLog.getLogType());
			List<DictInfo> dictInfos = dictInfoMapper.queryDictInfo(dict);
			operLog.setLogTypeNm(dictInfos.get(0).getDictNm());
			operLogs.add(operLog);
		}
		baseResponse.setBody(operLogs);
		baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(operLogVO));
		return baseResponse;
	}

	/**
	 * 分页查询操作日志
	 * @param operLogVO
	 * @return
	 */
	public BaseResponse<PageBean> queryOperLogPage(OperLogVO operLogVO) {
		BaseResponse<PageBean> baseResponse = 
					new BaseResponse<PageBean>();
		PageBean pageBean = new PageBean();
		if(operLogVO.getHead().getPgrw() != null && operLogVO.getHead().getPgsn() != null) {
			pageBean = new PageBean(
					operLogVO.getHead().getPgsn(), 
					operLogVO.getHead().getPgrw(), 
						operLogMapper.queryOperLogCount(operLogVO));
			operLogVO.getHead().setPgsn((operLogVO.getHead().getPgsn() -1)*operLogVO.getHead().getPgrw());
			List<OperLog> list = operLogMapper.queryOperLogPage(operLogVO);
			List<OperLog> operLogs = new ArrayList<OperLog>(); 
			for (OperLog operLog : list) {
				DictInfoVO dict = new DictInfoVO();
				dict.setDictCd(operLog.getLogType());
				dict.setDictTypeId(LOG_TYPE);
				List<DictInfo> dictInfos = dictInfoMapper.queryDictInfo(dict);
				operLog.setLogTypeNm(dictInfos.get(0).getDictNm());
				operLogs.add(operLog);
			}
			pageBean.setContent(operLogs);
		}
		baseResponse.setBody(pageBean);
		baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(operLogVO));
		return baseResponse;
	}

}
