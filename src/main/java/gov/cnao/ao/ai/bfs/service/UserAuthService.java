package gov.cnao.ao.ai.bfs.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjsasc.drap.pt.context.ThreadLocalUtil;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.common.ResponseHeadUtil;
import gov.cnao.ao.ai.bfs.common.RetCodeEnum;
import gov.cnao.ao.ai.bfs.contract.Hello;
import gov.cnao.ao.ai.bfs.entity.UserAuth;
import gov.cnao.ao.ai.bfs.mapper.OperLogMapper;
import gov.cnao.ao.ai.bfs.mapper.UserAuthMapper;
import gov.cnao.ao.ai.bfs.util.CommonUtil;
import gov.cnao.ao.ai.bfs.util.DateUtil;
import gov.cnao.ao.ai.bfs.util.JsonResourceUtils;
import gov.cnao.ao.ai.bfs.vo.AuditGroupVO;
import gov.cnao.ao.ai.bfs.vo.AuditGroups;
import gov.cnao.ao.ai.bfs.vo.Data;
import gov.cnao.ao.ai.bfs.vo.DataVO;
import gov.cnao.ao.ai.bfs.vo.OperLogVO;
import gov.cnao.ao.ai.bfs.vo.OrgTreeVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;
import gov.cnao.ao.ai.bfs.vo.RequestVo;
import gov.cnao.ao.ai.bfs.vo.UserAuthVO;
import gov.cnao.ao.ai.bfs.vo.UserAuthsVO;
import gov.cnao.ao.ai.bfs.vo.Users;
import gov.cnao.ao.ai.bfs.vo.UsersVO;
import gov.cnao.ao.ai.bfs.vo.XianProjectUser;
import gov.cnao.ao.ai.bfs.vo.XianProjectUserVO;
import gov.cnao.ao.ai.bfs.vo.AuthVO;

@Service
public class UserAuthService {
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(UserAuthService.class);

	@Autowired
	private UserAuthMapper userAuthMapper;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private OperLogMapper operLogMapper;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	RestTemplate restTemplate = RestTemplateBuilder.create();
	
	/**
	 * 查询授权信息列表
	 * @param userAuth
	 * @return
	 */
	public List<UserAuth> queryUserAuth(AuthVO authVO) {
		try {
			List<UserAuth> userAuths = userAuthMapper.queryUserAuth(authVO);
			return userAuths;
		} catch (Exception e) {
			log.error("查询授权信息列表失败", e);
		}
		return null;
	}

