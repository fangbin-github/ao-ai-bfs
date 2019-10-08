package gov.cnao.ao.ai.bfs.controller;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.contract.IUserAuth;
import gov.cnao.ao.ai.bfs.entity.UserAuth;
import gov.cnao.ao.ai.bfs.service.UserAuthService;
import gov.cnao.ao.ai.bfs.vo.PageBean;
import gov.cnao.ao.ai.bfs.vo.UserAuthVO;
import gov.cnao.ao.ai.bfs.vo.UserAuthsVO;
import gov.cnao.ao.ai.bfs.vo.XianProjectUserVO;
import gov.cnao.ao.ai.bfs.vo.AuthVO;

@RestSchema(schemaId = "iuserauth")
@RequestMapping(path = "/auth")
public class UserAuthController implements IUserAuth{
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(UserAuthController.class);
	
	@Autowired
	private UserAuthService userAuthService;
	
	/**
	 * 查询授权信息列表
	 */
	@Override
	@RequestMapping(path = "/queryUserAuth", method = RequestMethod.POST)
	public List<UserAuth> queryUserAuth(@RequestBody AuthVO authVO) {
		log.info("Access /IUserAuth/queryUserAuth -- 查询授权信息列表信息");
		return userAuthService.queryUserAuth(authVO);
	}

	/**
	 * 新增授权信息
	 */
	@Override
	@RequestMapping(path = "/insertUserAuth", method = RequestMethod.POST)
	public AuthVO insertUserAuth(@RequestBody AuthVO authVO) {
		log.info("Access /IUserAuth/insertUserAuth -- 新增授权信息");
		return userAuthService.insertUserAuth(authVO);
	}

	/**
	 * 删除授权信息
	 */
	@Override
	@RequestMapping(path = "/deleteUserAuth", method = RequestMethod.POST)
	public int deleteUserAuth(@RequestBody UserAuthsVO userAuthsVO) {
		log.info("Access /IUserAuth/deleteUserAuth -- 删除授权信息");
		return userAuthService.deleteUserAuth(userAuthsVO);
	}

	/**
	 * 授权
	 */
	@Override
	@RequestMapping(path = "/getAuth", method = RequestMethod.POST)
	public List<AuthVO> getAuth(@RequestBody UserAuthVO userAuthVO) {
		log.info("Access /IUserAuth/getAuth -- 授权");
		return userAuthService.getAuth(userAuthVO);
	}
	
	/**
	 * 取消授权
	 */
	@Override
	@RequestMapping(path = "/canAuth", method = RequestMethod.POST)
	public List<UserAuthsVO> canAuth(@RequestBody AuthVO authVO) {
		log.info("Access /IUserAuth/canAuth -- 取消授权");
		return userAuthService.canAuth(authVO);
	}

	/**
	 * 查询项目组织机构树
	 */
	@RequestMapping(path = "/xianProjectUser", method = RequestMethod.POST)
	public XianProjectUserVO xianProjectUser(String userId, String projectIds) {
		log.info("Access /IUserAuth/xianProjectUser -- 查询项目组织机构树");
		return userAuthService.xianProjectUser(userId, projectIds);
	}

	/**
	 * 分页查询授权信息
	 */
	@RequestMapping(path = "/queryUserAuthPage", method = RequestMethod.POST)
	public PageBean queryUserAuthPage(@RequestBody AuthVO authVO) {
		return userAuthService.queryUserAuthPage(authVO);
	}
	

}
