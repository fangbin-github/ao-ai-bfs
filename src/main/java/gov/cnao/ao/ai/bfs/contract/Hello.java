package gov.cnao.ao.ai.bfs.contract;

public interface Hello {
	
	/**
	 * 通过ID查询T_DMS_AUDI_METH_COL表columnDes信息
	 * @param id
	 * @return
	 */
	String queryAudiMethCol(String id, String auditPrjId);
	
	
    String sayHi(String name);
    
}
