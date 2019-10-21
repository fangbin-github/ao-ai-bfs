package gov.cnao.ao.ai.bfs.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.common.ResponseHeadUtil;
import gov.cnao.ao.ai.bfs.common.RetCodeEnum;
import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.entity.OperLog;
import gov.cnao.ao.ai.bfs.mapper.DictInfoMapper;
import gov.cnao.ao.ai.bfs.mapper.OperLogMapper;
import gov.cnao.ao.ai.bfs.util.CommonUtil;
import gov.cnao.ao.ai.bfs.util.DateUtil;
import gov.cnao.ao.ai.bfs.util.excelUtil;
import gov.cnao.ao.ai.bfs.vo.DictInfoVO;
import gov.cnao.ao.ai.bfs.vo.OperLogVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;

/**
 * @author fangbin
 */

@Service
public class OperLogService {
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(OperLogService.class);

	@Autowired
	private OperLogMapper operLogMapper;
	
	@Autowired
	private DictInfoMapper dictInfoMapper;
	
	/**
	 * 操作日志查询
	 * @param operLog
	 * @return
	 */
	public List<OperLog> queryOperLog(OperLogVO operLogVO) {
		try {
			return operLogMapper.queryOperLog(operLogVO);
		} catch (Exception e) {
			log.error("操作日志查询失败", e);
		}
		return null;
	}

	/**
	 * 操作日志新增
	 * @param operLog
	 * @return
	 */
	public OperLogVO insertOperLog(OperLogVO operLogVO) {
		try {
			operLogVO.setOperTm(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			operLogVO.setLogId(CommonUtil.getSeqNum());
			operLogMapper.insertOperLog(operLogVO);
			return operLogVO;
		} catch (Exception e) {
			log.error("操作日志新增失败", e);
		}
		return null;
	}

	/**
	 * 操作日志导出
	 * @param operLog
	 */
	public BaseResponse<List<OperLog>> exportOperLog(OperLogVO operLogVO) {
		BaseResponse<List<OperLog>> baseResponse = 
				new BaseResponse<List<OperLog>>();
		try {
			List<OperLog> operLogs = new ArrayList<OperLog>();
			List<OperLog> list = operLogMapper.queryOperLog(operLogVO);
			for (OperLog operLog : list) {
				DictInfoVO dictInfoVO = new DictInfoVO();
				dictInfoVO.setDictCd(operLog.getLogType());
				List<DictInfo> dictInfos = dictInfoMapper.queryDictInfo(dictInfoVO);
				operLog.setLogTypeNm(dictInfos.get(0).getDictNm());
				operLogs.add(operLog);
			}
			baseResponse.setBody(operLogs);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(operLogVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(operLogVO, RetCodeEnum.SYS_ERROR));
			log.error("操作日志导出失败", e);
		}
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
		try {
			if(operLogVO.getHead().getPgrw() != null && operLogVO.getHead().getPgsn() != null) {
				pageBean = new PageBean(
						operLogVO.getHead().getPgsn(), 
						operLogVO.getHead().getPgrw(), 
							operLogMapper.queryOperLogCount(operLogVO));
				operLogVO.getHead().setPgsn((operLogVO.getHead().getPgsn() -1)*operLogVO.getHead().getPgrw());
				List<OperLog> list = operLogMapper.queryOperLogPage(operLogVO);
				List<OperLog> operLogs = new ArrayList<OperLog>(); 
				for (OperLog operLog : list) {
					DictInfoVO dictInfoVO = new DictInfoVO();
					dictInfoVO.setDictCd(operLog.getLogType());
					List<DictInfo> dictInfos = dictInfoMapper.queryDictInfo(dictInfoVO);
					operLog.setLogTypeNm(dictInfos.get(0).getDictNm());
					operLogs.add(operLog);
				}
				pageBean.setContent(operLogs);
			}
			baseResponse.setBody(pageBean);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(operLogVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(operLogVO, RetCodeEnum.SYS_ERROR));
			log.error("分页查询操作日志失败", e);
		}
		return baseResponse;
	}

}
