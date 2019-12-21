package gov.cnao.ao.ai.bfs.contract;

import java.io.IOException;

public interface Hello {
	
	/**
	 * 通过ID查询T_DMS_AUDI_METH_COL表columnDes信息
	 * @param id
	 * @return
	 */
	String queryAudiMethCol(String id, String auditPrjId);
	
	/**
	 * sayhi
	 * @param name
	 * @return
	 * @throws IOException
	 */
    String sayHi(String name) throws IOException;
    
}
