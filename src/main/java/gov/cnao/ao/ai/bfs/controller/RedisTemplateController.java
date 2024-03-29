package gov.cnao.ao.ai.bfs.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gov.cnao.ao.ai.bfs.contract.IRedisTemplate;
import gov.cnao.ao.ai.bfs.service.RedisTemplateService;
import gov.cnao.ao.ai.bfs.vo.RedisVO;
import redis.clients.jedis.HostAndPort;

@RestSchema(schemaId="iRedisTemplate")
@RequestMapping(path="/redisTemplate")
public class RedisTemplateController implements IRedisTemplate {
	
	private final static org.slf4j.Logger log = LoggerFactory.getLogger(RedisTemplateController.class);
    
	@Autowired
	private RedisTemplateService redisTemplateService;
	
	public Set<HostAndPort> getHosts() {
        return redisTemplateService.getHosts();
	}

	/**
	 * redis操作String
	 * redis新增String类型的数据
	 * @param key
	 * @param value
	 */
	@Override
	@RequestMapping(path = "/insertStringRedisTemplate", method = RequestMethod.POST)
	public void insertStringRedisTemplate(@RequestParam(defaultValue = "key") String key, @RequestParam(defaultValue = "value")  String value) {
		log.info("Access /IRedisTemplate/insertStringRedisTemplate -- redis新增String类型的数据");
		redisTemplateService.insertStringRedisTemplate(key, value);
	}

	/**
	 * redis操作String
	 * redis通过键获取String类型的数据
	 * @param key
	 */
	@Override
	@RequestMapping(path = "/getStringRedisTemplate", method = RequestMethod.POST)
	public void getStringRedisTemplate(@RequestParam(defaultValue = "key")  String key) {
		log.info("Access /IRedisTemplate/getStringRedisTemplate -- redis通过键获取String类型的数据");
		redisTemplateService.getStringRedisTemplate(key);
	}

	/**
	 * redis操作String
	 * redis通过键删除String类型的数据
	 * @param key
	 */
	@Override
	@RequestMapping(path = "/deleteStringRedisTemplate", method = RequestMethod.POST)
	public void deleteStringRedisTemplate(@RequestParam(defaultValue = "key") String key) {
		log.info("Access /IRedisTemplate/getStringRedisTemplate -- redis通过键删除String类型的数据");
		redisTemplateService.deleteStringRedisTemplate(key);
	}
	
	/**
	 * 批量删除key
	 * @param keys
	 * @return
	 */
	@RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Collection<String> keys) {
    	log.info("Access /IRedisTemplate/delete -- 批量删除key");
		redisTemplateService.delete(keys);
    }

	/**
	 * redis操作list
	 * 将数据添加到key对应的现有数据的左边
	 * @param key
	 * @param value
	 */
	@Override
	@RequestMapping(path = "/insertListRedisTemplateLeftPush", method = RequestMethod.POST)
	public void insertListRedisTemplateLeftPush(@RequestParam(defaultValue = "key") String key, @RequestParam(defaultValue = "value") String value) {
		log.info("Access /IRedisTemplate/insertListRedisTemplateLeftPush -- redis操作list,将数据添加到key对应的现有数据的左边");
		redisTemplateService.insertListRedisTemplateLeftPush(key, value);
	}

	/**
	 * redis操作list
	 * 将数据添加到key对应的现有数据的右边
	 * @param key
	 * @param value
	 */
	@Override
	@RequestMapping(path = "/insertListRedisTemplateRightPush", method = RequestMethod.POST)
	public void insertListRedisTemplateRightPush(@RequestParam(defaultValue = "key") String key, @RequestParam(defaultValue = "value") String value) {
		log.info("Access /IRedisTemplate/insertListRedisTemplateRightPush -- redis操作list,将数据添加到key对应的现有数据的左边");
		redisTemplateService.insertListRedisTemplateRightPush(key, value);
	}

	/**
	 * redis操作list
	 * 通过key从左往右遍历数据
	 * @param key
	 */
	@Override
	@RequestMapping(path = "/getListRedisTemplateLeftPop", method = RequestMethod.POST)
	public void getListRedisTemplateLeftPop(@RequestParam(defaultValue = "key") String key) {
		log.info("Access /IRedisTemplate/getListRedisTemplateLeftPop -- redis操作list,通过key从左往右遍历数据");
		redisTemplateService.getListRedisTemplateLeftPop(key);
	}

	/**
	 * redis操作list
	 * 通过key从右往左遍历数据
	 * @param key
	 */
	@Override
	@RequestMapping(path = "/getListRedisTemplateRightPop", method = RequestMethod.POST)
	public void getListRedisTemplateRightPop(@RequestParam(defaultValue = "key") String key) {
		log.info("Access /IRedisTemplate/getListRedisTemplateRightPop -- redis操作list,通过key从右往左遍历数据");
		redisTemplateService.getListRedisTemplateRightPop(key);
	}
	
	/**
	 * 批量获取
	 * @param keys
	 * @return
	 */
	@RequestMapping(path = "/multiGet", method = RequestMethod.POST)
	public List<String> multiGet(@RequestBody Collection<String> keys) {
		log.info("Access /IRedisTemplate/multiGet -- 批量获取");
		return redisTemplateService.multiGet(keys);
	}
	
	/**
     * 批量添加
     * @param maps
     * @return
     */
	@RequestMapping(path = "/multiSet", method = RequestMethod.POST)
    public void multiSet(@RequestBody Map<String, String> maps) {
    	log.info("Access /IRedisTemplate/multiSet -- 批量添加");
    	redisTemplateService.multiSet(maps);
    }

	/**
	 * 通过key查询全部元素
	 * @param key
	 * @return
	 */
	@Override
	@RequestMapping(path = "/rangeListRedisTemplate", method = RequestMethod.POST)
	public List<String> rangeListRedisTemplate(@RequestParam(defaultValue = "key") String key) {
		log.info("Access /IRedisTemplate/rangeListRedisTemplate -- 通过key查询全部元素");
		return redisTemplateService.rangeListRedisTemplate(key);
	}

	/**
     * 获取key对应的map中key1的值
     * @param key map的键
     * @param key1 map中值的键
     * @return
	 * @throws IOException 
     */
	@Override
	@RequestMapping(path = "/getRedisForHash", method = RequestMethod.POST)
	public String getRedisForHash(@RequestBody RedisVO redisVO) throws IOException {
		log.info("Access /IRedisTemplate/getRedisForHash -- 获取key对应的map中key1的值");
		return redisTemplateService.getRedisForHash(redisVO.getUsno(), redisVO.getAuditPrjId());
	}
    
}
