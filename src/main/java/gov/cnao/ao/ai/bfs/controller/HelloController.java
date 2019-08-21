package gov.cnao.ao.ai.bfs.controller;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestSchema(schemaId="hello")
@RequestMapping(path = "/hello")
public class HelloController implements Hello {
    private static org.slf4j.Logger log = LoggerFactory.getLogger(HelloController.class);

    @Override
    @RequestMapping(path = "/sayhi", method = RequestMethod.GET)
    public String sayHi(@RequestParam(name = "name", required = false) String name) {
        log.info("Access /hello/sayhi, and name is " + name);
        return "from provider: Hello " + name;
    }
}
