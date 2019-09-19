package gov.cnao.ao.ai.bfs.controller;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.contract.IPerson;
import gov.cnao.ao.ai.bfs.entity.Person;
import gov.cnao.ao.ai.bfs.service.PersonService;

@RestSchema(schemaId = "iPerson")
@RequestMapping(path = "/person")
public class PersonController implements IPerson {
    @Autowired
    private PersonService personService;
    
    /**
     * 查询人员列表
     */
	@Override
	@RequestMapping(path = "/queryPerson", method = RequestMethod.POST)
	public List<Person> queryPerson(@RequestBody Person person) {
		return personService.queryPerson(person);
	}

	/**
	 * 新增人员信息
	 */
	@Override
	@RequestMapping(path = "/insertPerson", method = RequestMethod.POST)
	public Person insertPerson(@RequestBody Person person) {
		return personService.insertPerson(person);
	}
	
	/**
	 * 修改人员信息
	 */
	@Override
	@RequestMapping(path = "/updatePerson", method = RequestMethod.POST)
	public Person updatePerson(@RequestBody Person person) {
		return personService.updatePerson(person);
	}
	
	@Override
	@RequestMapping(path = "/deletePerson", method = RequestMethod.POST)
	public int deletePerson(@RequestBody Person person){
    	return personService.deletePerson(person);
    }

}
