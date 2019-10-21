package gov.cnao.ao.ai.bfs.service;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import com.oscar.jdbc.Array;

import gov.cnao.ao.ai.bfs.common.BaseResponse;
import gov.cnao.ao.ai.bfs.common.ResponseHeadUtil;
import gov.cnao.ao.ai.bfs.common.RetCodeEnum;
import gov.cnao.ao.ai.bfs.config.DynamicDataSource;
import gov.cnao.ao.ai.bfs.config.SwitchDB;
import gov.cnao.ao.ai.bfs.entity.SchemaState;
import gov.cnao.ao.ai.bfs.mapper.HomePageMapper;
import gov.cnao.ao.ai.bfs.mapper.OperLogMapper;
import gov.cnao.ao.ai.bfs.mapper.SchemaStateMapper;
import gov.cnao.ao.ai.bfs.util.CommonUtil;
import gov.cnao.ao.ai.bfs.util.DateUtil;
import gov.cnao.ao.ai.bfs.vo.MethodStatisticalVO;
import gov.cnao.ao.ai.bfs.vo.OperLogVO;
import gov.cnao.ao.ai.bfs.vo.SchemVO;

@Service
public class HomePageService {
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(HomePageService.class);

	@Autowired
	private HomePageMapper homePageMapper;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private DynamicDataSource dynamicDataSource;
	
	@Autowired
	private SwitchDB switchDB;
	
	@Autowired
	private OperLogMapper operLogMapper;
	
	@Autowired
	private SchemaStateMapper schemaStateMapper;
	
	/**
	 * 查询项目Schem信息
	 * @param schemVO
	 * @return
	 */
	public BaseResponse<SchemVO> queryPrjSchem(SchemVO schemVO) {
		BaseResponse<SchemVO> baseResponse = new BaseResponse<SchemVO>();
		try {
			stringRedisTemplate.opsForHash().put("user", "auditPrjId", schemVO.getAuditPrjId());
			stringRedisTemplate.opsForHash().put("user", "auditPrjCd", schemVO.getAuditPrjCd());
			stringRedisTemplate.opsForHash().put("user", "auditPrjNm", schemVO.getAuditPrjNm());
			schemVO.setTableSchem("SCM_" + schemVO.getAuditPrjId());
			SchemVO schem = homePageMapper.queryPrjSchem(schemVO);
			baseResponse.setBody(schem);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(schemVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(schemVO, RetCodeEnum.SYS_ERROR));
			log.error("查询项目Schem信息失败", e);
		}
		return baseResponse;
	}
	
