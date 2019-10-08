package gov.cnao.ao.ai.bfs.service;


import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.cnao.ao.ai.bfs.entity.DictInfo;
import gov.cnao.ao.ai.bfs.mapper.DictInfoMapper;
import gov.cnao.ao.ai.bfs.mapper.OperLogMapper;
import gov.cnao.ao.ai.bfs.util.CommonUtil;
import gov.cnao.ao.ai.bfs.util.DateTimeUtil;
import gov.cnao.ao.ai.bfs.util.DateUtil;
import gov.cnao.ao.ai.bfs.vo.DictInfoVO;
import gov.cnao.ao.ai.bfs.vo.InfoVO;
import gov.cnao.ao.ai.bfs.vo.OperLogVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;

@Service
public class DictInfoService {
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(DictInfoService.class);
	
	@Autowired
	private DictInfoMapper dictInfoMapper;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private OperLogMapper operLogMapper;
	
	/**
	 * 查询字典信息列表
	 * @throws ParseException 
	 */
	public List<DictInfo> queryDictInfo(DictInfoVO dictInfoVO){
		try {
			List<DictInfo> list = dictInfoMapper.queryDictInfo(dictInfoVO);
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
			operLogVO.setVisitMenu("数据字典信息管理");
			operLogMapper.insertOperLog(operLogVO);
			return list;
		} catch (Exception e) {
			log.error("查询字典信息列表失败", e);
		}
		return null;
	}
	
	/**
	 * 根据ID查询字典信息名称  
	 */
	public String queryDictInfoById(DictInfoVO dictInfoVO){
		try {
			String dictNm = dictInfoMapper.queryDictInfoById(dictInfoVO);
			return dictNm;
		} catch (Exception e) {
			log.error("根据ID查询字典信息名称失败", e);
		}
		return null;
	}
	
	/**
	 * 根据字典信息名称查询ID
	 */
	public String queryDictInfoByName(DictInfoVO dictInfoVO){
		try {
			String dictCd = dictInfoMapper.queryDictInfoByName(dictInfoVO);
			return dictCd;
		} catch (Exception e) {
			log.error("根据字典信息名称查询ID失败", e);
		}
		return null;
	}
	
	/**
	 * 删除字典信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteDictInfo(DictInfoVO dictInfoVO) {
		int num =0;
		List<InfoVO> dictInfoVOs = dictInfoVO.getDictInfoVOs();
		try {
			for (int i = 0; i < dictInfoVOs.size(); i++) {
				InfoVO infoVO = dictInfoVOs.get(i);
				dictInfoMapper.deleteDictInfo(infoVO);
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
			operLogVO.setVisitMenu("数据字典信息管理");
			operLogMapper.insertOperLog(operLogVO);
		} catch (Exception e) {
			log.error("删除字典信息失败", e);
		}
		return num;
	}
	
	
    /**
	 * 新增字典信息
	 */
	public DictInfoVO insertDictInfo(DictInfoVO dictInfoVO) {
		try {
			dictInfoVO.setCreateTms(DateTimeUtil.getCurrentTime());
			stringRedisTemplate.opsForValue().set(dictInfoVO.getDictCd(), dictInfoVO.getDictNm());
			dictInfoMapper.insertDictInfo(dictInfoVO);
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
			operLogVO.setVisitMenu("数据字典信息管理");
			operLogMapper.insertOperLog(operLogVO);
			return dictInfoVO;
		} catch (Exception e) {
			log.error("新增字典信息失败", e);
		}
		return null;
	}
	
	/**
	 * 修改字典信息
	 */
	public DictInfoVO updateDictInfo(DictInfoVO dictInfoVO) {
		try {
			dictInfoVO.setUpdateTm(DateTimeUtil.getCurrentTime());
			dictInfoMapper.updateDictInfo(dictInfoVO);
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
			operLogVO.setVisitMenu("数据字典信息管理");
			operLogMapper.insertOperLog(operLogVO);
			return dictInfoVO;
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
	public DictInfo queryDictInfoByDictCd(DictInfoVO dictInfoVO) {
		try {
			List<DictInfo> list = dictInfoMapper.queryDictInfo(dictInfoVO);
			if(list.size()>0){
				return list.get(0);
			}
		} catch (Exception e) {
			log.error("修改字典信息失败", e);
		}
		return null;
	}

	/**
	 * 分页查询字典信息列表
	 * @param dictInfoVO
	 * @return
	 */
	public PageBean queryDictInfoPage(DictInfoVO dictInfoVO) {
		PageBean pageBean = new PageBean();
		try {
			if(dictInfoVO.getHead().getPgrw() != null && dictInfoVO.getHead().getPgsn() != null) {
				pageBean = new PageBean(
						dictInfoVO.getHead().getPgsn(), 
						dictInfoVO.getHead().getPgrw(), 
						dictInfoMapper.queryDictInfoCount(dictInfoVO));
				dictInfoVO.getHead().setPgsn((dictInfoVO.getHead().getPgsn() -1)*dictInfoVO.getHead().getPgrw());
				pageBean.setContent(dictInfoMapper.queryDictInfoPage(dictInfoVO));
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
			operLogVO.setVisitMenu("数据字典信息管理");
			operLogMapper.insertOperLog(operLogVO);
		} catch (Exception e) {
			log.error("分页查询字典信息列表失败", e);
		}
		return pageBean;
	}
	
}
