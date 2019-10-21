package gov.cnao.ao.ai.bfs.contract;

import java.util.List;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.entity.UserAuth;
import gov.cnao.ao.ai.bfs.vo.AuthVO;
import gov.cnao.ao.ai.bfs.vo.OrgTreeVO;
import gov.cnao.ao.ai.bfs.vo.PageBean;
import gov.cnao.ao.ai.bfs.vo.UserAuthVO;
import gov.cnao.ao.ai.bfs.vo.UserAuthsVO;
import gov.cnao.ao.ai.bfs.vo.XianProjectUserVO;

public interface IUserAuth {
	
	/**
	 * 查询授权信息
	 * @param userAuth
	 * @return
	 */
	List<UserAuth> queryUserAuth(AuthVO authVO);
	
	/**
	 * 分页查询查询授权信息
	 * @param authVO
	 * @return
	 */
	BaseResponse<PageBean> queryUserAuthPage(AuthVO authVO);
	
	/**
	 * 新增授权信息
	 * @param userAuth
	 * @return
	 */
	AuthVO insertUserAuth(AuthVO authVO);
	
	/**
	 * 删除授权信息
	 * @param userAuth
	 * @return
	 */
	int deleteUserAuth(UserAuthsVO userAuthsVO);
	
	/**
	 * 授权
	 * @param userAuths
	 * @return
	 */
	BaseResponse<List<AuthVO>> getAuth(UserAuthVO userAuthVO);
	
	/**
	 * 取消授权
	 * @param userAuths
	 * @return
	 */
	BaseResponse<List<UserAuthsVO>> canAuth(AuthVO authVO);
	
	/**
	 * 查询项目组织机构树
	 * @return
	 */
	XianProjectUserVO xianProjectUser(OrgTreeVO orgTreeVO);
	
}
