package gov.cnao.ao.ai.bfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.vo.DictInfoVO;
import gov.cnao.ao.ai.bfs.vo.InfoVO;
@Mapper
public interface DictInfoMapper {

	/**
     * 新增字典信息
     */
	void insertDictInfo(DictInfoVO dictInfoVO);
	/**
	 * 修改字典信息
	 */
	void updateDictInfo(DictInfoVO dictInfoVO);
	
	/**
	 * 查询字典信息列表
	 */
	List<DictInfo> queryDictInfo(DictInfoVO dictInfoVO);
	/**
	 * 删除数据字典
	 */
	void deleteDictInfo(InfoVO infoVO);
	/**
	 * 根据ID查询字典信息名称
	 */
	String queryDictInfoById(DictInfoVO dictInfoVO);
	/**
	 * 根据字典信息名称查询ID
	 */
	String queryDictInfoByName(DictInfoVO dictInfoVO);
	
	/**
	 * 根据数据字典类型删除数据字典信息
	 * @param dictInfo
	 */
	void deleteDictInfoByTypeId(InfoVO infoVO);
	
	/**
	 * 分页查询字典信息列表
	 * @param dictInfoVO
	 * @return
	 */
	List<DictInfo> queryDictInfoPage(DictInfoVO dictInfoVO);
	
	/**
	 * 查询数据字典信息总数
	 * @param dictInfoVO
	 * @return
	 */
	int queryDictInfoCount(DictInfoVO dictInfoVO);
}