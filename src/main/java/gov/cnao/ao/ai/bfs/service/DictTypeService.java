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

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.common.ResponseHeadUtil;
import gov.cnao.ao.ai.bfs.common.RetCodeEnum;
import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.entity.DictType;
import gov.cnao.ao.ai.bfs.mapper.DictInfoMapper;
import gov.cnao.ao.ai.bfs.mapper.DictTypeMapper;
import gov.cnao.ao.ai.bfs.mapper.OperLogMapper;
import gov.cnao.ao.ai.bfs.util.CommonUtil;
import gov.cnao.ao.ai.bfs.util.DateTimeUtil;
import gov.cnao.ao.ai.bfs.util.DateUtil;
import gov.cnao.ao.ai.bfs.vo.DictInfoVO;
import gov.cnao.ao.ai.bfs.vo.DictTypeTreeVO;
import gov.cnao.ao.ai.bfs.vo.DictTypeVO;
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
		return list3;
	}
	
	/**
	 * 查询字典类别信息
	 */
	public List<DictType> queryDictType(DictTypeVO dictTypeVO) {
		try {
			return dictTypeMapper.queryDictType(dictTypeVO);
		} catch (Exception e) {
			log.error("查询字典类别信息失败", e);
		}
		return null;
	}
	
    /**
	 * 新增字典类别信息
	 */
	public BaseResponse<DictTypeVO> insertDictType(DictTypeVO dictTypeVO) {
		BaseResponse<DictTypeVO> baseResponse = new BaseResponse<DictTypeVO>();
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
			baseResponse.setBody(dictTypeVO);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(dictTypeVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(dictTypeVO, RetCodeEnum.SYS_ERROR));
			log.error("新增字典类别信息失败", e);
		}
		return baseResponse;
	}
	
	/**
	 * 修改字典类别信息
	 */
	public BaseResponse<DictTypeVO> updateDictType(DictTypeVO dictTypeVO) {
		BaseResponse<DictTypeVO> baseResponse = new BaseResponse<DictTypeVO>();
		try {
			dictTypeVO.setUpdateTm(DateTimeUtil.getCurrentTime());
			stringRedisTemplate.opsForValue().set(dictTypeVO.getDictTypeId(), dictTypeVO.getDictTypeNm());
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
			baseResponse.setBody(dictTypeVO);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(dictTypeVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(dictTypeVO, RetCodeEnum.SYS_ERROR));
			log.error("修改字典类别信息失败", e);
		}
		return baseResponse;
	}
	
	/**
	 * 删除字典类别信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public BaseResponse<Integer> deleteDictType(DictTypeVO dictTypeVO) {
		BaseResponse<Integer> baseResponse = new BaseResponse<Integer>();
		Integer num = 0;
		Boolean flag = true;
		List<TypeVO> dictTypeVOs = dictTypeVO.getDictTypeVOs();
		TypeVO typeVO = null;
		try {
			for (int i = 0; i < dictTypeVOs.size(); i++) {
				typeVO = dictTypeVOs.get(i);
				DictInfoVO dictInfoVO = new DictInfoVO();
				dictInfoVO.setDictTypeId(typeVO.getDictTypeId());
				List<DictInfo> dictInfos = dictInfoMapper.queryDictInfo(dictInfoVO);
				if(dictInfos.size()>0) {
					flag = false;
					num = -1;
					baseResponse.setBody(num);
					baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(dictTypeVO));
					return baseResponse;
				}
			}
			
			if(flag) {
				for (int i = 0; i < dictTypeVOs.size(); i++) {
					typeVO = dictTypeVOs.get(i);
					dictTypeMapper.deleteDictType(typeVO);
					num++;
				}
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
			baseResponse.setBody(num);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(dictTypeVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(dictTypeVO, RetCodeEnum.SYS_ERROR));
			log.error("删除字典类别信息失败", e);
		}
    	return baseResponse;
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
	public BaseResponse<PageBean> queryDictTypePage(DictTypeVO dictTypeVO) {
		BaseResponse<PageBean> baseResponse = new BaseResponse<PageBean>();
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
			baseResponse.setBody(pageBean);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(dictTypeVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(dictTypeVO, RetCodeEnum.SYS_ERROR));
			log.error("分页查询字典类别信息列表失败", e);
		}
		return baseResponse;
	}

	/**
	 * 查询数据字典树信息
	 * @return
	 */
	public BaseResponse<List<DictTypeTreeVO>> queryDictTypeTree(DictTypeVO dictTypeVO) {
		BaseResponse<List<DictTypeTreeVO>> baseResponse = 
					new BaseResponse<List<DictTypeTreeVO>>();
		List<DictTypeTreeVO> treeVOs = new ArrayList<DictTypeTreeVO>();
		DictTypeTreeVO dictTypeTreeVO = new DictTypeTreeVO();
		try {
			List<DictType> list = dictTypeMapper.queryDictType(dictTypeVO);
//			List<DictType> dictTypes = new ArrayList<DictType>();
//			for (DictType dictType : list) {
//				DictInfoVO dictInfoVO = new DictInfoVO();
//				dictInfoVO.setDictTypeId(dictType.getDictTypeId());
//				List<DictInfo> dictInfos = dictInfoMapper.queryDictInfo(dictInfoVO);
//				dictType.setDictInfos(dictInfos);
//				dictTypes.add(dictType);
//			}
			dictTypeTreeVO.setDictTypeNm("字典类别");
			dictTypeTreeVO.setDictTypes(list);
			treeVOs.add(dictTypeTreeVO);
			baseResponse.setBody(treeVOs);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(dictTypeVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(dictTypeVO, RetCodeEnum.SYS_ERROR));
			log.error("查询数据字典树信息失败", e);
		}
		return baseResponse;
	}
}
