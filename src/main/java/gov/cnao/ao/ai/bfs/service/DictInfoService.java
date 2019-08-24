package gov.cnao.ao.ai.bfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.entity.DictType;
import gov.cnao.ao.ai.bfs.mapper.DictInfoMapper;
import gov.cnao.ao.ai.bfs.mapper.DictTypeMapper;
import gov.cnao.ao.ai.bfs.util.DateTimeUtil;

@Service
public class DictInfoService {
	@Autowired
	private DictInfoMapper dictInfoMapper;
	@Autowired
	private DictTypeMapper dictTypeMapper;
	
	/**
	 *查询字典信息列表
	 */
	public List<DictInfo> queryDictInfo(DictInfo dictInfo){
		return dictInfoMapper.queryDictInfo(dictInfo);
		
	}
	/**
	 * 删除字典信息
	 */
	public int deleteDictInfo(DictInfo dictInfo) {
		return dictInfoMapper.deleteDictInfo(dictInfo);
	}
	
	
    /**
	 * 新增字典信息
	 */
	public DictInfo insertDictInfo(DictInfo dictInfo) {
		dictInfo.setCreateTm(DateTimeUtil.getCurrentTime());
		
		DictType  dictType = new DictType();
		String getInfoID = dictInfo.getDictTypeId();
		dictType.setDictTypeId(getInfoID);
		dictType.setCreateTm(DateTimeUtil.getCurrentTime());
		
		dictTypeMapper.insertDictType(dictType);
		dictInfoMapper.insertDictInfo(dictInfo);
		return dictInfo;
	}
	/**
	 * 修改字典信息
	 */
	public DictInfo updateDictInfo(DictInfo dictInfo) {
		dictInfo.setUpdateTm(DateTimeUtil.getCurrentTime());
		dictInfoMapper.updateDictInfo(dictInfo);
		return dictInfo;
	}
	
}
