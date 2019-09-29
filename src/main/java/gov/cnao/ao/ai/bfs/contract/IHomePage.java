package gov.cnao.ao.ai.bfs.contract;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import gov.cnao.ao.ai.bfs.vo.MethodStatisticalVO;
import gov.cnao.ao.ai.bfs.vo.SchemVO;

public interface IHomePage {
	
	/**
	 * 查询项目Schem信息
	 * @param schemVO
	 * @return
	 */
	SchemVO queryPrjSchem(SchemVO schemVO);
	
	/**
	 * 创建schema仓库
	 * @param auditPrjId
	 * @return
	 */
	public Boolean creSchema(SchemVO schemVO);
	
	/**
	 * 根据项目ID，向仓库中执行相应的 .sql文件
	 */
	Boolean creProLibrary(SchemVO schemVO) throws IOException,
				SQLException, ClassNotFoundException;
	
	/**
	 * 审计方法数量统计
	 * @return
	 */
	List<MethodStatisticalVO> getMethodStatisticalCount(MethodStatisticalVO methodStatisticalVO);
}
