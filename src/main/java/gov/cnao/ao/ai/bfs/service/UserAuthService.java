package gov.cnao.ao.ai.bfs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjsasc.drap.pt.context.ThreadLocalUtil;

import gov.cnao.ao.ai.bfs.contract.Hello;
import gov.cnao.ao.ai.bfs.entity.UserAuth;
import gov.cnao.ao.ai.bfs.mapper.UserAuthMapper;
import gov.cnao.ao.ai.bfs.util.DateUtil;
import gov.cnao.ao.ai.bfs.util.JsonResourceUtils;
import gov.cnao.ao.ai.bfs.vo.AuditGroupVO;
import gov.cnao.ao.ai.bfs.vo.AuditGroups;
import gov.cnao.ao.ai.bfs.vo.Data;
import gov.cnao.ao.ai.bfs.vo.DataVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;
import gov.cnao.ao.ai.bfs.vo.UserAuthVO;
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
	
//	@RpcReference(microserviceName="ao-ai-oes", schemaId="fileShare")
//	Hello client;
	 
	RestTemplate restTemplate = RestTemplateBuilder.create();
	
	/**
	 * 查询授权信息列表
	 * @param userAuth
	 * @return
	 */
	public List<UserAuth> queryUserAuth(AuthVO authVO) {
		try {
			return userAuthMapper.queryUserAuth(authVO);
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
	public int deleteUserAuth(AuthVO authVO) {
		try {
			return userAuthMapper.deleteUserAuth(authVO);
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
	public List<AuthVO> getAuth(UserAuthVO userAuthVO) {
		try {
			List<AuthVO> userAuths = userAuthVO.getUserAuths();
			for (int i = 0; i < userAuths.size(); i++) {
				AuthVO authVO = userAuths.get(i);
				authVO.setUserId(userAuthVO.getUserId());
				userAuthMapper.insertUserAuth(authVO);
			}
			return userAuths;
		} catch (Exception e) {
			log.error("表数据授权失败", e);
		}
		return null;
	}

	/**
	 * 取消授权
	 * @param userAuths
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<AuthVO> canAuth(AuthVO authVO) {
		List<AuthVO> authVOs = authVO.getAuthVOs();
		try {
			for (int i = 0; i < authVOs.size(); i++) {
				AuthVO authVO1 = authVOs.get(i);
				userAuthMapper.deleteUserAuth(authVO1);
			}
			return authVOs;
		} catch (Exception e) {
			log.error("表数据取消授权失败", e);
		}
		return null;
	}

	/**
	 * 查询项目组织机构树
	 * @return
	 */
	public XianProjectUserVO xianProjectUser(String userId, String projectIds) {
		
//		调用工行的接口
//		String userId = ThreadLocalUtil.getContextUser().getUserID();
//		String projectIds = "";
//		String ip = env.getProperty("icbc.service.ip");
//		String port = env.getProperty("icbc.service.port");
//		String projectUser = restTemplate.getForObject("
//					http://"+ip+":"+port+"/rest/apmservice/operationSystem/xianProjectUser?userId =" + 
//					userId + "&projectIds=" + projectIds , String.class);
//		调用武开的接口
//		String json = restTemplate.getForObject("cse://ao-ai-oes/fileShare/querySameTeam?userId=" + 
//					userId + "&projectIds" + projectIds, String.class);
//		System.out.println(json);
		try {
			XianProjectUser xianProjectUser = new XianProjectUser();
			XianProjectUserVO xianProjectUserVO = new XianProjectUserVO();
			List<AuditGroupVO> auditGroupVOs = null;
			List<UsersVO> usersVOs = null;
			List<DataVO> dataVOs = new ArrayList<DataVO>();
			
			JSONObject obj = JsonResourceUtils.getJsonObjFromResource("/json/xianProjectUser");
//			JSONObject obj = JsonResourceUtils.getJsonObjFromResource(json);
			xianProjectUser = JSON.parseObject(obj.toJSONString(), XianProjectUser.class);
			
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
	public PageBean queryUserAuthPage(AuthVO authVO) {
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
		} catch (Exception e) {
			log.error("分页查询授权信息失败", e);
		}
		return pageBean;
	}

}
