package gov.cnao.ao.ai.bfs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.cnao.ao.ai.bfs.config.SwitchDB;
import gov.cnao.ao.ai.bfs.mapper.HelloMapper;

@Service
public class HelloService {
	
	@Autowired
	private HelloMapper helloMapper;
	
	@Autowired
    private SwitchDB switchDB;
	
	/**
	 * 通过ID查询T_DMS_AUDI_METH_COL表columnDes信息
	 * @param id
	 * @return
	 */
	public String queryAudiMethCol(String id, String auditPrjId) {
		switchDB.change(auditPrjId);
		String audiMethCol = helloMapper.queryAudiMethCol(id);
		switchDB.change("");
		return audiMethCol;
	}
	
}
