package gov.cnao.ao.ai.bfs.controller;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.contract.IOperLog;
import gov.cnao.ao.ai.bfs.entity.OperLog;
import gov.cnao.ao.ai.bfs.service.OperLogService;
import gov.cnao.ao.ai.bfs.vo.OperLogVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;

/**
 * @author fangbin
 */

@RestSchema(schemaId = "iOperLog")
@RequestMapping(path = "/log")
public class OperLogController implements IOperLog {
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(OperLogController.class);
	
	@Autowired
	private OperLogService operLogService;
	
	/**
	 * 操作日志查询
	 */
	@Override
	@RequestMapping(path = "/queryOperLog", method = RequestMethod.POST)
	public List<OperLog> queryOperLog(@RequestBody OperLogVO operLogVO) {
		log.info("Access /IOperLog/queryOperLog -- 操作日志查询");
		return operLogService.queryOperLog(operLogVO);
	}

	/**
	 * 操作日志新增
	 */
	@Override
	@RequestMapping(path = "/insertOperLog", method = RequestMethod.POST)
	public OperLogVO insertOperLog(@RequestBody OperLogVO operLogVO) {
		log.info("Access /IOperLog/insertOperLog -- 操作日志新增");
		return operLogService.insertOperLog(operLogVO);
	}

	/**
	 * 操作日志导出
	 */
	@Override
	@RequestMapping(path = "/exportOperLog", method = RequestMethod.POST)
	public BaseResponse<List<OperLog>> exportOperLog(@RequestBody OperLogVO operLogVO) {
		log.info("Access /IOperLog/exportOperLog -- 操作日志导出");
		return operLogService.exportOperLog(operLogVO);
	}

	/**
	 * 分页查询操作日志
	 */
	@RequestMapping(path = "/queryOperLogPage", method = RequestMethod.POST)
	public BaseResponse<PageBean> queryOperLogPage(@RequestBody OperLogVO operLogVO) {
		log.info("Access /IOperLog/queryOperLogPage -- 分页查询操作日志");
		return operLogService.queryOperLogPage(operLogVO);
	}
	
}
