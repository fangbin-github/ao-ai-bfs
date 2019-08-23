package gov.cnao.ao.ai.bfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.cnao.ao.ai.bfs.entity.Person;
import gov.cnao.ao.ai.bfs.mapper.PersonMapper;

@Service
public class PersonService {
	
    @Autowired
    private PersonMapper personMapper;
    
    /**
     * 查询人员信息列表
     * @param person
     * @return
     */
	public List<Person> queryPerson(Person person) {
		return personMapper.queryPerson(person);
	}

	/**
	 * 新增人员列表
	 * @param person
	 * @return
	 */
	public Person insertPerson(Person person) {
		personMapper.insertPerson(person);
		return person;
	}

	/**
	 * 修改人员信息
	 * @param person
	 * @return
	 */
	public Person updatePerson(Person person) {
		personMapper.updatePerson(person);
		return person;
	}

	/**
	 * 删除人员信息
	 * @param person
	 * @return
	 */
	public int deletePerson(Person person) {
		return personMapper.deletePerson(person);
	}

}
