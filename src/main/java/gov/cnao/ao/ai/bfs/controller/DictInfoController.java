package gov.cnao.ao.ai.bfs.controller;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.contract.IDictInfo;
import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.service.DictInfoService;
import gov.cnao.ao.ai.bfs.vo.DictInfoVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;

@RestSchema(schemaId = "idictinfo")
@RequestMapping(path = "/dict")
public class DictInfoController implements IDictInfo{
	
	private final static org.slf4j.Logger log = LoggerFactory.getLogger(DictInfoController.class);
	
	@Autowired
	private DictInfoService dictInfoService;

	/**
	 * 查询字典信息
	 */
	@Override
    @RequestMapping(value = "/queryDictInfo", method = RequestMethod.POST)
	public List<DictInfo> queryDictInfo(@RequestBody DictInfoVO dictInfoVO) {
		log.info("Access /IDictInfo/queryDictInfo -- 查询字典信息");
		return dictInfoService.queryDictInfo(dictInfoVO);
	}
	/**
	 * 根据ID查询字典信息名称
	 */
	@Override
    @RequestMapping(value = "/queryDictInfoById", method = RequestMethod.POST)
	public String queryDictInfoById(@RequestBody DictInfoVO dictInfoVO) {
		log.info("Access /IDictInfo/queryDictInfoById -- 根据ID查询字典信息名称");
		return dictInfoService.queryDictInfoById(dictInfoVO);
	}
	/**
	 * 根据字典信息名称查询ID
	 */
	@Override
    @RequestMapping(value = "/queryDictInfoByName", method = RequestMethod.POST)
	public String queryDictInfoByName(@RequestBody DictInfoVO dictInfoVO) {
		log.info("Access /IDictInfo/queryDictInfoByName -- 根据字典信息名称查询ID");
		return dictInfoService.queryDictInfoByName(dictInfoVO);
	}
	/**
	 * 新增字典信息
	 */
	@Override
	@RequestMapping(path = "/insertDictInfo", method = RequestMethod.POST)
	public BaseResponse<DictInfoVO> insertDictInfo(@RequestBody DictInfoVO dictInfoVO) {
		log.info("Access /IDictInfo/insertDictInfo -- 新增字典信息");
		return dictInfoService.insertDictInfo(dictInfoVO);
	}
	
	/**
	 * 修改字典信息
	 */
	@Override
	@RequestMapping(path = "/updateDictInfo", method = RequestMethod.POST)
	public BaseResponse<DictInfoVO> updateDictInfo(@RequestBody DictInfoVO dictInfoVO) {
		log.info("Access /IDictInfo/updateDictInfo -- 修改字典信息");
		return dictInfoService.updateDictInfo(dictInfoVO);
	}
	
	/**
	 * 删除字典信息
	 */
	@Override
	@RequestMapping(path = "/deleteDictInfo", method = RequestMethod.POST)
	public BaseResponse<Integer> deleteDictInfo(@RequestBody DictInfoVO dictInfoVO) {
		log.info("Access /IDictInfo/deleteDictInfo -- 删除字典信息");
		return dictInfoService.deleteDictInfo(dictInfoVO);
	}
	
	/**
	 * 通过字典项代码查询字典信息
	 */
	@RequestMapping(path = "/queryDictInfoByDictCd", method = RequestMethod.POST)
	public DictInfo queryDictInfoByDictCd(@RequestBody DictInfoVO dictInfoVO) {
		log.info("Access /IDictInfo/queryDictInfoByDictCd -- 通过字典项代码查询字典信息");
		return dictInfoService.queryDictInfoByDictCd(dictInfoVO);
	}
	
	/**
	 * 分页查询字典信息列表
	 */
	@RequestMapping(path = "/queryDictInfoPage", method = RequestMethod.POST)
	public BaseResponse<PageBean> queryDictInfoPage(@RequestBody DictInfoVO dictInfoVO) {
		log.info("Access /IDictInfo/queryDictInfoPage -- 分页查询字典信息列表");
		return dictInfoService.queryDictInfoPage(dictInfoVO);
	}
	
}

