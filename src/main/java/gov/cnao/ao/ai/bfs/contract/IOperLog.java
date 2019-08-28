package gov.cnao.ao.ai.bfs.contract;

import java.util.List;

import gov.cnao.ao.ai.bfs.entity.OperLog;

/**
 * @author fangbin
 */
public interface IOperLog {
	
	/**
	 * 操作日志查询
	 * @param operLog
	 * @return
	 */
	List<OperLog> queryOperLog(OperLog operLog);
	
	/**
	 * 操作日志新增
	 * @param operLog
	 * @return
	 */
	OperLog insertOperLog(OperLog operLog);
	
	/**
	 * 操作日志导出
	 * @param operLog
	 */
	void exportOperLog(OperLog operLog);
}
