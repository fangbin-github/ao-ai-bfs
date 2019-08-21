package gov.cnao.ao.ai.bfs.mapper;

import gov.cnao.ao.ai.bfs.entity.Persons;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonsMapper {

	/**
	 * 通过主键查询人员信息
	 * @param persions
	 * @return
	 */
    List<Persons> selectByExample(Persons persons);

//    /**
//     * 查询人员信息列表
//     * @param id
//     * @return
//     */
//    Persons selectByPrimaryKey(String id);

    /**
     * 新增人员信息
     * @param persions
     */
	void insertPersons(Persons persons);

	/**
	 * 修改人员信息
	 * @param persions
	 */
	void updatePersons(Persons persons);

	/**
	 * 删除人员信息
	 * @param inValues
	 * @return
	 */
	int deletePersons(Persons persons);
}