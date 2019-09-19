package gov.cnao.ao.ai.bfs.service;


import java.text.ParseException;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.mapper.DictInfoMapper;
import gov.cnao.ao.ai.bfs.util.DateTimeUtil;

@Service
public class DictInfoService {
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(DictInfoService.class);
	
	@Autowired
	private DictInfoMapper dictInfoMapper;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	/**
	 *查询字典信息列表
	 * @throws ParseException 
	 */
	public List<DictInfo> queryDictInfo(DictInfo dictInfo){
		try {
			List<DictInfo> list = dictInfoMapper.queryDictInfo(dictInfo);
			return list;
		} catch (Exception e) {
			log.error("查询字典信息列表失败", e);
		}
		return null;
	}
	
	/**
	 * 根据ID查询字典信息名称  
	 */
	public String queryDictInfoById(DictInfo dictInfo){
		try {
			String dictNm = dictInfoMapper.queryDictInfoById(dictInfo);
			return dictNm;
		} catch (Exception e) {
			log.error("根据ID查询字典信息名称失败", e);
		}
		return null;
	}
	
	/**
	 * 根据字典信息名称查询ID
	 */
	public String queryDictInfoByName(DictInfo dictInfo){
		try {
			String dictCd = dictInfoMapper.queryDictInfoByName(dictInfo);
			return dictCd;
		} catch (Exception e) {
			log.error("根据字典信息名称查询ID失败", e);
		}
		return null;
	}
	
	/**
	 * 删除字典信息
	 */
	public int deleteDictInfo(List<DictInfo> list) {
		int num =0;
		try {
			for (int i = 0; i < list.size(); i++) {
				DictInfo dictInfo = list.get(i);
				dictInfoMapper.deleteDictInfo(dictInfo);
				num++;
			}
		} catch (Exception e) {
			log.error("删除字典信息失败", e);
		}
		return num;
	}
	
	
    /**
	 * 新增字典信息
	 */
	public DictInfo insertDictInfo(DictInfo dictInfo) {
		try {
			dictInfo.setCreateTms(DateTimeUtil.getCurrentTime());
			stringRedisTemplate.opsForValue().set(dictInfo.getDictCd(), dictInfo.getDictNm());
			dictInfoMapper.insertDictInfo(dictInfo);
			return dictInfo;
		} catch (Exception e) {
			log.error("新增字典信息失败", e);
		}
		return null;
	}
	
	/**
	 * 修改字典信息
	 */
	public DictInfo updateDictInfo(DictInfo dictInfo) {
		try {
			dictInfo.setUpdateTm(DateTimeUtil.getCurrentTime());
			dictInfoMapper.updateDictInfo(dictInfo);
			return dictInfo;
		} catch (Exception e) {
			log.error("修改字典信息失败", e);
		}
		return null;
	}
	
	/**
	 * 通过字典项代码查询字典信息
	 * @param dictInfo
	 * @return
	 */
	public DictInfo queryDictInfoByDictCd(DictInfo dictInfo) {
		try {
			List<DictInfo> list = dictInfoMapper.queryDictInfo(dictInfo);
			if(list.size()>0){
				return list.get(0);
			}
		} catch (Exception e) {
			log.error("修改字典信息失败", e);
		}
		return null;
	}
	
}
