package gov.cnao.ao.ai.bfs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
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
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Service
public class HomePageService {
	
	private final static org.slf4j.Logger log = LoggerFactory.getLogger(HomePageService.class);
	private final static String AUDIT_PRJ_ID= "auditPrjId";
	private final static String AUDIT_PRJ_CD= "auditPrjCd";
	private final static String AUDIT_PRJ_NM= "auditPrjNm";

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
	
	@Autowired
	private Environment env;
	
	@Autowired
	private RedisTemplateService redisTemplateService;
	
	/**
	 * 查询项目Schem信息
	 * @param schemVO
	 * @return
	 * @throws IOException 
	 */
	public BaseResponse<SchemVO> queryPrjSchem(SchemVO schemVO) throws IOException {
		BaseResponse<SchemVO> baseResponse = new BaseResponse<SchemVO>();
		Map<String, String> map = new HashMap<String, String>();
		map.put(AUDIT_PRJ_ID, schemVO.getAuditPrjId());
		map.put(AUDIT_PRJ_CD, schemVO.getAuditPrjCd());
		map.put(AUDIT_PRJ_NM, schemVO.getAuditPrjNm());
		if(schemVO.getHead().getUsno()!=null || !schemVO.getHead().getUsno().equals("")) {
			Set<HostAndPort> hosts = redisTemplateService.getHosts();
			final JedisCluster client = new JedisCluster(hosts, 15000);
			client.hmset(schemVO.getHead().getUsno(), map);
			client.close();
//			stringRedisTemplate.opsForHash().put(schemVO.getHead().getUsno(), "auditPrjId", schemVO.getAuditPrjId());
//			stringRedisTemplate.opsForHash().put(schemVO.getHead().getUsno(), "auditPrjCd", schemVO.getAuditPrjCd());
//			stringRedisTemplate.opsForHash().put(schemVO.getHead().getUsno(), "auditPrjNm", schemVO.getAuditPrjNm());
		}
		schemVO.setTableSchem("SCM_" + schemVO.getAuditPrjId());
		SchemVO schem = homePageMapper.queryPrjSchem(schemVO);
		baseResponse.setBody(schem);
		baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(schemVO));
		return baseResponse;
	}
	
	/**
	 * 查询SQL初始化脚本执行状态
	 * @throws IOException 
	 */
	public BaseResponse<SchemaState> querySqlExecutionStatus(SchemVO schemVO) throws IOException {
		BaseResponse<SchemaState> baseResponse = new BaseResponse<SchemaState>();
		switchDB.change();
		Map<String, String> map = new HashMap<String, String>();
		map.put(AUDIT_PRJ_ID, schemVO.getAuditPrjId());
		map.put(AUDIT_PRJ_CD, schemVO.getAuditPrjCd());
		map.put(AUDIT_PRJ_NM, schemVO.getAuditPrjNm());
		if(schemVO.getHead().getUsno()!=null || !schemVO.getHead().getUsno().equals("")) {
			Set<HostAndPort> hosts = redisTemplateService.getHosts();
			final JedisCluster client = new JedisCluster(hosts, 15000);
			client.hmset(schemVO.getHead().getUsno(), map);
			client.close();
//			stringRedisTemplate.opsForHash().put(schemVO.getHead().getUsno(), "auditPrjId", schemVO.getAuditPrjId());
//			stringRedisTemplate.opsForHash().put(schemVO.getHead().getUsno(), "auditPrjCd", schemVO.getAuditPrjCd());
//			stringRedisTemplate.opsForHash().put(schemVO.getHead().getUsno(), "auditPrjNm", schemVO.getAuditPrjNm());
		}
		SchemaState schemaState = new SchemaState();
		schemaState.setAuditPrjId(schemVO.getAuditPrjId());
		schemaState = schemaStateMapper.querySchemaState(schemaState);
		baseResponse.setBody(schemaState);
		baseResponse.setHead(ResponseHeadUtil.buildSuccessHead(schemVO));
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
		} catch (SQLException e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(schemVO, RetCodeEnum.SYS_ERROR));
			switchDB.change();
			delSchema(schemVO);
			schemaState.setAuditPrjId(schemVO.getAuditPrjId());
        	schemaState.setState("04");
        	schemaStateMapper.updateByPrimaryKeySelective(schemaState);
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
			        	conn = dynamicDataSource.getConnection(schemVO.getAuditPrjId());
			        	//设置不自动提交
			        	conn.setAutoCommit(false);
			        	ScriptRunner runner = new ScriptRunner(conn);
			        	Resources.setCharset(Charset.forName("UTF-8"));
			        	runner.setSendFullScript(false);
			        	runner.setDelimiter("；");
			        	runner.setFullLineDelimiter(false);
			        	File file = new File(env.getProperty("sqlPath"));
		        		File[] files = file.listFiles();
		        		if(files != null) {
		        			List<File> fileList = Arrays.asList(files);
		        			Collections.sort(fileList);
		        			for (File object : fileList) {
				        		runner.setLogWriter(null);
								runner.runScript(new InputStreamReader(new FileInputStream(object),"UTF-8"));
							}
		        		}
			        	conn.commit();
			        	runner.closeConnection();
			        }
			        //schema状态表新增（状态设置成已完成）
			        switchDB.change();
		        	schemaState.setAuditPrjId(schemVO.getAuditPrjId());
		        	schemaState.setState("02");
		        	schemaStateMapper.updateByPrimaryKeySelective(schemaState);
				} catch (SQLException e) {
					switchDB.change();
					delSchema(schemVO);
					schemaState.setAuditPrjId(schemVO.getAuditPrjId());
		        	schemaState.setState("04");
		        	schemaStateMapper.updateByPrimaryKeySelective(schemaState);
					log.error("根据项目ID，向仓库中执行相应的 .sql文件失败", e);
					try {
						if(conn != null) {
							conn.rollback();
						}
					} catch (SQLException e1) {
						log.error("数据库连接失败", e1);
					}
				} catch (UnsupportedEncodingException e) {
					switchDB.change();
					delSchema(schemVO);
					schemaState.setAuditPrjId(schemVO.getAuditPrjId());
		        	schemaState.setState("04");
		        	schemaStateMapper.updateByPrimaryKeySelective(schemaState);
					log.error("根据项目ID，向仓库中执行相应的 .sql文件失败", e);
					try {
						if(conn != null) {
							conn.rollback();
						}
					} catch (SQLException e1) {
						log.error("数据库连接失败", e1);
					}
				} catch (FileNotFoundException e) {
					switchDB.change();
					delSchema(schemVO);
					schemaState.setAuditPrjId(schemVO.getAuditPrjId());
		        	schemaState.setState("04");
		        	schemaStateMapper.updateByPrimaryKeySelective(schemaState);
					log.error("根据项目ID，向仓库中执行相应的 .sql文件失败", e);
					try {
						if(conn != null) {
							conn.rollback();
						}
					} catch (SQLException e1) {
						log.error("数据库连接失败", e1);
					}
				}finally {
					try {
						if(conn != null) {
							conn.close();
						}	
						switchDB.change();
					} catch (SQLException e) {
						log.error("数据库连接失败", e);
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
		List<MethodStatisticalVO> list = new ArrayList<MethodStatisticalVO>();
		methodStatisticalVO = new MethodStatisticalVO();
		methodStatisticalVO.setMethodName("财政审计");
		methodStatisticalVO.setMethodCount(30);
		list.add(methodStatisticalVO);
		
		MethodStatisticalVO methodStatisticalVO1 = new MethodStatisticalVO();
		methodStatisticalVO1.setMethodName("税务审计");
		methodStatisticalVO1.setMethodCount(0);
		list.add(methodStatisticalVO1);
		
		MethodStatisticalVO methodStatisticalVO2 = new MethodStatisticalVO();
		methodStatisticalVO2.setMethodName("金融审计");
		methodStatisticalVO2.setMethodCount(6);
		list.add(methodStatisticalVO2);
		
		MethodStatisticalVO methodStatisticalVO3 = new MethodStatisticalVO();
		methodStatisticalVO3.setMethodName("土地审计");
		methodStatisticalVO3.setMethodCount(0);
		list.add(methodStatisticalVO3);
		
		MethodStatisticalVO methodStatisticalVO4 = new MethodStatisticalVO();
		methodStatisticalVO4.setMethodName("企业审计");
		methodStatisticalVO4.setMethodCount(4);
		list.add(methodStatisticalVO4);
		
		MethodStatisticalVO methodStatisticalVO5 = new MethodStatisticalVO();
		methodStatisticalVO5.setMethodName("社保审计");
		methodStatisticalVO5.setMethodCount(0);
		list.add(methodStatisticalVO5);
		
		MethodStatisticalVO methodStatisticalVO6 = new MethodStatisticalVO();
		methodStatisticalVO6.setMethodName("工商审计");
		methodStatisticalVO6.setMethodCount(0);
		list.add(methodStatisticalVO6);
		
		MethodStatisticalVO methodStatisticalVO7 = new MethodStatisticalVO();
		methodStatisticalVO7.setMethodName("医疗审计");
		methodStatisticalVO7.setMethodCount(0);
		list.add(methodStatisticalVO7);
		baseResponse.setBody(list);
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
		} catch (SQLException e) {
			baseResponse.setHead(ResponseHeadUtil.buildFailHead(schemVO, RetCodeEnum.SYS_ERROR));
			log.error("删除schema仓库失败", e);
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}	
			} catch (SQLException e) {
				log.error("删除schema仓库失败", e);
			}
		}
		return baseResponse;
	}

}
