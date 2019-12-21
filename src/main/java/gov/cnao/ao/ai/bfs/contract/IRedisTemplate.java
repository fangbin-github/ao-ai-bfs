package gov.cnao.ao.ai.bfs.contract;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gov.cnao.ao.ai.bfs.vo.RedisVO;
import redis.clients.jedis.HostAndPort;

public interface IRedisTemplate {
	
	/**
	 * 获取华为redis连接
	 * @return
	 */
	Set<HostAndPort> getHosts();
	
	/**
	 * redis操作String
	 * redis新增String类型的数据
	 * @param key
	 * @param value
	 */
	void insertStringRedisTemplate(String key, String value); 
	
	/**
	 * redis操作String
	 * redis通过键获取String类型的数据
	 * @param key
	 */
	void getStringRedisTemplate(String key);
	
	/**
	 * redis操作String
	 * redis通过键删除String类型的数据
	 * @param key
	 */
	void deleteStringRedisTemplate(String key);
	
	/**
	 * 批量删除key
	 * @param keys
	 * @return
	 */
	void delete(Collection<String> keys);
	
	/**
	 * redis操作list
	 * 将数据添加到key对应的现有数据的左边
	 * @param key
	 * @param value
	 */
	void insertListRedisTemplateLeftPush(String key, String value);
	
	/**
	 * redis操作list
	 * 将数据添加到key对应的现有数据的右边
	 * @param key
	 * @param value
	 */
	void insertListRedisTemplateRightPush(String key, String value);
	
	/**
	 * redis操作list
	 * 通过key从左往右遍历数据
	 * @param key
	 */
	void getListRedisTemplateLeftPop(String key);
	
	/**
	 * redis操作list
	 * 通过key从右往左遍历数据
	 * @param key
	 */
	void getListRedisTemplateRightPop(String key);
	
	/**
	 * 通过key查询全部元素
	 * @param key
	 * @return
	 */
	List<String> rangeListRedisTemplate(String key);
	
	/**
	 * 批量获取
	 * @param keys
	 * @return
	 */
	List<String> multiGet(Collection<String> keys);
	
	/**
     * 批量添加
     * @param maps
     * @return
     */
	void multiSet(Map<String, String> maps);
    
    /**
     * 获取key对应的map中key1的值
     * @param key map的键
     * @param key1 map中值的键
     * @return
     * @throws IOException 
     */
    String getRedisForHash(RedisVO redisVO) throws IOException;

	
}
