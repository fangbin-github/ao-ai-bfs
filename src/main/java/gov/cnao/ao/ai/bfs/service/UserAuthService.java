package gov.cnao.ao.ai.bfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.cnao.ao.ai.bfs.entity.UserAuth;
import gov.cnao.ao.ai.bfs.mapper.UserAuthMapper;

@Service
public class UserAuthService {

	@Autowired
	private UserAuthMapper userAuthMapper;
	
	/**
	 * 查询授权信息列表
	 * @param userAuth
	 * @return
	 */
	public List<UserAuth> queryUserAuth(UserAuth userAuth) {
		return userAuthMapper.queryUserAuth(userAuth);
	}

	/**
	 * 新增授权信息
	 * @param userAuth
	 * @return
	 */
	public UserAuth insertUserAuth(UserAuth userAuth) {
		userAuthMapper.insertUserAuth(userAuth);
		return userAuth;
	}

	/**
	 * 删除授权信息
	 * @param userAuth
	 * @return
	 */
	public int deleteUserAuth(UserAuth userAuth) {
		return userAuthMapper.deleteUserAuth(userAuth);
	}

}
