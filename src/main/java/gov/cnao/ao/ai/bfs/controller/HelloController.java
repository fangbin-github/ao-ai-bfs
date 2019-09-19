package gov.cnao.ao.ai.bfs.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import gov.cnao.ao.ai.bfs.contract.Hello;

@RestSchema(schemaId="hello")
@RequestMapping(path = "/hello")
public class HelloController implements Hello {
    private static org.slf4j.Logger log = LoggerFactory.getLogger(HelloController.class);
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) {
		if(file.isEmpty()) {
			return "上传失败！！！";
		}
		String fileName = file.getOriginalFilename();
		String filePath = "H:/AO";
		File dest = new File(filePath + fileName);
		try {
			file.transferTo(dest);
			return "上传成功！！！";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "上传失败！！！";
	}

    @Override
    @RequestMapping(path = "/sayhi", method = RequestMethod.GET)
    public String sayHi(@RequestParam(name = "name", required = false) String name) {
        log.info("Access /hello/sayhi, and name is " + name);
        stringRedisTemplate.opsForValue().set("name", name);
        System.out.println(stringRedisTemplate.opsForValue().get("name"));
        return "from provider: Hello " + name;
    }
    
}
