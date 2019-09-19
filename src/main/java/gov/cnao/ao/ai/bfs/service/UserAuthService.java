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
import gov.cnao.ao.ai.bfs.vo.UserAuthVO;
import gov.cnao.ao.ai.bfs.vo.Users;
import gov.cnao.ao.ai.bfs.vo.UsersVO;
import gov.cnao.ao.ai.bfs.vo.XianProjectUser;
import gov.cnao.ao.ai.bfs.vo.XianProjectUserVO;

@Service
public class UserAuthService {
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(UserAuthService.class);

	@Autowired
	private UserAuthMapper userAuthMapper;
	
	@Autowired
	private Environment env;
	
	@RpcReference(microserviceName="", schemaId="prjInf_01")
	Hello client;
	 
	RestTemplate restTemplate = RestTemplateBuilder.create();
	
	/**
	 * 查询授权信息列表
	 * @param userAuth
	 * @return
	 */
	public List<UserAuth> queryUserAuth(UserAuth userAuth) {
		try {
			return userAuthMapper.queryUserAuth(userAuth);
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
	public UserAuth insertUserAuth(UserAuth userAuth) {
		try {
			userAuth.setCreateTms(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			userAuthMapper.insertUserAuth(userAuth);
			return userAuth;
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
	public int deleteUserAuth(UserAuth userAuth) {
		try {
			return userAuthMapper.deleteUserAuth(userAuth);
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
	public List<UserAuth> getAuth(UserAuthVO userAuthVO) {
		try {
			List<UserAuth> userAuths = userAuthVO.getUserAuths();
			for (int i = 0; i < userAuths.size(); i++) {
				UserAuth userAuth = userAuths.get(i);
				userAuth.setUserId(userAuthVO.getUserId());
				userAuthMapper.insertUserAuth(userAuth);
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
	public List<UserAuth> canAuth(List<UserAuth> userAuths) {
		try {
			for (int i = 0; i < userAuths.size(); i++) {
				UserAuth userAuth = userAuths.get(i);
				userAuthMapper.deleteUserAuth(userAuth);
			}
			return userAuths;
		} catch (Exception e) {
			log.error("表数据取消授权失败", e);
		}
		return null;
	}

	/**
	 * 查询项目组织机构树
	 * @return
	 */
	public XianProjectUserVO xianProjectUser() {
		
//		调用工行的接口
//		String userId = ThreadLocalUtil.getContextUser().getUserID();
//		String projectIds = "";
//		String ip = env.getProperty("icbc.service.ip");
//		String port = env.getProperty("icbc.service.port");
//		String projectUser = restTemplate.getForObject("
//					http://"+ip+":"+port+"/rest/apmservice/operationSystem/xianProjectUser?userId =" + 
//					userId + "&projectIds=" + projectIds , String.class);
//		调用武开的接口
//		restTemplate.getForObject("cse://helloprovider/hello/sayhi?userId=" + userId, String.class);
		try {
			XianProjectUser xianProjectUser = new XianProjectUser();
			XianProjectUserVO xianProjectUserVO = new XianProjectUserVO();
			List<AuditGroupVO> auditGroupVOs = null;
			List<UsersVO> usersVOs = null;
			List<DataVO> dataVOs = new ArrayList<DataVO>();
			
			JSONObject obj = JsonResourceUtils.getJsonObjFromResource("/json/xianProjectUser");
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

}
