package gov.cnao.ao.ai.bfs.contract;

import java.util.List;

import gov.cnao.ao.ai.bfs.entity.Person;

public interface IPerson {
	
    /**
     * 查询人员信息列表
     * @param person
     * @return
     */
    List<Person> queryPerson(Person person);
    
    /**
     * 新增人员信息
     * @param person
     * @return
     */
    Person insertPerson(Person person);
    
    /**
     * 修改人员信息
     * @param person
     * @return
     */
    Person updatePerson(Person person);
    
    /**
     * 删除人员信息
     * @param person
     * @return
     */
    int deletePerson(Person person);
    
}
