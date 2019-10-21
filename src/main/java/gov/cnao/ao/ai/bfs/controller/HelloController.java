package gov.cnao.ao.ai.bfs.controller;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gov.cnao.ao.ai.bfs.contract.Hello;
import gov.cnao.ao.ai.bfs.service.HelloService;

@RestSchema(schemaId="hello")
@RequestMapping(path = "/hello")
public class HelloController implements Hello {
	
    private static org.slf4j.Logger log = LoggerFactory.getLogger(HelloController.class);
    
    @Autowired
    private HelloService helloService;
    
    @Override
    @RequestMapping(path = "/sayhi", method = RequestMethod.GET)
    public String sayHi(@RequestParam(name = "name", required = false) String name) {
        log.info("Access /hello/sayhi, and name is " + name);
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
