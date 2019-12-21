package gov.cnao.ao.ai.bfs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.common.ResponseHeadUtil;
import gov.cnao.ao.ai.bfs.entity.UserAuth;
import gov.cnao.ao.ai.bfs.mapper.OperLogMapper;
import gov.cnao.ao.ai.bfs.mapper.UserAuthMapper;
import gov.cnao.ao.ai.bfs.util.CommonUtil;
import gov.cnao.ao.ai.bfs.util.DateUtil;
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
	
	@Autowired
	private UserAuthMapper userAuthMapper;
	
	@Autowired
	private OperLogMapper operLogMapper;
	
	RestTemplate restTemplate = RestTemplateBuilder.create();
	
	/**
	 * 查询授权信息列表
	 * @param userAuth
	 * @return
	 */
	public List<UserAuth> queryUserAuth(AuthVO authVO) {
		List<UserAuth> userAuths = userAuthMapper.queryUserAuth(authVO);
		return userAuths;
	}

	/**
	 * 新增授权信息
	 * @param userAuth
	 * @return
	 */
	public AuthVO insertUserAuth(AuthVO authVO) {
		authVO.setCreateTms(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		userAuthMapper.insertUserAuth(authVO);
		return authVO;
	}

	/**
	 * 删除授权信息
	 * @param userAuth
	 * @return
	 */
	public int deleteUserAuth(UserAuthsVO userAuthsVO) {
		return userAuthMapper.deleteUserAuth(userAuthsVO);
	}

	/**
	 * 授权
	 * @param userAuths
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public BaseResponse<List<AuthVO>> getAuth(UserAuthVO userAuthVO) {
		BaseResponse<List<AuthVO>> baseResponse = new BaseResponse<List<AuthVO>>();
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
		return baseResponse;
	}

	/**
	 * 查询项目组织机构树
	 * @return
	 */
	public XianProjectUserVO xianProjectUser(OrgTreeVO orgTreeVO) {
		orgTreeVO.setUserId("0001");
		orgTreeVO.setProjectIds("00000001");
		RequestVo requestVo = new RequestVo();
		requestVo.setUserId(orgTreeVO.getUserId());
		requestVo.setAuditGrpId(orgTreeVO.getProjectIds());
		String json = restTemplate.postForObject("cse://ao-ai-oes/syncFromICBC/syncGrpMembToYX", requestVo , String.class);
		XianProjectUser xianProjectUser = new XianProjectUser();
		XianProjectUserVO xianProjectUserVO = new XianProjectUserVO();
		List<AuditGroupVO> auditGroupVOs = null;
		List<UsersVO> usersVOs = null;
		List<DataVO> dataVOs = new ArrayList<DataVO>();
		xianProjectUser = JSON.parseObject(json, XianProjectUser.class);
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
	}

	/**
	 * 分页查询授权信息
	 * @param authVO
	 * @return
	 */
	public BaseResponse<PageBean> queryUserAuthPage(AuthVO authVO) {
		BaseResponse<PageBean> baseResponse = new BaseResponse<PageBean>();
		PageBean pageBean = new PageBean();
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
		return baseResponse;
	}

}
