package gov.cnao.ao.ai.bfs.mapper;

import org.apache.ibatis.annotations.Mapper;

import gov.cnao.ao.ai.bfs.vo.SchemVO;

@Mapper
public interface HomePageMapper {

	/**
	 * 查询项目Schem信息
	 * @param schemVO
	 * @return
	 */
	SchemVO queryPrjSchem(SchemVO schemVO);

}
