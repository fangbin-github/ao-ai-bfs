package gov.cnao.ao.ai.bfs.contract;

import java.util.List;

import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.vo.DictInfoVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;

public interface IDictInfo {
	
	/**
	 * 通过字典项代码查询字典信息
	 * @param dictInfo
	 * @return
	 */
	DictInfo queryDictInfoByDictCd(DictInfoVO dictInfoVO);
	 /**
     * 查询字典列表
     */
	List<DictInfo> queryDictInfo(DictInfoVO dictInfoVO);
	
	/**
	 * 分页查询字典信息列表
	 * @param dictInfo
	 * @return
	 */
	PageBean queryDictInfoPage(DictInfoVO dictInfoVO);
	
	
	/**
	 * 根据ID查询字典信息名称
	 */
	String queryDictInfoById(DictInfoVO dictInfoVO);
	/**
	 * 根据字典信息名称查询ID
	 */
	String queryDictInfoByName(DictInfoVO dictInfoVO);
	/**
	 * 新增字典信息
	 */
	DictInfoVO insertDictInfo(DictInfoVO dictInfoVO);
	/**
	 * 修改字典信息
	 */
	DictInfoVO updateDictInfo(DictInfoVO dictInfoVO);
	/**
	 * 删除字典信息
	 */
	int deleteDictInfo(DictInfoVO dictInfoVO);
	
}
