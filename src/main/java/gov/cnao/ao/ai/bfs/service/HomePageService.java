package gov.cnao.ao.ai.bfs.service;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oscar.jdbc.Array;

import gov.cnao.ao.ai.bfs.config.DynamicDataSource;
import gov.cnao.ao.ai.bfs.config.SwitchDB;
import gov.cnao.ao.ai.bfs.mapper.HomePageMapper;
import gov.cnao.ao.ai.bfs.vo.MethodStatisticalVO;
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
	
	/**
	 * 查询项目Schem信息
	 * @param schemVO
	 * @return
	 */
	public SchemVO queryPrjSchem(SchemVO schemVO) {
		try {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("userId", "t1001");
//			map.put("name", "测试1");
//			map.put("sex", "男");
//			stringRedisTemplate.opsForHash().putAll("user", map);
			
			stringRedisTemplate.opsForHash().put("user", "auditPrjId", schemVO.getAuditPrjId());
			stringRedisTemplate.opsForHash().put("user", "auditPrjCd", schemVO.getAuditPrjCd());
			stringRedisTemplate.opsForHash().put("user", "auditPrjNm", schemVO.getAuditPrjNm());
//			System.out.println(stringRedisTemplate.opsForHash().get("user", "auditPrjId"));
			
//			stringRedisTemplate.opsForValue().set("auditPrjId", schemVO.getAuditPrjId());
//			stringRedisTemplate.opsForValue().set("auditPrjNm", schemVO.getAuditPrjNm());
//			stringRedisTemplate.opsForValue().set("auditPrjCd", schemVO.getAuditPrjCd());
			schemVO.setTableSchem("SCM_" + schemVO.getAuditPrjId());
			return homePageMapper.queryPrjSchem(schemVO);
		} catch (Exception e) {
			log.error("查询项目Schem信息失败", e);
		}
		return null;
	}
	
	/**
	 * 创建schema仓库
	 * @param auditPrjId
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean creSchema(SchemVO schemVO) {
		try {
			Connection conn = dynamicDataSource.getConnection();
			if(StringUtils.isNoneBlank(schemVO.getAuditPrjId())) {
	        	String sql = "CREATE SCHEMA SCM_" + schemVO.getAuditPrjId() + ";" + 
	        			"\r\nCREATE TABLESPACE TBS_"+ schemVO.getAuditPrjId() +
	        			"_DATA DATAFILE 'TBS_" + schemVO.getAuditPrjId() +"_DATA.data' SIZE 5m" + ";" ;
	        	Statement statement = conn.createStatement();
	        	statement.execute(sql);
	        	return true;
			}
		} catch (Exception e) {
			log.error("创建项目库并执行相应的 .sql文件失败", e);
		}
		return false;
	}

	/**
	 * 根据项目ID，向仓库中执行相应的 .sql文件
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean creProLibrary(SchemVO schemVO) {
		Connection conn = null;
		try {
	        if(StringUtils.isNoneBlank(schemVO.getAuditPrjId())) {
	        	switchDB.change(schemVO.getAuditPrjId());
	        	conn = dynamicDataSource.getConnection();
	        	//设置不自动提交
	        	conn.setAutoCommit(false);
	        	ScriptRunner runner = new ScriptRunner(conn);
	        	Resources.setCharset(Charset.forName("UTF-8"));
	        	runner.setSendFullScript(false);
	        	runner.setDelimiter("；");
	        	runner.setFullLineDelimiter(false);
	        	
	        	File file = new File("src/main/resources/sql");
	        	File[] files = file.listFiles();
	        	List fileList = Arrays.asList(files);
	        	Collections.sort(fileList);
//	        	
	        	for (Object object : fileList) {
	        		String obj = object.toString().substring(object.toString().lastIndexOf("\\") + 1);
	        		runner.runScript(Resources.getResourceAsReader("sql/"+obj));
				}
	        	
//	        	String[] fileNames = file.list();
//	        	for (String fileName : fileNames) {
//	        		runner.runScript(Resources.getResourceAsReader("sql/" + fileName));
//				}
	        	
//	       		runner.setLogWriter(null);//设置是否输出日志
//	        	runner.runScript(Resources.getResourceAsReader("sql/01_create_cj.sql"));
//	        	runner.runScript(Resources.getResourceAsReader("sql/02_create_cz.sql"));
//	        	runner.runScript(Resources.getResourceAsReader("sql/02_insert_cz.sql"));
//	        	runner.runScript(Resources.getResourceAsReader("sql/03_create_jr.sql"));
//	        	runner.runScript(Resources.getResourceAsReader("sql/04_create_sjfx.sql"));
	        	conn.commit();
	        	runner.closeConnection();
	        }
	        return true;
	        
		} catch (Exception e) {
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
				switchDB.change("");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 审计方法数量统计
	 * @return
	 */
	public List<MethodStatisticalVO> getMethodStatisticalCount(MethodStatisticalVO methodStatisticalVO) {
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
			return list;
		} catch (Exception e) {
			log.error("审计方法数量统计失败", e);
		}
		return null;
	}

}
