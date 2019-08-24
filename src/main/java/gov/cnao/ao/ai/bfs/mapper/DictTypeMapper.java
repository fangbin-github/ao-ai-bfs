package gov.cnao.ao.ai.bfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.entity.DictType;
@Mapper
public interface DictTypeMapper {
    
    /**
     * 查询字典类别信息
     */
    List<DictType> queryDictType(DictType dictType);
    /**
     * 新增字典类别信息
     */
	void insertDictType(DictType dictType);
	/**
	 * 修改字典类别信息
	 */
	void updateDictType(DictType dictType);
	/**
	 * 删除字典类别信息
	 */
	int deleteDictType(DictType dictType);
    
}