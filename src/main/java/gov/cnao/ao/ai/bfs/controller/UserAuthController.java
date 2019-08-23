package gov.cnao.ao.ai.bfs.controller;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.contract.IUserAuth;
import gov.cnao.ao.ai.bfs.entity.UserAuth;
import gov.cnao.ao.ai.bfs.service.UserAuthService;

@RestSchema(schemaId = "iUserAuth")
@RequestMapping(path = "/ao-ai-bfs/auth")
public class UserAuthController implements IUserAuth{
	
	@Autowired
	private UserAuthService userAuthService;
	
	/**
	 * 查询授权信息列表
	 */
	@Override
	@RequestMapping(path = "/queryUserAuth", method = RequestMethod.POST)
	public List<UserAuth> queryUserAuth(@RequestBody UserAuth userAuth) {
		return userAuthService.queryUserAuth(userAuth);
	}

	/**
	 * 新增授权信息
	 */
	@Override
	@RequestMapping(path = "/insertUserAuth", method = RequestMethod.POST)
	public UserAuth insertUserAuth(@RequestBody UserAuth userAuth) {
		return userAuthService.insertUserAuth(userAuth);
	}

	/**
	 * 删除授权信息
	 */
	@Override
	@RequestMapping(path = "/deleteUserAuth", method = RequestMethod.POST)
	public int deleteUserAuth(@RequestBody UserAuth userAuth) {
		return userAuthService.deleteUserAuth(userAuth);
	}

	/**
	 * 授权
	 */
	@Override
	@RequestMapping(path = "/getAuth", method = RequestMethod.POST)
	public List<UserAuth> getAuth(@RequestBody List<UserAuth> userAuths) {
		return userAuthService.getAuth(userAuths);
	}
	
	

}
