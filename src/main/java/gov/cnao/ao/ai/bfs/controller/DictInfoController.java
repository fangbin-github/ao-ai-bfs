package gov.cnao.ao.ai.bfs.controller;

import java.util.List;
import java.util.Map;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gov.cnao.ao.ai.bfs.contract.IDictInfo;
import gov.cnao.ao.ai.bfs.contract.IDictType;
import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.entity.DictType;
import gov.cnao.ao.ai.bfs.service.DictInfoService;
import gov.cnao.ao.ai.bfs.service.DictTypeService;

@RestSchema(schemaId = "iDictInfo")
@RequestMapping(path = "/ao-ai-bfs/dict")
public class DictInfoController implements IDictInfo{
	private static org.slf4j.Logger log = LoggerFactory.getLogger(DictInfoController.class);
	@Autowired
	private DictInfoService dictInfoService;

	/**
	 * 查询字典信息
	 */
	@Override
    @RequestMapping(value = "/queryDictInfo", method = RequestMethod.POST)
	public List<DictInfo> queryDictInfo(@RequestBody DictInfo dictInfo) {
		return dictInfoService.queryDictInfo(dictInfo);
	}
	/**
	 * 根据ID查询字典信息名称
	 */
	@Override
    @RequestMapping(value = "/queryDictInfoById", method = RequestMethod.POST)
	public List<DictInfo> queryDictInfoById(@RequestBody DictInfo dictInfo) {
		return dictInfoService.queryDictInfoById(dictInfo);
	}
	/**
	 * 根据字典信息名称查询ID
	 */
	@Override
    @RequestMapping(value = "/queryDictInfoByName", method = RequestMethod.POST)
	public List<DictInfo> queryDictInfoByName(@RequestBody DictInfo dictInfo) {
		return dictInfoService.queryDictInfoByName(dictInfo);
	}
	/**
	 * 新增字典信息
	 */
	@Override
	@RequestMapping(path = "/insertDictInfo", method = RequestMethod.POST)
	public DictInfo insertDictInfo(@RequestBody  DictInfo dictInfo) {
		return dictInfoService.insertDictInfo(dictInfo);
	}
	
	/**
	 * 修改字典信息
	 */
	@Override
	@RequestMapping(path = "/updateDictInfo", method = RequestMethod.POST)
	public DictInfo updateDictInfo(@RequestBody DictInfo dictInfo) {
		return dictInfoService.updateDictInfo(dictInfo);
	}
	/**
	 * 删除字典信息
	 */
	@Override
	@RequestMapping(path = "/deleteDictInfo", method = RequestMethod.POST)
	public int deleteDictInfo(@RequestBody DictInfo dictInfo) {
    	return dictInfoService.deleteDictInfo(dictInfo);
    }
}

