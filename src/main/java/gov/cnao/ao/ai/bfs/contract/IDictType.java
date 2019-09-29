package gov.cnao.ao.ai.bfs.contract;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import gov.cnao.ao.ai.bfs.entity.DictType;
import gov.cnao.ao.ai.bfs.vo.DictTypeVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;


public interface IDictType {
	/**
	 * 通过字典类型代码查询字典类型信息
	 * @param dictType
	 * @return
	 */
	DictType queryDictTypeByDictTypeId(DictTypeVO dictTypeVO);
	 /**
     * 查询字典类别目录
     */
	List<Map<String, Object>> queryDictTypeCon(DictTypeVO dictTypeVO);
	 /**
     * 查询字典类别信息列表
     */
	List<DictType> queryDictType(DictTypeVO dictTypeVO);
	
	/**
	 * 分页查询字典类别信息列表
	 * @param dictType
	 * @return
	 */
	PageBean queryDictTypePage(DictTypeVO dictTypeVO);
	
	/**
	 * 新增字典类别信息
	 */
	DictTypeVO insertDictType(DictTypeVO dictTypeVO);
	/**
	 * 修改字典类别信息
	 */
	DictTypeVO updateDictType(DictTypeVO dictTypeVO);
	/**
	 * 删除字典类别信息
	 * @throws IOException 
	 */
	int deleteDictType(DictTypeVO dictTypeVO);
	
}
