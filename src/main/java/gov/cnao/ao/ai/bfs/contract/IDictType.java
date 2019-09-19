package gov.cnao.ao.ai.bfs.contract;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.entity.DictType;


public interface IDictType {
	/**
	 * 通过字典类型代码查询字典类型信息
	 * @param dictType
	 * @return
	 */
	DictType queryDictTypeByDictTypeId(DictType dictType);
	 /**
     * 查询字典类别目录
     */
	List<Map<String, Object>> queryDictTypeCon(DictType dictType);
	 /**
     * 查询字典类别信息列表
     */
	List<DictType> queryDictType(DictType dictType);
	/**
	 * 新增字典类别信息
	 */
	DictType insertDictType(DictType dictType);
	/**
	 * 修改字典类别信息
	 */
	DictType updateDictType(DictType dictType);
	/**
	 * 删除字典类别信息
	 */
	int deleteDictType(List<DictType> list);
	
}
