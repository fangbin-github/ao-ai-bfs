package gov.cnao.ao.ai.bfs.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gov.cnao.ao.ai.bfs.contract.Hello;
import gov.cnao.ao.ai.bfs.service.HelloService;
import gov.cnao.ao.ai.bfs.service.RedisTemplateService;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@RestSchema(schemaId="hello")
@RequestMapping(path = "/hello")
public class HelloController implements Hello {
	
    private final static org.slf4j.Logger log = LoggerFactory.getLogger(HelloController.class);
    
    @Autowired
    private HelloService helloService;
    
    @Autowired
    private RedisTemplateService redisTemplateService;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    /**
	 * sayhi
	 * @param name
	 * @return
	 * @throws IOException
	 */
    @Override
    @RequestMapping(path = "/sayhi", method = RequestMethod.GET)
    public String sayHi(@RequestParam(name = "name", required = false) String name) throws IOException {
        log.info("Access /hello/sayhi, and name is " + name);
        Set<HostAndPort> hosts = redisTemplateService.getHosts();
        final JedisCluster client = new JedisCluster(hosts, 15000);
        String key = "message";
        client.set(key, name);
        name = client.get(key);
//        
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("zhangsan", "张三");
//        map.put("list", "李四");
//        client.hmset("map", map);
////        System.out.println("client.hget(\"map\", \"zhangsan\") ==" + client.hget("map", "zhangsan")+ "===================");
//        log.info(client.hget("map", "zhangsan"));
//        
//        List<String> list = client.hvals("map");
//        for (String str : list) {
//        	log.info(str);
//		}
//        
//        List<String> list2 = client.hmget("map", "zhangsan","lisi");
//        for (String lis : list2) {
//			log.info(lis);
//		}
//        name = client.get(name);
        client.close();
        return "from provider: Hello " + name;
    }

    /**
	 * 通过ID查询T_DMS_AUDI_METH_COL表columnDes信息
	 * @param subjCd
	 * @return
	 */
	@Override
	@RequestMapping(path = "/queryAudiMethCol", method = RequestMethod.POST)
	public String queryAudiMethCol(@RequestParam(defaultValue = "id") String id,
					@RequestParam(defaultValue = "auditPrjId") String auditPrjId) {
		return helloService.queryAudiMethCol(id, auditPrjId);
	}
    
}
