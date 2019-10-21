package gov.cnao.ao.ai.bfs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.entity.DictType;
import gov.cnao.ao.ai.bfs.entity.OperLog;
import gov.cnao.ao.ai.bfs.mapper.DictInfoMapper;
import gov.cnao.ao.ai.bfs.mapper.DictTypeMapper;
import gov.cnao.ao.ai.bfs.mapper.OperLogMapper;
import gov.cnao.ao.ai.bfs.util.CommonUtil;
import gov.cnao.ao.ai.bfs.util.DateTimeUtil;
import gov.cnao.ao.ai.bfs.util.DateUtil;
import gov.cnao.ao.ai.bfs.vo.DictInfoVO;
import gov.cnao.ao.ai.bfs.vo.DictTypeVO;
import gov.cnao.ao.ai.bfs.vo.InfoVO;
import gov.cnao.ao.ai.bfs.vo.OperLogVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;
import gov.cnao.ao.ai.bfs.vo.TypeVO;
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
	
	@Autowired
	private OperLogMapper operLogMapper;
	
	/**
	 * 查询字典类别信息目录
	 */
	public List<Map<String, Object>> queryDictTypeCon(DictTypeVO dictTypeVO){
		
		List<Map<String, Object>> list3 = null;
		try {
			List<DictType> list1= dictTypeMapper.queryDictTypeConent(dictTypeVO);
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
	public List<DictType> queryDictType(DictTypeVO dictTypeVO) {
		try {
			List<DictType> dictTypes = dictTypeMapper.queryDictType(dictTypeVO);
			//操作日志新增
			OperLogVO operLogVO = new OperLogVO();
			operLogVO.setLogId(CommonUtil.getSeqNum());
			operLogVO.setProjId("项目编号");
			operLogVO.setUserId("用户标识");
			operLogVO.setUserNm("用户名称");
			operLogVO.setOrgId("机构代码");
			operLogVO.setOrgNm("机构名称");
			operLogVO.setLoginIp("登录IP");
			operLogVO.setOperTm(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			operLogVO.setLogType("01");
			operLogVO.setFunFlg("查询");
			operLogVO.setLogCont("日志内容");
			operLogVO.setVisitMicr("ao-ai-bfs");
			operLogVO.setVisitMenu("数据字典类型管理");
			operLogMapper.insertOperLog(operLogVO);
			return dictTypes;
		} catch (Exception e) {
			log.error("查询字典类别信息失败", e);
		}
		return null;
	}
	
    /**
	 * 新增字典类别信息
	 */
	public DictTypeVO insertDictType(DictTypeVO dictTypeVO) {
		try {
			dictTypeVO.setCreateTms(DateTimeUtil.getCurrentTime());
			stringRedisTemplate.opsForValue().set(dictTypeVO.getDictTypeId(), dictTypeVO.getDictTypeNm());
			dictTypeMapper.insertDictType(dictTypeVO);
			//操作日志新增
			OperLogVO operLogVO = new OperLogVO();
			operLogVO.setLogId(CommonUtil.getSeqNum());
			operLogVO.setProjId("项目编号");
			operLogVO.setUserId("用户标识");
			operLogVO.setUserNm("用户名称");
			operLogVO.setOrgId("机构代码");
			operLogVO.setOrgNm("机构名称");
			operLogVO.setLoginIp("登录IP");
			operLogVO.setOperTm(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			operLogVO.setLogType("01");
			operLogVO.setFunFlg("新增");
			operLogVO.setLogCont("日志内容");
			operLogVO.setVisitMicr("ao-ai-bfs");
			operLogVO.setVisitMenu("数据字典类型管理");
			operLogMapper.insertOperLog(operLogVO);
			return dictTypeVO;
		} catch (Exception e) {
			log.error("新增字典类别信息失败", e);
		}
		return null;
	}
	
	/**
	 * 修改字典类别信息
	 */
	public DictTypeVO updateDictType(DictTypeVO dictTypeVO) {
		try {
			dictTypeVO.setUpdateTm(DateTimeUtil.getCurrentTime());
			dictTypeMapper.updateDictType(dictTypeVO);
			//操作日志新增
			OperLogVO operLogVO = new OperLogVO();
			operLogVO.setLogId(CommonUtil.getSeqNum());
			operLogVO.setProjId("项目编号");
			operLogVO.setUserId("用户标识");
			operLogVO.setUserNm("用户名称");
			operLogVO.setOrgId("机构代码");
			operLogVO.setOrgNm("机构名称");
			operLogVO.setLoginIp("登录IP");
			operLogVO.setOperTm(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			operLogVO.setLogType("01");
			operLogVO.setFunFlg("修改");
			operLogVO.setLogCont("日志内容");
			operLogVO.setVisitMicr("ao-ai-bfs");
			operLogVO.setVisitMenu("数据字典类型管理");
			operLogMapper.insertOperLog(operLogVO);
			return dictTypeVO;
		} catch (Exception e) {
			log.error("修改字典类别信息失败", e);
		}
		return null;
	}
	
	/**
	 * 删除字典类别信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteDictType(DictTypeVO dictTypeVO) {
		int num = 0;
		List<TypeVO> dictTypeVOs = dictTypeVO.getDictTypeVOs();
		try {
			for (int i = 0; i < dictTypeVOs.size(); i++) {
				TypeVO typeVO = dictTypeVOs.get(i);
				dictTypeMapper.deleteDictType(typeVO);
				InfoVO infoVO = new InfoVO();
				infoVO.setDictTypeId(typeVO.getDictTypeId());
				dictInfoMapper.deleteDictInfoByTypeId(infoVO);
				num++;
			}
			//操作日志新增
			OperLogVO operLogVO = new OperLogVO();
			operLogVO.setLogId(CommonUtil.getSeqNum());
			operLogVO.setProjId("项目编号");
			operLogVO.setUserId("用户标识");
			operLogVO.setUserNm("用户名称");
			operLogVO.setOrgId("机构代码");
			operLogVO.setOrgNm("机构名称");
			operLogVO.setLoginIp("登录IP");
			operLogVO.setOperTm(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			operLogVO.setLogType("01");
			operLogVO.setFunFlg("删除");
			operLogVO.setLogCont("日志内容");
			operLogVO.setVisitMicr("ao-ai-bfs");
			operLogVO.setVisitMenu("数据字典类型管理");
			operLogMapper.insertOperLog(operLogVO);
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
	public DictType queryDictTypeByDictTypeId(DictTypeVO dictTypeVO) {
		try {
			List<DictType> list = dictTypeMapper.queryDictType(dictTypeVO);
			if(list.size()>0){
				return list.get(0);
			}
		} catch (Exception e) {
			log.error("通过字典类型代码查询字典类型信息失败", e);
		}
		return null;
	}

	/**
	 * 分页查询字典类别信息列表
	 * @param dictType
	 * @return
	 */
	public PageBean queryDictTypePage(DictTypeVO dictTypeVO) {
		PageBean pageBean = new PageBean();
		try {
			if(dictTypeVO.getHead().getPgrw() != null && dictTypeVO.getHead().getPgsn() != null) {
				pageBean = new PageBean(
						dictTypeVO.getHead().getPgsn(), 
						dictTypeVO.getHead().getPgrw(), 
							dictTypeMapper.queryDictTypeCount(dictTypeVO));
				dictTypeVO.getHead().setPgsn((dictTypeVO.getHead().getPgsn() -1)*dictTypeVO.getHead().getPgrw());
				pageBean.setContent(dictTypeMapper.queryDictTypePage(dictTypeVO));
			}
			//操作日志新增
			OperLogVO operLogVO = new OperLogVO();
			operLogVO.setLogId(CommonUtil.getSeqNum());
			operLogVO.setProjId("项目编号");
			operLogVO.setUserId("用户标识");
			operLogVO.setUserNm("用户名称");
			operLogVO.setOrgId("机构代码");
			operLogVO.setOrgNm("机构名称");
			operLogVO.setLoginIp("登录IP");
			operLogVO.setOperTm(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			operLogVO.setLogType("01");
			operLogVO.setFunFlg("查询");
			operLogVO.setLogCont("日志内容");
			operLogVO.setVisitMicr("ao-ai-bfs");
			operLogVO.setVisitMenu("数据字典类型管理");
			operLogMapper.insertOperLog(operLogVO);
		} catch (Exception e) {
			log.error("分页查询字典类别信息列表失败", e);
		}
		return pageBean;
	}
}
