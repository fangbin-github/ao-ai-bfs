package gov.cnao.ao.ai.bfs.vo;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DictInfoVO extends BaseRequest {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 字典项名称
     */
    private String dictNm;

    /**
     * 上级字典项代码
     */
    private String supDictCd;

    /**
     * 排序号
     */
    private Integer sortNo;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建人名称
     */
    private String createUserNm;

    /**
     * 创建时间
     */
    private Date createTms;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新人名称
     */
    private String updateUserNm;

    /**
     * 更新时间
     */
    private Date updateTm;

    /**
     * 数据字典类型代码
     */
    private String dictTypeId;

    /**
     * 字典项代码
     */
    private String dictCd;
    
    /**
     * 实体外属性
     */
    private List<InfoVO> dictInfoVOs;
	public List<InfoVO> getDictInfoVOs() {
		return dictInfoVOs;
	}
	public void setDictInfoVOs(List<InfoVO> dictInfoVOs) {
		this.dictInfoVOs = dictInfoVOs;
	}

	public DictInfoVO() {
		super();
    }
    
    public DictInfoVO(String json) throws JsonParseException, JsonMappingException, IOException {
    	DictInfoVO dictInfoVO = new ObjectMapper().readValue(json, DictInfoVO.class);
		this.dictNm = dictInfoVO.dictNm;
		this.supDictCd = dictInfoVO.supDictCd;
		this.sortNo = dictInfoVO.sortNo;
		this.createUser = dictInfoVO.createUser;
		this.createUserNm = dictInfoVO.createUserNm;
		this.createTms = dictInfoVO.createTms;
		this.updateUser = dictInfoVO.updateUser;
		this.updateUserNm = dictInfoVO.updateUserNm;
		this.updateTm = dictInfoVO.updateTm;
		this.dictTypeId = dictInfoVO.dictTypeId;
		this.dictCd = dictInfoVO.dictCd;
		this.dictInfoVOs = dictInfoVO.dictInfoVOs;
	}



	public String getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getDictCd() {
        return dictCd;
    }

    public void setDictCd(String dictCd) {
        this.dictCd = dictCd;
    }
    

    public String getDictNm() {
        return dictNm;
    }

    public void setDictNm(String dictNm) {
        this.dictNm = dictNm;
    }

    public String getSupDictCd() {
        return supDictCd;
    }

    public void setSupDictCd(String supDictCd) {
        this.supDictCd = supDictCd;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getCreateUserNm() {
        return createUserNm;
    }

    public void setCreateUserNm(String createUserNm) {
        this.createUserNm = createUserNm;
    }
    
    public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTms() {
		return createTms;
	}

	public void setCreateTms(Date createTms) {
		this.createTms = createTms;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateUserNm() {
        return updateUserNm;
    }

    public void setUpdateUserNm(String updateUserNm) {
        this.updateUserNm = updateUserNm;
    }

    public Date getUpdateTm() {
        return updateTm;
    }

    public void setUpdateTm(Date updateTm) {
        this.updateTm = updateTm;
    }
}
