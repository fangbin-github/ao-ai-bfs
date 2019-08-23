package gov.cnao.ao.ai.bfs.mapper;

import gov.cnao.ao.ai.bfs.entity.Person;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper {

	/**
	 * 通过主键查询人员信息
	 * @param person
	 * @return
	 */
    List<Person> queryPerson(Person person);


    /**
     * 新增人员信息
     * @param person
     */
	void insertPerson(Person person);

	/**
	 * 修改人员信息
	 * @param person
	 */
	void updatePerson(Person person);

	/**
	 * 删除人员信息
	 * @param person
	 * @return
	 */
	int deletePerson(Person person);
}