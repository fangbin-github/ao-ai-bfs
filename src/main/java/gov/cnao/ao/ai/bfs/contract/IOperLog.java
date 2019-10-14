package gov.cnao.ao.ai.bfs.contract;

import java.util.List;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.entity.OperLog;
import gov.cnao.ao.ai.bfs.vo.OperLogVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;

/**
 * @author fangbin
 */
public interface IOperLog {
	
	/**
	 * 操作日志查询
	 * @param operLog
	 * @return
	 */
	List<OperLog> queryOperLog(OperLogVO operLogVO);
	
	/**
	 * 分页查询操作日志
	 * @param operLogVO
	 * @return
	 */
	BaseResponse<PageBean> queryOperLogPage(OperLogVO operLogVO);
	
	/**
	 * 操作日志新增
	 * @param operLog
	 * @return
	 */
	OperLogVO insertOperLog(OperLogVO operLogVO);
	
	/**
	 * 操作日志导出
	 * @param operLog
	 */
	List<OperLog> exportOperLog(OperLogVO operLogVO);
	
}
