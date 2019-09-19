package gov.cnao.ao.ai.bfs.controller;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import gov.cnao.ao.ai.bfs.contract.IIndex;
import gov.cnao.ao.ai.bfs.service.IndexService;

@RestSchema(schemaId="iIndex")
@RequestMapping(path = "/index")
public class IndexController implements IIndex {
	
	@Autowired
	private IndexService indexService;
	
	
	@RequestMapping(path = "/prjInf_01", method = RequestMethod.POST)
	public String prjInf_01() {
		return indexService.prjInf_01(); 
	}

	@RequestMapping(path = "/prjInf_02", method = RequestMethod.POST)
	public String prjInf_02() {
		return indexService.prjInf_02();
	}

	@RequestMapping(path = "/prjInf_03", method = RequestMethod.POST)
	public String prjInf_03() {
		return indexService.prjInf_03();
	}

	@RequestMapping(path = "/prjInf_04", method = RequestMethod.POST)
	public String prjInf_04() {
		return indexService.prjInf_04();
	}

}