	/**
	 * 新增授权信息
	 * @param userAuth
	 * @return
	 */
	public AuthVO insertUserAuth(AuthVO authVO) {
		try {
			authVO.setCreateTms(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			userAuthMapper.insertUserAuth(authVO);
			return authVO;
		} catch (Exception e) {
			log.error("新增授权信息失败", e);
		}
		return null;
	}

	/**
	 * 删除授权信息
	 * @param userAuth
	 * @return
	 */
	public int deleteUserAuth(UserAuthsVO userAuthsVO) {
		try {
			return userAuthMapper.deleteUserAuth(userAuthsVO);
		} catch (Exception e) {
			log.error("删除授权信息失败", e);
		}
		return 0;
	}

	/**
	 * 授权
	 * @param userAuths
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public BaseResponse<List<AuthVO>> getAuth(UserAuthVO userAuthVO) {
		BaseResponse<List<AuthVO>> baseResponse = new BaseResponse<List<AuthVO>>();
		try {
			List<AuthVO> userAuths = userAuthVO.getUserAuths();
			for (int i = 0; i < userAuths.size(); i++) {
				AuthVO authVO = userAuths.get(i);
				authVO.setUserId(userAuthVO.getUserId());
				userAuthMapper.insertUserAuth(authVO);
			}
			//操作日志新增
			OperLogVO LogVO = new OperLogVO();
			LogVO.setLogId(CommonUtil.getSeqNum());
			LogVO.setProjId("项目编号");
			LogVO.setUserId("用户标识");
			LogVO.setUserNm("用户名称");
			LogVO.setOrgId("机构代码");
			LogVO.setOrgNm("机构名称");
			LogVO.setLoginIp("登录IP");
			LogVO.setOperTm(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			LogVO.setLogType("01");
			LogVO.setFunFlg("数据授权");
			LogVO.setLogCont("日志内容");
			LogVO.setVisitMicr("ao-ai-bfs");
			LogVO.setVisitMenu("数据授权管理");
			operLogMapper.insertOperLog(LogVO);
			baseResponse.setBody(userAuths);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(userAuthVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(userAuthVO, RetCodeEnum.SYS_ERROR));
			log.error("表数据授权失败", e);
		}
		return baseResponse;
	}

	/**
	 * 取消授权
	 * @param userAuths
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public BaseResponse<List<UserAuthsVO>> canAuth(AuthVO authVO) {
		BaseResponse<List<UserAuthsVO>> baseResponse = new BaseResponse<List<UserAuthsVO>>();
		List<UserAuthsVO> authVOs = authVO.getAuthVOs();
		try {
			for (int i = 0; i < authVOs.size(); i++) {
				UserAuthsVO userAuthsVO = authVOs.get(i);
				userAuthMapper.deleteUserAuth(userAuthsVO);
			}
			//操作日志新增
			OperLogVO LogVO = new OperLogVO();
			LogVO.setLogId(CommonUtil.getSeqNum());
			LogVO.setProjId("项目编号");
			LogVO.setUserId("用户标识");
			LogVO.setUserNm("用户名称");
			LogVO.setOrgId("机构代码");
			LogVO.setOrgNm("机构名称");
			LogVO.setLoginIp("登录IP");
			LogVO.setOperTm(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			LogVO.setLogType("01");
			LogVO.setFunFlg("取消数据授权");
			LogVO.setLogCont("日志内容");
			LogVO.setVisitMicr("ao-ai-bfs");
			LogVO.setVisitMenu("数据授权管理");
			operLogMapper.insertOperLog(LogVO);
			baseResponse.setBody(authVOs);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(authVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(authVO, RetCodeEnum.SYS_ERROR));
			log.error("表数据取消授权失败", e);
		}
		return baseResponse;
	}

	/**
	 * 查询项目组织机构树
	 * @return
	 */
	public XianProjectUserVO xianProjectUser(OrgTreeVO orgTreeVO) {
		
//		调用工行的接口
//		String userId = ThreadLocalUtil.getContextUser().getUserID();
//		String projectIds = "";
//		String ip = env.getProperty("icbc.service.ip");
//		String port = env.getProperty("icbc.service.port");
//		String projectUser = restTemplate.getForObject("
//					http://"+ip+":"+port+"/rest/apmservice/operationSystem/xianProjectUser?userId =" + 
//					userId + "&projectIds=" + projectIds , String.class);
//		调用武开的接口
//		String json = restTemplate.getForObject("cse://ao-ai-oes/fileShare/querySameTeam?userId=" + "0001"  + "&projectIds" + 
//					 "00000002" , String.class);
		
		
//		String json = restTemplate.postForObject("cse://ao-ai-dms-sjm/hello/sayhi", orgTreeVO , String.class);
//		System.out.println(str);
		
		orgTreeVO.setUserId("0001");
		orgTreeVO.setProjectIds("00000001");
		RequestVo requestVo = new RequestVo();
		requestVo.setUserId(orgTreeVO.getUserId());
		requestVo.setAuditGrpId(orgTreeVO.getProjectIds());;
		String json = restTemplate.postForObject("cse://ao-ai-oes/syncFromICBC/syncGrpMembToYX", requestVo , String.class);
		try {
			XianProjectUser xianProjectUser = new XianProjectUser();
			XianProjectUserVO xianProjectUserVO = new XianProjectUserVO();
			List<AuditGroupVO> auditGroupVOs = null;
			List<UsersVO> usersVOs = null;
			List<DataVO> dataVOs = new ArrayList<DataVO>();
			
			xianProjectUser = JSON.parseObject(json, XianProjectUser.class);
			
//			String obj = "";
//			ClassPathResource resource = new ClassPathResource("json/xianProjectUser.json");
//			byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
//			obj = new String(bdata, StandardCharsets.UTF_8);
//			xianProjectUser = JSON.parseObject(obj, XianProjectUser.class);
//			
			
//			JSONObject obj = JsonResourceUtils.getJsonObjFromResource("/json/xianProjectUser");
//			xianProjectUser = JSON.parseObject(obj.toJSONString(), XianProjectUser.class);
			
			List<Data> datas = xianProjectUser.getData();
			for (Data data : datas) {
				auditGroupVOs = new ArrayList<AuditGroupVO>();
				DataVO dataVO = new DataVO();
				dataVO.setProjectId(data.getProjectId());
				dataVO.setProjectName(data.getProjectName());
				
				List<AuditGroups> auditGroups = data.getAuditGroups();
				for (AuditGroups auditGroup : auditGroups) {
					AuditGroupVO auditGroupVO = new AuditGroupVO();
					auditGroupVO.setAuditGroupId(auditGroup.getAuditGroupId());
					auditGroupVO.setName(auditGroup.getAuditGroupName());
					List<Users> users = auditGroup.getUsers();
					usersVOs = new ArrayList<UsersVO>();
					for (Users user : users) {
						UsersVO usersVO = new UsersVO();
						usersVO.setName(user.getUserName() + "(" + user.getRoleName() + ")");
						usersVO.setRoleCode(user.getRoleCode());
						usersVO.setRoleName(user.getRoleName());
						usersVO.setUserId(user.getUserId());
						usersVOs.add(usersVO);
					}
					auditGroupVO.setUsersVOs(usersVOs);
					auditGroupVOs.add(auditGroupVO);
				}
				dataVO.setAuditGroupVOs(auditGroupVOs);
				dataVOs.add(dataVO);
			}
			
			xianProjectUserVO.setResultCode(200);
			xianProjectUserVO.setResultMsg("请求成功");
			xianProjectUserVO.setSuccess(true);
			xianProjectUserVO.setDataVOs(dataVOs);
			
			return xianProjectUserVO;
		} catch (Exception e) {
			log.error("查询项目组织机构树失败", e);
		}
		return null;
	}

	/**
	 * 分页查询授权信息
	 * @param authVO
	 * @return
	 */
	public BaseResponse<PageBean> queryUserAuthPage(AuthVO authVO) {
		BaseResponse<PageBean> baseResponse = new BaseResponse<PageBean>();
		PageBean pageBean = new PageBean();
		try {
			if(authVO.getHead().getPgrw() != null && authVO.getHead().getPgsn() != null) {
				pageBean = new PageBean(
						authVO.getHead().getPgsn(), 
						authVO.getHead().getPgrw(), 
						userAuthMapper.queryUserAuthCount(authVO));
				authVO.getHead().setPgsn((authVO.getHead().getPgsn() -1)*authVO.getHead().getPgrw());
				pageBean.setContent(userAuthMapper.queryUserAuthPage(authVO));
			}
			baseResponse.setBody(pageBean);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(authVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(authVO, RetCodeEnum.SYS_ERROR));
			log.error("分页查询授权信息失败", e);
		}
		return baseResponse;
	}

}
