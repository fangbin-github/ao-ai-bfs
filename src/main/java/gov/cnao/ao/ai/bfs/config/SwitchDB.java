package gov.cnao.ao.ai.bfs.config;

import com.alibaba.druid.pool.DruidDataSource;

import gov.cnao.security.service.EncryptDecryptService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
//import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 切换数据库类
 */
@Configuration
public class SwitchDB {
    @Autowired
    private Environment evn;
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    //私有库数据源key
   // private static String  AoAiSourceKey = "aoai_" ;
    private static String  AoAiSourceKey = "SCM_" ;

    @Autowired
    private EncryptDecryptService encryptDecryptService;
    
    @Autowired
    DynamicDataSource dynamicDataSource;

//    @Autowired
//    private PlatformTransactionManager transactionManager;

    /**
     * 切换数据库对外方法,如果私有库newDbName参数非空,则首先连接私有库，否则连接其他已存在的数据源
     * @param dbName 已存在的数据库源对象
     * @param newDbName 私有库主键
     * @return 返回当前数据库连接对象对应的key
     */
    public String change(String dbSchema,String NewdbSchema)
    {
        if( "".equals(NewdbSchema) || NewdbSchema == null){
            toDB(dbSchema);
        }else {
            toPriDB(NewdbSchema);
        }
        //获取当前连接的数据源对象的key
        String currentKey = DynamicDataSourceContextHolder.getDataSourceKey();
        log.info("＝＝＝＝＝当前连接的数据库是:" + currentKey);
        return currentKey;
    }
    
    public String change(String project){
        if( "".equals(project) || project == null){
            toDB("default");
        }
        toDB(project);
        //获取当前连接的数据源对象的key
        String currentKey = DynamicDataSourceContextHolder.getDataSourceKey();
        log.info("＝＝＝＝＝当前连接的数据库是:" + currentKey);
        return currentKey;
    }
    
    public String change(){
        toDB("default");
        //获取当前连接的数据源对象的key
        String currentKey = DynamicDataSourceContextHolder.getDataSourceKey();
        log.info("＝＝＝＝＝当前连接的数据库是:" + currentKey);
        return currentKey;
    }
    
    /**
     * 切换已存在的数据源
     * @param dbName
     */
    private void toDB(String projectId) {
    	if(null == projectId) {
    		projectId ="";
    	}
        //如果不指定数据库，则直接连接默认数据库
        String dbSourceKey = projectId.trim().isEmpty() ? "default" : AoAiSourceKey+projectId.trim();
        if("default".equalsIgnoreCase(projectId)) {
        	dbSourceKey ="default";
        }
        //获取当前连接的数据源对象的key
        String currentKey = DynamicDataSourceContextHolder.getDataSourceKey();
        //如果当前数据库连接已经是想要的连接，则直接返回
        if(currentKey.equals(dbSourceKey) ) {
        	return;
        }
        //判断储存动态数据源实例的map中key值是否存在
        if( DynamicDataSource.isExistDataSource(dbSourceKey) ){
        	if("default".equalsIgnoreCase(projectId)) {
        		dynamicDataSource.setSchemaName(evn.getProperty("spring.datasource.default"
        		+".schemaName"));
        	}else {
        		dynamicDataSource.setSchemaName(AoAiSourceKey+projectId);
        	}
        	
            DynamicDataSourceContextHolder.setDataSourceKey(dbSourceKey);
            log.info("＝＝＝＝＝普通模式: SCM_"+projectId+",切换完毕");
        }else {
            toPriDB(projectId);
        }
    }

    /**
     * 创建新的私有库数据源
     * @param ljyunId
     */
    private void  toPriDB(String projectId){
    	 if(DynamicDataSource.isExistDataSource(projectId)) {
    		 DynamicDataSourceContextHolder.setDataSourceKey(projectId);
    		 dynamicDataSource.setSchemaName(projectId);
             log.info("＝＝＝＝＝普通模式: "+projectId+",切换完毕");
             return;
    	 }
        //组合私有库数据源对象key
        String dbSourceKey = AoAiSourceKey+projectId;
        //获取当前连接的数据源对象的key
        String currentKey = DynamicDataSourceContextHolder.getDataSourceKey();
        if(dbSourceKey == currentKey) return;

        //创建私有库数据源
        createPrivateDataSource(projectId);

        //切换到当前数据源
        DynamicDataSourceContextHolder.setDataSourceKey(dbSourceKey);
        log.info("＝＝＝＝＝私有模式: SCM_"+projectId+",切换完毕");
    }

    /**
     * 创建私有库数据源，并将数据源赋值到targetDataSources中，供后切库用
     * @param ljyunId
     * @return
     */
    private DruidDataSource createPrivateDataSource(String projectId){
        //创建新的数据源
        if("".equals(projectId) || projectId == null ) {
            log.info("动态创建私有库数据时，私有库主键丢失");
        }
        DruidDataSource dataSource = new DruidDataSource();
        String prefix = "spring.datasource.private.";
        String dbUrl = evn.getProperty( prefix + "url-base")
                + evn.getProperty( prefix + "host") + ":"
                + evn.getProperty( prefix + "port") + "/"
                + evn.getProperty( prefix + "dbname") + evn.getProperty( prefix + "url-other");
        log.info("+++创建云平台私有库连接url = " + dbUrl);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(evn.getProperty( prefix + "username"));
        try {
			dataSource.setPassword(encryptDecryptService.decrypt(evn.getProperty( prefix + "password")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
			log.error(e.getMessage());
		}
        dataSource.setDriverClassName(evn.getProperty( prefix + "driver-class-name"));
        //将创建的数据源，新增到targetDataSources中
        Map<Object,Object> map = new HashMap<>();
        map.put(AoAiSourceKey+projectId, dataSource);
      //获取动态数据库的实例（单例方式）
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        dynamicDataSource.setSchemaName(AoAiSourceKey+projectId);
        dynamicDataSource.setTargetDataSources(map);
        return dataSource;
    }
}
