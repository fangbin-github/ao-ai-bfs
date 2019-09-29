package gov.cnao.ao.ai.bfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gov.cnao.ao.ai.bfs.entity.OperLog;
import gov.cnao.ao.ai.bfs.vo.OperLogVO;

/**
 * @author fangbin
 */

@Mapper
public interface OperLogMapper {
	
	/**
	 * 操作日志查询
	 * @param operLog
	 * @return
	 */
	List<OperLog> queryOperLog(OperLogVO operLogVO);
	
	/**
	 * 操作日志新增
	 * @param operLog
	 */
    void insertOperLog(OperLogVO operLogVO);

    /**
     * 操作日志修改
     * @param operLog
     */
    void updateOperLog(OperLogVO operLogVO);
    
    /**
     * 操作日志删除
     * @param operLog
     * @return
     */
    int deleteOperLog(OperLogVO operLogVO);
    
    /**
     * 操作日志选择性新增
     * @param operLog
     */
    void insertSelective(OperLogVO operLogVO);

    /**
     * 操作日志选择性修改
     * @param operLog
     */
    void updateByPrimaryKeySelective(OperLogVO operLogVO);

    /**
     * 分页查询操作日志
     * @param operLogVO
     * @return
     */
	List<OperLog> queryOperLogPage(OperLogVO operLogVO);

	/**
	 * 查询操作日志总数
	 * @param operLogVO
	 * @return
	 */
	int queryOperLogCount(OperLogVO operLogVO);

}