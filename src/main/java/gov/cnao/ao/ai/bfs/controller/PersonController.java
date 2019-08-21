package gov.cnao.ao.ai.bfs.controller;

import java.util.List;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.entity.Persons;
import gov.cnao.ao.ai.bfs.service.PersionService;

@RestSchema(schemaId = "person")
@RequestMapping(path = "/person")
public class PersonController implements Person {
    @Autowired
    private PersionService persionService;
    
    /**
     * 查询人员列表
     */
	@Override
	@RequestMapping(path = "/queryPersons", method = RequestMethod.POST)
	public List<Persons> queryPersions(@RequestBody Persons persons) {
		return persionService.queryPersons(persons);
	}

	/**
	 * 新增人员信息
	 */
	@Override
	@RequestMapping(path = "/insertPersons", method = RequestMethod.POST)
	public Persons save(@RequestBody Persons persons) {
		return persionService.save(persons);
	}
	
	/**
	 * 修改人员信息
	 */
	@Override
	@RequestMapping(path = "/updatePersons", method = RequestMethod.POST)
	public Persons update(@RequestBody Persons persons) {
		return persionService.update(persons);
	}
	
	@Override
	@RequestMapping(path = "/deletePersons", method = RequestMethod.POST)
	public int deletePersions(@RequestBody Persons persons){
    	return persionService.deletePersons(persons);
    }

}
