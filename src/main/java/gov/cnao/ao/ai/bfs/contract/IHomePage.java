package gov.cnao.ao.ai.bfs.contract;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.entity.SchemaState;
import gov.cnao.ao.ai.bfs.vo.MethodStatisticalVO;
import gov.cnao.ao.ai.bfs.vo.SchemVO;

public interface IHomePage {
	
	/**
	 * 查询项目Schem信息
	 * @param schemVO
	 * @return
	 * @throws IOException 
	 */
	BaseResponse<SchemVO> queryPrjSchem(SchemVO schemVO) throws IOException;
	
	/**
	 * 查询SQL初始化脚本执行状态
	 * @throws IOException 
	 */
	BaseResponse<SchemaState> querySqlExecutionStatus(SchemVO schemVO) throws IOException;
	
	/**
	 * 创建schema仓库
	 * @param auditPrjId
	 * @return
	 */
	BaseResponse<SchemaState> creSchema(SchemVO schemVO);
	
	/**
	 * 删除schema仓库
	 * @param schemVO
	 * @return
	 */
	BaseResponse<SchemaState> delSchema(SchemVO schemVO);
	
	/**
	 * 根据项目ID，向仓库中执行相应的 .sql文件
	 */
	void creProLibrary(SchemVO schemVO) throws IOException,
				SQLException, ClassNotFoundException;
	
	/**
	 * 审计方法数量统计
	 * @return
	 */
	BaseResponse<List<MethodStatisticalVO>> getMethodStatisticalCount(
				MethodStatisticalVO methodStatisticalVO);
}
