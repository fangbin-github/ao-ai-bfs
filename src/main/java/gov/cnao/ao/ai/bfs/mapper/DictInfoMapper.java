package gov.cnao.ao.ai.bfs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.entity.DictType;
@Mapper
public interface DictInfoMapper {

	/**
     * 新增字典信息
     */
	void insertDictInfo(DictInfo dictInfo);
	/**
	 * 修改字典信息
	 */
	void updateDictInfo(DictInfo dictInfo);
	
	/**
	 * 查询字典信息列表
	 */
	List<DictInfo> queryDictInfo(DictInfo dictInfo);
	/**
	 * 删除数据字典
	 */
	int deleteDictInfo(DictInfo dictInfo);
	/**
	 * 根据ID查询字典信息名称
	 */
	String queryDictInfoById(DictInfo dictInfo);
	/**
	 * 根据字典信息名称查询ID
	 */
	String queryDictInfoByName(DictInfo dictInfo);
	
	/**
	 * 根据数据字典类型删除数据字典信息
	 * @param dictInfo
	 */
	void deleteDictInfoByTypeId(DictInfo dictInfo);
}