package gov.cnao.ao.ai.bfs.service;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bjsasc.drap.pt.context.ThreadLocalUtil;

import gov.cnao.ao.ai.bfs.contract.Hello;

@Service
public class IndexService {
	
	@RpcReference(microserviceName="", schemaId="prjInf_01")
	Hello client;
	 
	RestTemplate restTemplate = RestTemplateBuilder.create();
	
	public String prjInf_01() {
		String userId = ThreadLocalUtil.getContextUser().getUserID();
		String prjInf_01 = restTemplate.getForObject("cse://helloprovider/hello/sayhi?userId=" + userId, String.class);
		return prjInf_01;
	}

	public String prjInf_02() {
		String prjInf_02 = restTemplate.getForObject("cse://helloprovider/hello/sayhi?userId=" + "", String.class);
		return prjInf_02;
	}

	public String prjInf_03() {
		String prjInf_03 = restTemplate.getForObject("cse://helloprovider/hello/sayhi?userId=" + "", String.class);
		return prjInf_03;
	}

	public String prjInf_04() {
		String prjInf_04 = restTemplate.getForObject("cse://helloprovider/hello/sayhi?userId=" + "", String.class);
		return prjInf_04;
	}

}
