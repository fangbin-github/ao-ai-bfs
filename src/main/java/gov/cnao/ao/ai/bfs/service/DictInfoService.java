package gov.cnao.ao.ai.bfs.service;


import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.common.ResponseHeadUtil;
import gov.cnao.ao.ai.bfs.entity.DictInfo;
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
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Service
public class DictInfoService {
	
	private final static org.slf4j.Logger log = LoggerFactory.getLogger(DictInfoService.class);
	
	@Autowired
	private DictInfoMapper dictInfoMapper;
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private OperLogMapper operLogMapper;
	
	@Autowired
	private RedisTemplateService redisTemplateService;
	
	@Autowired
	private DictTypeMapper dictTypeMapper;
	
	/**
	 * 查询字典信息列表
	 * @throws ParseException 
	 */
	public List<DictInfo> queryDictInfo(DictInfoVO dictInfoVO){
		return dictInfoMapper.queryDictInfo(dictInfoVO);
	}
	
	/**
	 * 根据ID查询字典信息名称  
	 */
	public String queryDictInfoById(DictInfoVO dictInfoVO){
		return dictInfoMapper.queryDictInfoById(dictInfoVO);
	}
	
	/**
	 * 根据字典信息名称查询ID
	 */
	public String queryDictInfoByName(DictInfoVO dictInfoVO){
		return dictInfoMapper.queryDictInfoByName(dictInfoVO);
	}
	
	/**
	 * 删除字典信息
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public BaseResponse<Integer> deleteDictInfo(DictInfoVO dictInfoVO) {
		BaseResponse<Integer> baseResponse = new BaseResponse<Integer>();
		Integer num =0;
		List<InfoVO> dictInfoVOs = dictInfoVO.getDictInfoVOs();
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
		baseResponse.setBody(num);
		baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(dictInfoVO));
		return baseResponse;
	}
	
	
    /**
	 * 新增字典信息
	 */
	public BaseResponse<DictInfoVO> insertDictInfo(DictInfoVO dictInfoVO) {
		BaseResponse<DictInfoVO> baseResponse = new BaseResponse<DictInfoVO>();
		try {
			dictInfoVO.setCreateTms(DateTimeUtil.getCurrentTime());
			Set<HostAndPort> hosts = redisTemplateService.getHosts();
			final JedisCluster client = new JedisCluster(hosts, 15000);
			client.set(dictInfoVO.getDictCd(), dictInfoVO.getDictNm());
//			System.out.println(client.get(dictInfoVO.getDictCd()) + "===================================================");
//			stringRedisTemplate.opsForValue().set(dictInfoVO.getDictCd(), dictInfoVO.getDictNm());
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
			baseResponse.setBody(dictInfoVO);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(dictInfoVO));
			client.close();
		} catch (IOException e) {
			log.error("关闭华为ridis失败！" + e);
		}
		return baseResponse;
	}
	
	/**
	 * 修改字典信息
	 */
	public BaseResponse<DictInfoVO> updateDictInfo(DictInfoVO dictInfoVO) {
		BaseResponse<DictInfoVO> baseResponse = new BaseResponse<DictInfoVO>();
		try {
			dictInfoVO.setUpdateTm(DateTimeUtil.getCurrentTime());
			Set<HostAndPort> hosts = redisTemplateService.getHosts();
			final JedisCluster client = new JedisCluster(hosts, 15000);
			client.set(dictInfoVO.getDictCd(), dictInfoVO.getDictNm());
//			System.out.println(client.get(dictInfoVO.getDictCd()) + "===================================================");
//			stringRedisTemplate.opsForValue().set(dictInfoVO.getDictCd(), dictInfoVO.getDictNm());
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
			baseResponse.setBody(dictInfoVO);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(dictInfoVO));
			client.close();
		} catch (IOException e) {
			log.error("关闭华为ridis失败！" + e);
		}
		return baseResponse;
	}
	
	/**
	 * 通过字典项代码查询字典信息
	 * @param dictInfo
	 * @return
	 */
	public DictInfo queryDictInfoByDictCd(DictInfoVO dictInfoVO) {
		List<DictInfo> list = dictInfoMapper.queryDictInfo(dictInfoVO);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 分页查询字典信息列表
	 * @param dictInfoVO
	 * @return
	 */
	public BaseResponse<PageBean> queryDictInfoPage(DictInfoVO dictInfoVO) {
		BaseResponse<PageBean> baseResponse = new BaseResponse<PageBean>();
		PageBean pageBean = new PageBean();
		List<DictInfo> dictInfos = new ArrayList<DictInfo>();
		if(dictInfoVO.getHead().getPgrw() != null && dictInfoVO.getHead().getPgsn() != null) {
			pageBean = new PageBean(
					dictInfoVO.getHead().getPgsn(), 
					dictInfoVO.getHead().getPgrw(), 
					dictInfoMapper.queryDictInfoCount(dictInfoVO));
			dictInfoVO.getHead().setPgsn((dictInfoVO.getHead().getPgsn() -1)*dictInfoVO.getHead().getPgrw());
			List<DictInfo> list = dictInfoMapper.queryDictInfoPage(dictInfoVO);
			for (DictInfo dictInfo : list) {
				DictTypeVO dictTypeVO = new DictTypeVO();
				dictTypeVO.setDictTypeId(dictInfo.getDictTypeId());
				dictInfo.setDictTypeNm(dictTypeMapper.queryDictType(dictTypeVO).get(0).getDictTypeNm());
				dictInfos.add(dictInfo);
			}
			pageBean.setContent(dictInfos);
		}
		baseResponse.setBody(pageBean);
		baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(dictInfoVO));
		return baseResponse;
	}
	
}
