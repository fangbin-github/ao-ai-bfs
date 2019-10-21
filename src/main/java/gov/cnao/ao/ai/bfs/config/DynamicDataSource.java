package gov.cnao.ao.ai.bfs.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据设置以及获取，本类属于单例
 * @author lxf 2018-09-29
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    //单例句柄
    private static DynamicDataSource instance;
   // private String schemaName;
    private static byte[] lock=new byte[0];
    //用于存储已实例的数据源map
    private static Map<Object,Object> dataSourceMap=new HashMap<Object, Object>();
    
    private String schemaName;

    public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	/**
     * 获取当前数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("Current DataSource is [{}]", DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

    /**
     * 设置数据源
     * @param targetDataSources
     */
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();// 必须添加该句，否则新添加数据源无法识别到
    }

    /**
     * 获取存储已实例的数据源map
     * @return
     */
    public Map<Object, Object> getDataSourceMap() {
        return dataSourceMap;
    }

    /**
     * 单例方法
     * @return
     */
    public static synchronized DynamicDataSource getInstance(){
        if(instance==null){
            synchronized (lock){
                if(instance==null){
                    instance=new DynamicDataSource();
                }
            }
        }
        return instance;
    }

    /**
     * 是否存在当前key的 DataSource
     * @param key
     * @return 存在返回 true, 不存在返回 false
     */
    public static boolean isExistDataSource(String key) {
        return dataSourceMap.containsKey(key);
    }

    /**
     * 重写getConnection,连接后切换schema
     */
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn = super.getConnection();
        if (!"".equals(this.schemaName.trim())) {
                Statement stamt = conn.createStatement();
                stamt.execute("set search_path to " + 
                                schemaName.trim());
                logger.info("schema已切换:"+this.schemaName);
            }
        return conn;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		Connection conn = super.getConnection(username, password);
        if (!"".equals(this.schemaName.trim())) {
                Statement stamt = conn.createStatement();
                stamt.execute("set search_path to " + 
                                schemaName.trim());
                logger.info("schema已切换:"+this.schemaName);
            }
        return conn;
	}
    
	public Connection getConnection(String shcemaName) throws SQLException{
		Connection conn = super.getConnection();
        if (!"".equals(shcemaName.trim())) {
                Statement stamt = conn.createStatement();
                stamt.execute("set search_path to " + 
                		"SCM_"+shcemaName.trim());
                logger.info("schema已切换:SCM_"+shcemaName);
            }
        return conn;
	}
    
}