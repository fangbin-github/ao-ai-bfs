package gov.cnao.ao.ai.bfs.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
	private DictInfo temp;
	
	/**
	 *查询字典信息列表
	 * @throws ParseException 
	 */
	public List<DictInfo> queryDictInfo(DictInfo dictInfo){
		List<DictInfo> list = dictInfoMapper.queryDictInfo(dictInfo);
		return list;
	}
	/**
	 * 根据ID查询字典信息名称  
	 */
	public List<DictInfo> queryDictInfoById(DictInfo dictInfo){
		List<DictInfo> list = dictInfoMapper.queryDictInfoById(dictInfo);
		return list;
	}
	/**
	 * 根据字典信息名称查询ID
	 */
	public List<DictInfo> queryDictInfoByName(DictInfo dictInfo){
		List<DictInfo> list = dictInfoMapper.queryDictInfoByName(dictInfo);
		return list;
	}
//	public List<Map<String, Object>> queryDictInfo(DictInfo dictInfo){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		List<DictInfo> list= dictInfoMapper.queryDictInfo(dictInfo);
//		Map<String,Object> map = new HashMap<>();
//		map.put("id", 1);
//		map.put("lable", "数据字典管理");
//		map.put("字典信息", list);
//		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
//		list2.add(map);
//		return list2;
//	}
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
