package gov.cnao.ao.ai.bfs.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedisTemplateService {
	
	private static org.slf4j.Logger log = LoggerFactory.getLogger(RedisTemplateService.class);
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;

	/**
	 * redis操作String
	 * redis新增String类型的数据
	 * @param key
	 * @param value
	 */
	public Boolean insertStringRedisTemplate(String key, String value) {
		try {
			stringRedisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			log.error("redis新增String类型的数据失败", e);
		}
		return false;
	}

	/**
	 * redis操作String
	 * redis通过键获取String类型的数据
	 * @param key
	 */
	public Boolean getStringRedisTemplate(String key) {
		try {
			stringRedisTemplate.opsForValue().get(key);
			return true;
		} catch (Exception e) {
			log.error("redis通过键获取String类型的数据失败", e);
		}
		return false;
	}

	/**
	 * redis操作String
	 * redis通过键删除String类型的数据
	 * @param key
	 */
	public Boolean deleteStringRedisTemplate(String key) {
		try {
			stringRedisTemplate.delete(key);
			return true;
		} catch (Exception e) {
			log.error("redis通过键删除String类型的数据失败", e);
		}
		return false;
	}
	
	/**
	 * 批量删除key
	 * @param keys
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
    public Boolean delete(Collection<String> keys) {
    	try {
    		stringRedisTemplate.delete(keys);
    		return true;
		} catch (Exception e) {
			log.error("批量删除key失败", e);
		}
    	return false;
    }

	/**
	 * redis操作list
	 * 将数据添加到key对应的现有数据的左边
	 * @param key
	 * @param value
	 */
	public Boolean insertListRedisTemplateLeftPush(String key, String value) {
		try {
			stringRedisTemplate.opsForList().leftPush(key, value);
			return true;
		} catch (Exception e) {
			log.error("redis操作list,将数据添加到key对应的现有数据的左边失败", e);
		}
		return false;
	}

	/**
	 * redis操作list
	 * 将数据添加到key对应的现有数据的右边
	 * @param key
	 * @param value
	 */
	public Boolean insertListRedisTemplateRightPush(String key, String value) {
		try {
			stringRedisTemplate.opsForList().leftPush(key, value);
			return true;
		} catch (Exception e) {
			log.error("redis操作list,将数据添加到key对应的现有数据的右边失败", e);
		}
		return false;
	}

	/**
	 * redis操作list
	 * 通过key从左往右遍历数据
	 * @param key
	 * @param value
	 */
	public Boolean getListRedisTemplateLeftPop(String key) {
		try {
			stringRedisTemplate.opsForList().leftPop(key);
			return true;
		} catch (Exception e) {
			log.error("redis操作list,通过key从左往右遍历数据失败", e);
		}
		return false;
	}

	/**
	 * redis操作list
	 * 通过key从右往左遍历数据
	 * @param key
	 */
	public Boolean getListRedisTemplateRightPop(String key) {
		try {
			stringRedisTemplate.opsForList().rightPop(key);
			return true;
		} catch (Exception e) {
			log.error("redis操作list,通过key从右往左遍历数据失败", e);
		}
		return false;
	}
	
	/**
	 * 批量获取
	 * @param keys
	 * @return
	 */
    public List<String> multiGet(Collection<String> keys) {
        try {
        	return stringRedisTemplate.opsForValue().multiGet(keys);
		} catch (Exception e) {
			log.error("批量获取失败", e);
		}
        return null;
    }
    
    /**
     * 批量添加
     * @param maps
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean multiSet(Map<String, String> maps) {
    	try {
    		stringRedisTemplate.opsForValue().multiSet(maps);
    		return true;
		} catch (Exception e) {
			log.error("批量添加失败", e);
		}
    	return false;
    }

    /**
	 * 通过key查询全部元素
	 * @param key
	 * @return
	 */
	public List<String> rangeListRedisTemplate(String key) {
		try {
			return stringRedisTemplate.opsForList().range(key, 0, -1);
		} catch (Exception e) {
			log.error("通过key查询全部元素失败", e);
		}
		return null;
	}

	/**
     * 获取key对应的map中key1的值
     * @param key map的键
     * @param key1 map中值的键
     * @return
     */
	public String getRedisForHash(String key, String key1) {
		try {
			return (String) stringRedisTemplate.opsForHash().get(key, key1);
		} catch (Exception e) {
			log.error("通过key查询全部元素失败", e);
		}
		return null;
	}
	
}
