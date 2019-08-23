package gov.cnao.ao.ai.bfs.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * 授权
	 * @param userAuths
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public List<UserAuth> getAuth(List<UserAuth> userAuths) {
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");
		for (int i = 0; i < userAuths.size(); i++) {
			UserAuth userAuth = userAuths.get(i);
			userAuth.setCreateUserId("" + System.currentTimeMillis() + "");
			userAuth.setCreateUserNm("测试" + (i+1));
			userAuth.setCreateTm(format.format(new Date()));
			userAuthMapper.insertUserAuth(userAuth);
		}
		return userAuths;
	}

}
