package gov.cnao.ao.ai.bfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import gov.cnao.ao.ai.bfs.entity.DictType;
import gov.cnao.ao.ai.bfs.vo.DictTypeVO;
import gov.cnao.ao.ai.bfs.vo.TypeVO;
@Mapper
public interface DictTypeMapper {
	 /**
     * 查询字典类别目录信息
     */
    List<DictType> queryDictTypeConent(DictTypeVO dictTypeVO);
    /**
     * 查询字典类别信息
     */
    List<DictType> queryDictType(DictTypeVO dictTypeVO);
    /**
     * 新增字典类别信息
     */
	void insertDictType(DictTypeVO dictTypeVO);
	/**
	 * 修改字典类别信息
	 */
	void updateDictType(DictTypeVO dictTypeVO);
	/**
	 * 删除字典类别信息
	 */
	void deleteDictType(TypeVO typeVO);
	
	/**
	 * 查询数据字典类型总数
	 * @param dictType
	 * @return
	 */
	int queryDictTypeCount(DictTypeVO dictTypeVO);
	
	/**
	 * 
	 * @param dictType
	 * @return
	 */
	List<DictType> queryDictTypePage(DictTypeVO dictTypeVO);
    
}