package gov.cnao.ao.ai.bfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.entity.DictType;
import gov.cnao.ao.ai.bfs.mapper.DictInfoMapper;
import gov.cnao.ao.ai.bfs.mapper.DictTypeMapper;
import gov.cnao.ao.ai.bfs.util.DateTimeUtil;

@Service
public class DictTypeService {
	
	@Autowired
	private DictTypeMapper dictTypeMapper;
	@Autowired
	private DictInfoMapper dictInfoMapper;
	/**
	 * 查询字典类别信息
	 */
	public List<DictType> queryDictType(DictType dictType) {
		return dictTypeMapper.queryDictType(dictType);
	}
    /**
	 * 新增字典类别信息
	 */
	public DictType insertDictType(DictType dictType) {
//		 dictType.setUpdateTm(DateTimeUtil.getCurrentTime());
		 dictType.setCreateTm(DateTimeUtil.getCurrentTime());
		 dictTypeMapper.insertDictType(dictType);
		return dictType;
	}
	/**
	 * 修改字典类别信息
	 */
	public DictType updateDictType(DictType dictType) {
		 dictType.setUpdateTm(DateTimeUtil.getCurrentTime());
		 dictTypeMapper.updateDictType(dictType);
		return dictType;
	}
	/**
	 * 删除字典类别信息
	 */
	public int deleteDictType(DictType dictType) {
		int num1 = dictTypeMapper.deleteDictType(dictType);
		DictInfo dictInfo = new DictInfo();
		String getDictID = dictType.getDictTypeId();
		dictInfo.setDictTypeId(getDictID);
		int num2 = dictInfoMapper.deleteDictInfo(dictInfo);
		int num = num1+num2;
    	return num;
    }
}
