package gov.cnao.ao.ai.bfs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import gov.cnao.ao.ai.bfs.entity.UserAuth;

@Mapper
public interface UserAuthMapper {
	
	/**
	 * 查询授权信息
	 * @param userAuth
	 * @return
	 */
	List<UserAuth> queryUserAuth(UserAuth userAuth);
	
	/**
	 * 删除授权信息
	 * @param userAuth
	 * @return
	 */
    int deleteUserAuth(UserAuth userAuth);

    /**
     * 新增授权信息
     * @param userAuth
     */
    void insertUserAuth(UserAuth userAuth);

    /**
     * 修改授权信息
     * @param userAuth
     */
    void updateUserAuth(UserAuth userAuth);

    /**
     * 选择性新增授权信息
     * @param userAuth
     */
    void insertSelective(UserAuth userAuth);
    
    /**
     * 选择性修改授权信息
     * @param userAuth
     */
    void updateByPrimaryKeySelective(UserAuth userAuth);
}