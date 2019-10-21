package gov.cnao.ao.ai.bfs;

import org.apache.servicecomb.springboot.starter.provider.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude=DispatcherServletAutoConfiguration.class)
@EnableServiceComb
@ComponentScan({"gov.cnao.ao.ai.bfs", "com.bjsasc.drap","gov.cnao.security"})
public class ServerMain {
	
    public static void main(String[] args) {
        SpringApplication.run(ServerMain.class, args);
    }
    
}
