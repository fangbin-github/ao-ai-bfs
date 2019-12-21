package gov.cnao.ao.ai.bfs.controller;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.contract.IHomePage;
import gov.cnao.ao.ai.bfs.entity.SchemaState;
import gov.cnao.ao.ai.bfs.service.HomePageService;
import gov.cnao.ao.ai.bfs.vo.MethodStatisticalVO;
import gov.cnao.ao.ai.bfs.vo.SchemVO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestSchema(schemaId = "iHomePage")
@RequestMapping(path = "/homePage")
public class HomePageController implements IHomePage {
	
	private final static org.slf4j.Logger log = LoggerFactory.getLogger(HomePageController.class);
	
	@Autowired
	private HomePageService homePageService;
	
	/**
	 * 查询项目Schem信息
	 * @throws IOException 
	 */
	@RequestMapping(path = "/queryPrjSchem", method = RequestMethod.POST)
	public BaseResponse<SchemVO> queryPrjSchem(@RequestBody SchemVO schemVO) throws IOException {
		log.info("Access /IHomePage/queryPrjSchem -- 查询项目Schem信息");
		return homePageService.queryPrjSchem(schemVO);
	}
	
	/**
	 * 查询SQL初始化脚本执行状态
	 * @throws IOException 
	 */
	@Override
	@RequestMapping(path = "/querySqlExecutionStatus", method = RequestMethod.POST)
	public BaseResponse<SchemaState> querySqlExecutionStatus(@RequestBody SchemVO schemVO) throws IOException {
		log.info("Access /IHomePage/querySqlExecutionStatus -- 查询SQL初始化脚本执行状态");
		return homePageService.querySqlExecutionStatus(schemVO);
	}

	/**
	 * 根据项目ID，向仓库中执行相应的 .sql文件
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	@RequestMapping(path = "/creProLibrary", method = RequestMethod.POST)
	public void creProLibrary(@RequestBody SchemVO schemVO) 
				throws IOException, SQLException {
		log.info("Access /IHomePage/creProLibrary -- 根据项目ID，向仓库中执行相应的 .sql文件");
		homePageService.creProLibrary(schemVO);
	}
	
	/**
	 * 创建schema仓库
	 * @param auditPrjId
	 * @return
	 */
	@RequestMapping(path = "/creSchema", method = RequestMethod.POST)
	public BaseResponse<SchemaState> creSchema(@RequestBody SchemVO schemVO) {
		log.info("Access /IHomePage/creSchema -- 创建schema仓库");
		return homePageService.creSchema(schemVO);
	}

	/**
	 * 审计方法数量统计
	 */
	@Override
	@RequestMapping(path = "/getMethodStatisticalCount", method = RequestMethod.POST)
	public BaseResponse<List<MethodStatisticalVO>> getMethodStatisticalCount(@RequestBody MethodStatisticalVO methodStatisticalVO) {
		log.info("Access /IHomePage/getMethodStatisticalCount -- 审计方法数量统计");
		return homePageService.getMethodStatisticalCount(methodStatisticalVO);
	}

	/**
	 * 删除schema仓库
	 * @param schemVO
	 * @return
	 */
	@Override
	@RequestMapping(path = "/delSchema", method = RequestMethod.POST)
	public BaseResponse<SchemaState> delSchema(@RequestBody SchemVO schemVO) {
		return homePageService.delSchema(schemVO);
	}

}
