package gov.cnao.ao.ai.bfs.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.contract.IDictType;
import gov.cnao.ao.ai.bfs.entity.DictType;
import gov.cnao.ao.ai.bfs.service.DictTypeService;
import gov.cnao.ao.ai.bfs.vo.DictTypeTreeVO;
import gov.cnao.ao.ai.bfs.vo.DictTypeVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;

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
	public List<Map<String, Object>> queryDictTypeCon(@RequestBody DictTypeVO dictTypeVO) {
		log.info("Access /IDictType/queryDictTypeCon -- 查询字典类别目录信息");
	    return dictTypeService.queryDictTypeCon(dictTypeVO);
	}
	
	/**
	 * 查询字典类别信息
	 */
	@Override
    @RequestMapping(value = "/queryDictType", method = RequestMethod.POST)
	public List<DictType> queryDictType(@RequestBody DictTypeVO dictTypeVO) {
		log.info("Access /IDictType/queryDictType -- 查询字典类别信息");
	    return dictTypeService.queryDictType(dictTypeVO);
	}
	/**
	 * 新增字典类别信息
	 */
	@Override
	@RequestMapping(path = "/insertDictType", method = RequestMethod.POST)
	public BaseResponse<DictTypeVO> insertDictType(@RequestBody DictTypeVO dictTypeVO) {
		log.info("Access /IDictType/insertDictType -- 新增字典类别信息");
		return dictTypeService.insertDictType(dictTypeVO);
	}
	
	/**
	 * 修改字典类别信息
	 */
	@Override
	@RequestMapping(path = "/updateDictType", method = RequestMethod.POST)
	public BaseResponse<DictTypeVO> updateDictType(@RequestBody DictTypeVO dictTypeVO) {
		log.info("Access /IDictType/updateDictType -- 修改字典类别信息");
		return dictTypeService.updateDictType(dictTypeVO);
	}
	/**
	 * 删除字典类别信息
	 * @throws IOException 
	 */
	@Override
	@RequestMapping(path = "/deleteDictType", method = RequestMethod.POST)
	public BaseResponse<Integer> deleteDictType(@RequestBody DictTypeVO dictTypeVO) {
		log.info("Access /IDictType/deleteDictType -- 删除字典类别信息");
    	return dictTypeService.deleteDictType(dictTypeVO);
    }
	
	/**
	 * 通过字典类型代码查询字典类型信息
	 */
	@Override
	@RequestMapping(path = "/queryDictTypeByDictTypeId", method = RequestMethod.POST)
	public DictType queryDictTypeByDictTypeId(@RequestBody DictTypeVO dictTypeVO) {
		log.info("Access /IDictType/queryDictTypeByDictTypeId -- 通过字典类型代码查询字典类型信息");
		return dictTypeService.queryDictTypeByDictTypeId(dictTypeVO);
	}
	
	/**
	 * 分页查询字典类别信息列表
	 */
	@Override
	@RequestMapping(path = "/queryDictTypePage", method = RequestMethod.POST)
	public BaseResponse<PageBean> queryDictTypePage(@RequestBody DictTypeVO dictTypeVO) {
		log.info("Access /IDictType/queryDictTypePage -- 分页查询字典类别信息列表");
		return dictTypeService.queryDictTypePage(dictTypeVO);
	}
	
	/**
	 * 查询数据字典树信息
	 * @return
	 */
	@Override
	@RequestMapping(path = "/queryDictTypeTree", method = RequestMethod.POST)
	public BaseResponse<List<DictTypeTreeVO>> queryDictTypeTree(@RequestBody DictTypeVO dictTypeVO) {
		log.info("Access /IDictType/queryDictTypeTree -- 查询数据字典树信息");
		return dictTypeService.queryDictTypeTree(dictTypeVO);
	}
}
