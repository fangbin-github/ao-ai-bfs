package gov.cnao.ao.ai.bfs.controller;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.contract.IOperLog;
import gov.cnao.ao.ai.bfs.entity.OperLog;
import gov.cnao.ao.ai.bfs.service.OperLogService;

/**
 * @author fangbin
 */

@RestSchema(schemaId = "iOperLog")
@RequestMapping(path = "/ao-ai-bfs/log")
public class OperLogController implements IOperLog {
	
	@Autowired
	private OperLogService operLogService;
	
	/**
	 * 操作日志查询
	 */
	@Override
	@RequestMapping(path = "/queryOperLog", method = RequestMethod.POST)
	public List<OperLog> queryOperLog(@RequestBody OperLog operLog) {
		return operLogService.queryOperLog(operLog);
	}

	/**
	 * 操作日志新增
	 */
	@Override
	@RequestMapping(path = "/insertOperLog", method = RequestMethod.POST)
	public OperLog insertOperLog(@RequestBody OperLog operLog) {
		return operLogService.insertOperLog(operLog);
	}

	/**
	 * 操作日志导出
	 */
	@Override
	@RequestMapping(path = "/exportOperLog", method = RequestMethod.POST)
	public void exportOperLog() {
		operLogService.exportOperLog();
	}

}
