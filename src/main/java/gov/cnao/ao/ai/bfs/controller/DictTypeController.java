package gov.cnao.ao.ai.bfs.controller;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gov.cnao.ao.ai.bfs.contract.IDictType;
import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.entity.DictType;
import gov.cnao.ao.ai.bfs.service.DictTypeService;

@RestSchema(schemaId = "iDictType")
@RequestMapping(path = "/ao-ai-bfs/dict")
public class DictTypeController implements IDictType{
	private static org.slf4j.Logger log = LoggerFactory.getLogger(DictTypeController.class);
	@Autowired
	private DictTypeService dictTypeService;

	/**
	 * 查询字典类别信息
	 */
	@Override
    @RequestMapping(value = "/queryDictType", method = RequestMethod.POST)
	public List<DictType> queryDictType(@RequestBody DictType dictType) {
	        return dictTypeService.queryDictType(dictType);
	}
	/**
	 * 新增字典类别信息
	 */
	@Override
	@RequestMapping(path = "/insertDictType", method = RequestMethod.POST)
	public DictType insertDictType(@RequestBody DictType dictType) {
		return dictTypeService.insertDictType(dictType);
	}
	
	/**
	 * 修改字典类别信息
	 */
	@Override
	@RequestMapping(path = "/updateDictType", method = RequestMethod.POST)
	public DictType updateDictType(@RequestBody DictType dictType) {
		return dictTypeService.updateDictType(dictType);
	}
	/**
	 * 删除字典类别信息
	 */
	@Override
	@RequestMapping(path = "/deleteDictType", method = RequestMethod.POST)
	public int deleteDictType(@RequestBody DictType dictType) {
    	return dictTypeService.deleteDictType(dictType);
    }
}