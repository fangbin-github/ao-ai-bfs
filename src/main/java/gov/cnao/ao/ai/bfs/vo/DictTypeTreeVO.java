package gov.cnao.ao.ai.bfs.vo;

import java.io.Serializable;
import java.util.List;

import gov.cnao.ao.ai.bfs.entity.DictType;

public class DictTypeTreeVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String dictTypeNm;
	private List<DictType> dictTypes;
	
	public String getDictTypeNm() {
		return dictTypeNm;
	}
	public void setDictTypeNm(String dictTypeNm) {
		this.dictTypeNm = dictTypeNm;
	}
	public List<DictType> getDictTypes() {
		return dictTypes;
	}
	public void setDictTypes(List<DictType> dictTypes) {
		this.dictTypes = dictTypes;
	}
	
}
