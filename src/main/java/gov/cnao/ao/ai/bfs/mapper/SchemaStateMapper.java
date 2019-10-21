package gov.cnao.ao.ai.bfs.mapper;

import org.apache.ibatis.annotations.Mapper;

import gov.cnao.ao.ai.bfs.entity.SchemaState;

@Mapper
public interface SchemaStateMapper {
	
	/**
	 * schema状态表删除
	 * @param schemaState
	 * @return
	 */
    int deleteSchemaState(SchemaState schemaState);
    
    /**
     * schema状态表新增
     * @param schemaState
     */
    void insertSchemaState(SchemaState schemaState);

    /**
     * schema状态表选择性新增
     * @param schemaState
     */
    void insertSelective(SchemaState schemaState);

    /**
     * schema状态表信息查询
     * @param schemaState
     * @return
     */
    SchemaState querySchemaState(SchemaState schemaState);

    /**
     * schema状态表选择性修改
     * @param schemaState
     */
    void updateByPrimaryKeySelective(SchemaState schemaState);

    /**
     * schema状态表修改
     * @param schemaState
     */
    void updateSchemaState(SchemaState schemaState);
}