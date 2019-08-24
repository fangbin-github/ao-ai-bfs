package gov.cnao.ao.ai.bfs.mapper;

import java.util.List;

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
}