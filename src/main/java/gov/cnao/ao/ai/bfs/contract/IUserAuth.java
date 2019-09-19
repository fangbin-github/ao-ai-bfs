package gov.cnao.ao.ai.bfs.contract;

import java.util.List;

import gov.cnao.ao.ai.bfs.entity.UserAuth;
import gov.cnao.ao.ai.bfs.vo.UserAuthVO;
import gov.cnao.ao.ai.bfs.vo.UsersVO;
import gov.cnao.ao.ai.bfs.vo.XianProjectUserVO;

public interface IUserAuth {
	
	/**
	 * 查询授权信息
	 * @param userAuth
	 * @return
	 */
	List<UserAuth> queryUserAuth(UserAuth userAuth);
	
	/**
	 * 新增授权信息
	 * @param userAuth
	 * @return
	 */
	UserAuth insertUserAuth(UserAuth userAuth);
	
	/**
	 * 删除授权信息
	 * @param userAuth
	 * @return
	 */
	int deleteUserAuth(UserAuth userAuth);
	
	/**
	 * 授权
	 * @param userAuths
	 * @return
	 */
	List<UserAuth> getAuth(UserAuthVO userAuthVO);
	
	/**
	 * 取消授权
	 * @param userAuths
	 * @return
	 */
	List<UserAuth> canAuth(List<UserAuth> userAuths);
	
	/**
	 * 查询项目组织机构树
	 * @return
	 */
	XianProjectUserVO xianProjectUser();
	
}