	/**
	 * 查询SQL初始化脚本执行状态
	 */
	public BaseResponse<SchemaState> querySqlExecutionStatus(SchemVO schemVO) {
		BaseResponse<SchemaState> baseResponse = new BaseResponse<SchemaState>();
		try {
			switchDB.change("BFS");
			stringRedisTemplate.opsForHash().put("user", "auditPrjId", schemVO.getAuditPrjId());
			stringRedisTemplate.opsForHash().put("user", "auditPrjCd", schemVO.getAuditPrjCd());
			stringRedisTemplate.opsForHash().put("user", "auditPrjNm", schemVO.getAuditPrjNm());
			SchemaState schemaState = new SchemaState();
			schemaState.setAuditPrjId(schemVO.getAuditPrjId());
			schemaState = schemaStateMapper.querySchemaState(schemaState);
			baseResponse.setBody(schemaState);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(schemVO));
		} catch (Exception e) {
			log.error("查询SQL初始化脚本执行状态失败", e);
		}
		return baseResponse;
	}
	
	/**
	 * 创建schema仓库
	 * @param auditPrjId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public BaseResponse<SchemaState> creSchema(SchemVO schemVO) {
		BaseResponse<SchemaState> baseResponse = new BaseResponse<SchemaState>();
		//操作日志新增
		OperLogVO LogVO = new OperLogVO();
		LogVO.setLogId(CommonUtil.getSeqNum());
		LogVO.setProjId("项目编号");
		LogVO.setUserId("用户标识");
		LogVO.setUserNm("用户名称");
		LogVO.setOrgId("机构代码");
		LogVO.setOrgNm("机构名称");
		LogVO.setLoginIp("登录IP");
		LogVO.setOperTm(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		LogVO.setLogType("01");
		LogVO.setFunFlg("创建schema仓库");
		LogVO.setLogCont("日志内容");
		LogVO.setVisitMicr("ao-ai-bfs");
		LogVO.setVisitMenu("系统管理");
		operLogMapper.insertOperLog(LogVO);
		SchemaState schemaState = new SchemaState();
		try {
			Connection conn = dynamicDataSource.getConnection();
			if(StringUtils.isNoneBlank(schemVO.getAuditPrjId())) {
	        	String sql = "CREATE SCHEMA SCM_" + schemVO.getAuditPrjId() + ";" + 
	        			"\r\nCREATE TABLESPACE TBS_"+ schemVO.getAuditPrjId() +
	        			"_DATA DATAFILE 'TBS_" + schemVO.getAuditPrjId() +"_DATA.data' SIZE 5m" + ";" ;
	        	Statement statement = conn.createStatement();
	        	statement.execute(sql);
	        	
	        	//查询状态是否存在
	        	schemaState.setAuditPrjId(schemVO.getAuditPrjId());
	        	SchemaState state = schemaStateMapper.querySchemaState(schemaState);
	        	if(state != null) {
	        		schemaState.setState("01");
	        		schemaStateMapper.updateByPrimaryKeySelective(schemaState);
	        	}else {
	        		//schema状态表新增（状态设置成执行中）
	        		schemaState.setState("01");
	        		schemaState.setCreateUser("");
	        		schemaState.setCreateUserNm("");
	        		schemaState.setCreateTms(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
	        		schemaStateMapper.insertSchemaState(schemaState);
	        	}
	        	
	        	//根据项目ID，向仓库中执行相应的 .sql文件
	        	creProLibrary(schemVO);
	        	
	        	SchemaState schema = schemaStateMapper.querySchemaState(schemaState);
	        	schemaState.setAuditPrjId(schemVO.getAuditPrjId());
	        	baseResponse.setBody(schema);
	        	baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(schemVO));
			}
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(schemVO, RetCodeEnum.SYS_ERROR));
			switchDB.change("BFS");
			delSchema(schemVO);
			schemaStateMapper.deleteSchemaState(schemaState);
			log.error("创建项目库并执行相应的 .sql文件失败", e);
		}
		return baseResponse;
	}

	/**
	 * 根据项目ID，向仓库中执行相应的 .sql文件
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void creProLibrary(SchemVO schemVO) {
		SchemaState schemaState = new SchemaState();
		Thread thread = new Thread( new Runnable() {
			Connection conn = null;
			public void run() {
				try {
					
			        if(StringUtils.isNoneBlank(schemVO.getAuditPrjId())) {
			        	conn = dynamicDataSource.getConnection();
			        	switchDB.change(schemVO.getAuditPrjId());
			        	//设置不自动提交
			        	conn.setAutoCommit(false);
			        	ScriptRunner runner = new ScriptRunner(conn);
			        	Resources.setCharset(Charset.forName("UTF-8"));
			        	runner.setSendFullScript(false);
			        	runner.setDelimiter("；");
			        	runner.setFullLineDelimiter(false);
			        	
			        	String str = "";
						ClassPathResource resource = new ClassPathResource("sql");
//						ClassPathResource resource = new ClassPathResource("sql/01_create_cz.sql");
						File file = resource.getFile();
//						byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
//						str = new String(bdata, StandardCharsets.UTF_8);
//						System.out.println(str);
						
//			        	File file = new File(str);
			        	File[] files = file.listFiles();
			        	List fileList = Arrays.asList(files);
			        	Collections.sort(fileList);
			        	
			        	for (Object object : fileList) {
			        		String obj = object.toString().substring(object.toString().lastIndexOf("\\") + 1);
			        		System.out.println("========================================" + obj);
			        		runner.setLogWriter(null);
			        		runner.runScript(Resources.getResourceAsReader("sql/" +obj));
						}
			        	conn.commit();
			        	runner.closeConnection();
			        }
			        //schema状态表新增（状态设置成已完成）
			        switchDB.change("BFS");
		        	schemaState.setAuditPrjId(schemVO.getAuditPrjId());
		        	schemaState.setState("02");
		        	schemaStateMapper.updateByPrimaryKeySelective(schemaState);
				} catch (Exception e) {
					switchDB.change("BFS");
					delSchema(schemVO);
					schemaState.setAuditPrjId(schemVO.getAuditPrjId());
					schemaStateMapper.deleteSchemaState(schemaState);
					log.error("根据项目ID，向仓库中执行相应的 .sql文件失败", e);
					try {
						if(conn != null) {
							conn.rollback();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} finally {
					try {
						if(conn != null) {
							conn.close();
						}	
						switchDB.change("BFS");
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread.start();
	}

	/**
	 * 审计方法数量统计
	 * @return
	 */
	public BaseResponse<List<MethodStatisticalVO>> getMethodStatisticalCount(MethodStatisticalVO methodStatisticalVO) {
		BaseResponse<List<MethodStatisticalVO>> baseResponse = new BaseResponse<List<MethodStatisticalVO>>();
		try {
			List<MethodStatisticalVO> list = new ArrayList<MethodStatisticalVO>();
			methodStatisticalVO = new MethodStatisticalVO();
			methodStatisticalVO.setMethodName("财政审计");
			methodStatisticalVO.setMethodCount(30);
			list.add(methodStatisticalVO);
			
			MethodStatisticalVO methodStatisticalVO1 = new MethodStatisticalVO();
			methodStatisticalVO1.setMethodName("税务审计");
			methodStatisticalVO1.setMethodCount(36);
			list.add(methodStatisticalVO1);
			
			MethodStatisticalVO methodStatisticalVO2 = new MethodStatisticalVO();
			methodStatisticalVO2.setMethodName("金融审计");
			methodStatisticalVO2.setMethodCount(65);
			list.add(methodStatisticalVO2);
			
			MethodStatisticalVO methodStatisticalVO3 = new MethodStatisticalVO();
			methodStatisticalVO3.setMethodName("土地审计");
			methodStatisticalVO3.setMethodCount(22);
			list.add(methodStatisticalVO3);
			
			MethodStatisticalVO methodStatisticalVO4 = new MethodStatisticalVO();
			methodStatisticalVO4.setMethodName("企业审计");
			methodStatisticalVO4.setMethodCount(88);
			list.add(methodStatisticalVO4);
			
			MethodStatisticalVO methodStatisticalVO5 = new MethodStatisticalVO();
			methodStatisticalVO5.setMethodName("社保审计");
			methodStatisticalVO5.setMethodCount(40);
			list.add(methodStatisticalVO5);
			
			MethodStatisticalVO methodStatisticalVO6 = new MethodStatisticalVO();
			methodStatisticalVO6.setMethodName("工商审计");
			methodStatisticalVO6.setMethodCount(48);
			list.add(methodStatisticalVO6);
			
			MethodStatisticalVO methodStatisticalVO7 = new MethodStatisticalVO();
			methodStatisticalVO7.setMethodName("医疗审计");
			methodStatisticalVO7.setMethodCount(36);
			list.add(methodStatisticalVO7);
			baseResponse.setBody(list);
//			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(methodStatisticalVO));
		} catch (Exception e) {
//			baseResponse.setHead(ResponseHeadUtil.buildFailHead(methodStatisticalVO, RetCodeEnum.SYS_ERROR));
			log.error("审计方法数量统计失败", e);
		}
		return baseResponse;
	}

	/**
	 * 删除schema仓库
	 * @param schemVO
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public BaseResponse<SchemaState> delSchema(SchemVO schemVO) {
		BaseResponse<SchemaState> baseResponse = new BaseResponse<SchemaState>();
		Connection conn = null;
		try {
			//操作日志新增
			OperLogVO LogVO = new OperLogVO();
			LogVO.setLogId(CommonUtil.getSeqNum());
			LogVO.setProjId("项目编号");
			LogVO.setUserId("用户标识");
			LogVO.setUserNm("用户名称");
			LogVO.setOrgId("机构代码");
			LogVO.setOrgNm("机构名称");
			LogVO.setLoginIp("登录IP");
			LogVO.setOperTm(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			LogVO.setLogType("01");
			LogVO.setFunFlg("删除schema仓库");
			LogVO.setLogCont("日志内容");
			LogVO.setVisitMicr("ao-ai-bfs");
			LogVO.setVisitMenu("系统管理");
			operLogMapper.insertOperLog(LogVO);
			conn = dynamicDataSource.getConnection();
			if(StringUtils.isNoneBlank(schemVO.getAuditPrjId())) {
	        	String sql = "DROP SCHEMA SCM_" + schemVO.getAuditPrjId() + " CASCADE;" + 
	        			"\r\nDROP TABLESPACE TBS_"+ schemVO.getAuditPrjId() + "_DATA;" ;
	        	Statement statement = conn.createStatement();
	        	statement.execute(sql);
			}
			SchemaState schemaState = new SchemaState();
			schemaState.setAuditPrjId(schemVO.getAuditPrjId());
        	schemaState.setState("04");
        	schemaStateMapper.updateByPrimaryKeySelective(schemaState);
        	
			baseResponse.setBody(schemaState);
			baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(schemVO));
		} catch (Exception e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(schemVO, RetCodeEnum.SYS_ERROR));
			log.error("删除schema仓库失败", e);
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return baseResponse;
	}

}
