package gov.cnao.ao.ai.bfs.controller;

import java.util.List;
import java.util.Map;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.contract.IDictType;
import gov.cnao.ao.ai.bfs.entity.DictType;
import gov.cnao.ao.ai.bfs.service.DictTypeService;

@RestSchema(schemaId = "iDictType")
@RequestMapping(path = "/dict")
public class DictTypeController implements IDictType{
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(DictTypeController.class);
	
	@Autowired
	private DictTypeService dictTypeService;
	
	/**
	 * 查询字典类别目录信息
	 */
	@Override
    @RequestMapping(value = "/queryDictTypeCon", method = RequestMethod.POST)
	public List<Map<String, Object>> queryDictTypeCon(@RequestBody DictType dictType) {
		log.info("Access /IDictType/queryDictTypeCon -- 查询字典类别目录信息");
	    return dictTypeService.queryDictTypeCon(dictType);
	}
	/**
	 * 查询字典类别信息
	 */
	@Override
    @RequestMapping(value = "/queryDictType", method = RequestMethod.POST)
	public List<DictType> queryDictType(@RequestBody DictType dictType) {
		log.info("Access /IDictType/queryDictType -- 查询字典类别信息");
	    return dictTypeService.queryDictType(dictType);
	}
	/**
	 * 新增字典类别信息
	 */
	@Override
	@RequestMapping(path = "/insertDictType", method = RequestMethod.POST)
	public DictType insertDictType(@RequestBody DictType dictType) {
		log.info("Access /IDictType/insertDictType -- 新增字典类别信息");
		return dictTypeService.insertDictType(dictType);
	}
	
	/**
	 * 修改字典类别信息
	 */
	@Override
	@RequestMapping(path = "/updateDictType", method = RequestMethod.POST)
	public DictType updateDictType(@RequestBody DictType dictType) {
		log.info("Access /IDictType/updateDictType -- 修改字典类别信息");
		return dictTypeService.updateDictType(dictType);
	}
	/**
	 * 删除字典类别信息
	 */
	@Override
	@RequestMapping(path = "/deleteDictType", method = RequestMethod.POST)
	public int deleteDictType(@RequestBody List<DictType> list) {
		log.info("Access /IDictType/deleteDictType -- 删除字典类别信息");
    	return dictTypeService.deleteDictType(list);
    }
	
	/**
	 * 通过字典类型代码查询字典类型信息
	 */
	@Override
	@RequestMapping(path = "/queryDictTypeByDictTypeId", method = RequestMethod.POST)
	public DictType queryDictTypeByDictTypeId(@RequestBody DictType dictType) {
		log.info("Access /IDictType/queryDictTypeByDictTypeId -- 通过字典类型代码查询字典类型信息");
		return dictTypeService.queryDictTypeByDictTypeId(dictType);
	}
}
