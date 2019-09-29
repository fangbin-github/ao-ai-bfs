package gov.cnao.ao.ai.bfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gov.cnao.ao.ai.bfs.entity.UserAuth;
import gov.cnao.ao.ai.bfs.vo.AuthVO;

@Mapper
public interface UserAuthMapper {
	
	/**
	 * 查询授权信息
	 * @param userAuth
	 * @return
	 */
	List<UserAuth> queryUserAuth(AuthVO authVO);
	
	/**
	 * 删除授权信息
	 * @param userAuth
	 * @return
	 */
    int deleteUserAuth(AuthVO authVO);

    /**
     * 新增授权信息
     * @param userAuth
     */
    void insertUserAuth(AuthVO authVO);

    /**
     * 修改授权信息
     * @param userAuth
     */
    void updateUserAuth(AuthVO authVO);

    /**
     * 选择性新增授权信息
     * @param userAuth
     */
    void insertSelective(AuthVO authVO);
    
    /**
     * 选择性修改授权信息
     * @param userAuth
     */
    void updateByPrimaryKeySelective(AuthVO authVO);

    /**
     * 分页查询授权信息
     * @param authVO
     * @return
     */
	List<UserAuth> queryUserAuthPage(AuthVO authVO);

	/**
	 * 查询授权信息总数
	 * @param authVO
	 * @return
	 */
	int queryUserAuthCount(AuthVO authVO);
}