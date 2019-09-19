package gov.cnao.ao.ai.bfs.controller;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.contract.IDictInfo;
import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.service.DictInfoService;

@RestSchema(schemaId = "iDictInfo")
@RequestMapping(path = "/dict")
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
		log.info("Access /IDictInfo/queryDictInfo -- 查询字典信息");
		return dictInfoService.queryDictInfo(dictInfo);
	}
	/**
	 * 根据ID查询字典信息名称
	 */
	@Override
    @RequestMapping(value = "/queryDictInfoById", method = RequestMethod.POST)
	public String queryDictInfoById(@RequestBody DictInfo dictInfo) {
		log.info("Access /IDictInfo/queryDictInfoById -- 根据ID查询字典信息名称");
		return dictInfoService.queryDictInfoById(dictInfo);
	}
	/**
	 * 根据字典信息名称查询ID
	 */
	@Override
    @RequestMapping(value = "/queryDictInfoByName", method = RequestMethod.POST)
	public String queryDictInfoByName(@RequestBody DictInfo dictInfo) {
		log.info("Access /IDictInfo/queryDictInfoByName -- 根据字典信息名称查询ID");
		return dictInfoService.queryDictInfoByName(dictInfo);
	}
	/**
	 * 新增字典信息
	 */
	@Override
	@RequestMapping(path = "/insertDictInfo", method = RequestMethod.POST)
	public DictInfo insertDictInfo(@RequestBody  DictInfo dictInfo) {
		log.info("Access /IDictInfo/insertDictInfo -- 新增字典信息");
		return dictInfoService.insertDictInfo(dictInfo);
	}
	
	/**
	 * 修改字典信息
	 */
	@Override
	@RequestMapping(path = "/updateDictInfo", method = RequestMethod.POST)
	public DictInfo updateDictInfo(@RequestBody DictInfo dictInfo) {
		log.info("Access /IDictInfo/updateDictInfo -- 修改字典信息");
		return dictInfoService.updateDictInfo(dictInfo);
	}
	
	/**
	 * 删除字典信息
	 */
	@Override
	@RequestMapping(path = "/deleteDictInfo", method = RequestMethod.POST)
	public int deleteDictInfo(@RequestBody List<DictInfo> list) {
		log.info("Access /IDictInfo/deleteDictInfo -- 删除字典信息");
		return dictInfoService.deleteDictInfo(list);
	}
	
	/**
	 * 通过字典项代码查询字典信息
	 */
	@RequestMapping(path = "/queryDictInfoByDictCd", method = RequestMethod.POST)
	public DictInfo queryDictInfoByDictCd(@RequestBody DictInfo dictInfo) {
		log.info("Access /IDictInfo/queryDictInfoByDictCd -- 通过字典项代码查询字典信息");
		return dictInfoService.queryDictInfoByDictCd(dictInfo);
	}
	
}

