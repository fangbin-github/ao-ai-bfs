package gov.cnao.ao.ai.bfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gov.cnao.ao.ai.bfs.entity.OperLog;

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
	List<OperLog> queryOperLog(OperLog operLog);
	
	/**
	 * 操作日志新增
	 * @param operLog
	 */
    void insertOperLog(OperLog operLog);

    /**
     * 操作日志修改
     * @param operLog
     */
    void updateOperLog(OperLog operLog);
    
    /**
     * 操作日志删除
     * @param operLog
     * @return
     */
    int deleteOperLog(OperLog operLog);
    
    /**
     * 操作日志选择性新增
     * @param operLog
     */
    void insertSelective(OperLog operLog);

    /**
     * 操作日志选择性修改
     * @param operLog
     */
    void updateByPrimaryKeySelective(OperLog operLog);

}