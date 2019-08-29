package gov.cnao.ao.ai.bfs.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * 查询字典类别信息目录
	 */
	public Map<String, Object> queryDictTypeCon(DictType dictType){
		List<DictType> list= dictTypeMapper.queryDictTypeConent(dictType);
		Map<String,Object> map = new HashMap<>();
		map.put("id", 2);
		map.put("lable", "字典类别");
		map.put("children", list);
		List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
		list2.add(map);
		Map<String,Object> amap = new HashMap<String,Object>();
		amap.put("id", 1);
		amap.put("lable", "数据字典管理");
		amap.put("children", list2);
		return amap;
	}
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
