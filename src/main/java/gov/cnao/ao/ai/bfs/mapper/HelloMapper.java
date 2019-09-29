package gov.cnao.ao.ai.bfs.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HelloMapper {
	
	/**
	 * 通过ID查询T_DMS_AUDI_METH_COL表columnDes信息
	 * @param id
	 * @return
	 */
	String queryAudiMethCol(String id);
	
}
