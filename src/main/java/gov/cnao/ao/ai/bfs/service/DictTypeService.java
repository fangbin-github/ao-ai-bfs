package gov.cnao.ao.ai.bfs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.entity.DictType;
import gov.cnao.ao.ai.bfs.mapper.DictInfoMapper;
import gov.cnao.ao.ai.bfs.mapper.DictTypeMapper;
import gov.cnao.ao.ai.bfs.util.DateTimeUtil;
import net.sf.json.JSONObject;

@Service
public class DictTypeService {
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(DictTypeService.class);
	
	@Autowired
	private DictTypeMapper dictTypeMapper;
	
	@Autowired
	private DictInfoMapper dictInfoMapper;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 查询字典类别信息目录
	 */
	public List<Map<String, Object>> queryDictTypeCon(DictType dictType){
		
		List<Map<String, Object>> list3 = null;
		try {
			List<DictType> list1= dictTypeMapper.queryDictTypeConent(dictType);
			//对里层数据进行处理
			List<Object> list = new ArrayList<>(); 
			for(DictType c : list1) {
				JSONObject object = new JSONObject();
				object.put("label", c.getDictTypeNm());
				object.put("id", c.getDictTypeId());
				object.put("dictTypeNm", c.getDictTypeNm());
				object.put("dictTypeId", c.getDictTypeId());
//				object.put("children", c.getDictTypeNm());
				list.add(object);
			}
			
			Map<String,Object> map = new HashMap<>();
			map.put("id", 2);
			map.put("label", "字典类别");
			map.put("children", list);
			List<Map<String, Object>> list2 = new ArrayList<Map<String,Object>>();
			list2.add(map);
			Map<String,Object> amap = new HashMap<String,Object>();
			amap.put("id", 1);
			amap.put("label", "数据字典管理");
			amap.put("children", list2);
			list3 = new ArrayList<Map<String, Object>>();
			list3.add(amap);
		} catch (Exception e) {
			log.error("查询字典类别信息目录失败", e);
		}
		//返回包装的数据
		return list3;
	}
	
	/**
	 * 查询字典类别信息
	 */
	public List<DictType> queryDictType(DictType dictType) {
		try {
			return dictTypeMapper.queryDictType(dictType);
		} catch (Exception e) {
			log.error("查询字典类别信息失败", e);
		}
		return null;
	}
	
    /**
	 * 新增字典类别信息
	 */
	public DictType insertDictType(DictType dictType) {
		try {
			dictType.setCreateTms(DateTimeUtil.getCurrentTime());
			stringRedisTemplate.opsForValue().set(dictType.getDictTypeId(), dictType.getDictTypeNm());
			dictTypeMapper.insertDictType(dictType);
			return dictType;
		} catch (Exception e) {
			log.error("新增字典类别信息失败", e);
		}
		return null;
	}
	
	/**
	 * 修改字典类别信息
	 */
	public DictType updateDictType(DictType dictType) {
		try {
			dictType.setUpdateTm(DateTimeUtil.getCurrentTime());
			dictTypeMapper.updateDictType(dictType);
			return dictType;
		} catch (Exception e) {
			log.error("修改字典类别信息失败", e);
		}
		return null;
	}
	
	/**
	 * 删除字典类别信息
	 */
	public int deleteDictType(List<DictType> list) {
		int num = 0;
		try {
			for (int i = 0; i < list.size(); i++) {
				DictType dictType = list.get(i);
				dictTypeMapper.deleteDictType(dictType);
				DictInfo dictInfo = new DictInfo();
				dictInfo.setDictTypeId(dictType.getDictTypeId());
				dictInfoMapper.deleteDictInfoByTypeId(dictInfo);
				num++;
			}
		} catch (Exception e) {
			log.error("删除字典类别信息失败", e);
		}
    	return num;
    }
	
	/**
	 * 通过字典类型代码查询字典类型信息
	 * @param dictType
	 * @return
	 */
	public DictType queryDictTypeByDictTypeId(DictType dictType) {
		try {
			List<DictType> list = dictTypeMapper.queryDictType(dictType);
			if(list.size()>0){
				return list.get(0);
			}
		} catch (Exception e) {
			log.error("通过字典类型代码查询字典类型信息失败", e);
		}
		return null;
	}
}
