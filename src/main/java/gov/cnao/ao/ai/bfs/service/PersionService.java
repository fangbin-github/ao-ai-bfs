package gov.cnao.ao.ai.bfs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import gov.cnao.ao.ai.bfs.entity.Persons;
import gov.cnao.ao.ai.bfs.mapper.PersonsMapper;
import gov.cnao.ao.ai.bfs.util.CommonUtil;

@Service
public class PersionService {
	
    @Autowired
    private PersonsMapper personsMapper;
    
//    /**
//     * 通过主键查询人员信息
//     * @param id
//     * @return
//     */
//    public Persons findPersion(String id){
//        return personsMapper.selectByPrimaryKey(id);
//    }

    /**
     * 查询人员信息列表
     * @return
     */
	public List<Persons> queryPersons(Persons persons) {
		return personsMapper.selectByExample(persons);
	}

	/**
	 * 新增人员列表
	 * @param persions
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Persons save(Persons persons) {
		personsMapper.insertPersons(persons);
		return persons;
	}

	/**
	 * 修改人员信息
	 * @param persions
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Persons update(Persons persons) {
		personsMapper.updatePersons(persons);
		return persons;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public int deletePersons(Persons persons) {
		return personsMapper.deletePersons(persons);
	}

}
