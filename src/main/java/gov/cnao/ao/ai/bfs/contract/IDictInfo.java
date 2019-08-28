package gov.cnao.ao.ai.bfs.contract;

import java.util.List;

import gov.cnao.ao.ai.bfs.entity.DictInfo;

public interface IDictInfo {
	 /**
     * 查询字典列表
     */
	List<DictInfo> queryDictInfo(DictInfo dictInfo);
	/**
	 * 新增字典信息
	 */
	DictInfo insertDictInfo(DictInfo dictInfo);
	/**
	 * 修改字典信息
	 */
	DictInfo updateDictInfo(DictInfo dictInfo);
	/**
	 * 删除字典信息
	 */
	int deleteDictInfo(DictInfo dictInfo);
	
}