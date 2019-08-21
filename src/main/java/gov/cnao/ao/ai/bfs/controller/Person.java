package gov.cnao.ao.ai.bfs.controller;

import java.util.List;

import gov.cnao.ao.ai.bfs.entity.Persons;

public interface Person {
	
//	/**
//	 * 通过主键查询人员信息
//	 * @param id
//	 * @return
//	 */
//    String findPersionById(String id);
    
    /**
     * 查询人员信息列表
     * @param persions
     * @return
     */
    List<Persons> queryPersions(Persons persions);
    
    /**
     * 新增人员信息
     * @param persions
     * @return
     */
    Persons save(Persons persions);
    
    /**
     * 修改人员信息
     * @param persions
     * @return
     */
    Persons update(Persons persions);
    
    /**
     * 删除人员信息
     * @param ids
     * @return
     */
    int deletePersions(Persons persions);
    
}
