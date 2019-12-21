package gov.cnao.ao.ai.bfs.service;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Service
public class RedisTemplateService {
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private Environment env;

	/**
	 * redis操作String
	 * redis新增String类型的数据
	 * @param key
	 * @param value
	 */
	public void insertStringRedisTemplate(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	/**
	 * redis操作String
	 * redis通过键获取String类型的数据
	 * @param key
	 */
	public void getStringRedisTemplate(String key) {
		stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * redis操作String
	 * redis通过键删除String类型的数据
	 * @param key
	 */
	public void deleteStringRedisTemplate(String key) {
		stringRedisTemplate.delete(key);
	}
	
	/**
	 * 批量删除key
	 * @param keys
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
    public void delete(Collection<String> keys) {
		stringRedisTemplate.delete(keys);
    }

	/**
	 * redis操作list
	 * 将数据添加到key对应的现有数据的左边
	 * @param key
	 * @param value
	 */
	public void insertListRedisTemplateLeftPush(String key, String value) {
		stringRedisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * redis操作list
	 * 将数据添加到key对应的现有数据的右边
	 * @param key
	 * @param value
	 */
	public void insertListRedisTemplateRightPush(String key, String value) {
		stringRedisTemplate.opsForList().leftPush(key, value);
	}

	/**
	 * redis操作list
	 * 通过key从左往右遍历数据
	 * @param key
	 * @param value
	 */
	public void getListRedisTemplateLeftPop(String key) {
		stringRedisTemplate.opsForList().leftPop(key);
	}

	/**
	 * redis操作list
	 * 通过key从右往左遍历数据
	 * @param key
	 */
	public void getListRedisTemplateRightPop(String key) {
		stringRedisTemplate.opsForList().rightPop(key);
	}
	
	/**
	 * 批量获取
	 * @param keys
	 * @return
	 */
    public List<String> multiGet(Collection<String> keys) {
    	return stringRedisTemplate.opsForValue().multiGet(keys);
    }
    
    /**
     * 批量添加
     * @param maps
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void multiSet(Map<String, String> maps) {
		stringRedisTemplate.opsForValue().multiSet(maps);
    }

    /**
	 * 通过key查询全部元素
	 * @param key
	 * @return
	 */
	public List<String> rangeListRedisTemplate(String key) {
		return stringRedisTemplate.opsForList().range(key, 0, -1);
	}

	/**
     * 获取key对应的map中key1的值
     * @param key map的键
     * @param key1 map中值的键
     * @return
	 * @throws IOException 
     */
	public String getRedisForHash(String key, String key1) throws IOException {
		Set<HostAndPort> hosts = getHosts();
		final JedisCluster client = new JedisCluster(hosts, 15000);
		String auditPrjId = client.hget(key, key1);
		client.close();
		return auditPrjId;
	}

	/**
	 * 获取华为ridis连接
	 * @return
	 */
	public Set<HostAndPort> getHosts() {
		String prefix = "spring.redis.cluster.";
        String redisIPPort = env.getProperty( prefix + "nodes");
        Set<HostAndPort> hosts = new HashSet<HostAndPort>();
        for (String hostAndPort : redisIPPort.split(",")) {
            hosts.add(new HostAndPort(hostAndPort.split(":")[0], Integer.parseInt(hostAndPort.split(":")[1])));
        }
		return hosts;
	}
	
}
