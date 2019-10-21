package gov.cnao.ao.ai.bfs.vo;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DictTypeVO extends BaseRequest {

	private static final long serialVersionUID = 1L;

	/**
     * 字典类型代码
     */
    private String dictTypeId;

    /**
     * 字典类型名称
     */
    private String dictTypeNm;

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
     * 实体外属性
     */
    private List<TypeVO> dictTypeVOs;
	public List<TypeVO> getDictTypeVOs() {
		return dictTypeVOs;
	}
	public void setDictTypeVOs(List<TypeVO> dictTypeVOs) {
		this.dictTypeVOs = dictTypeVOs;
	}

	public DictTypeVO() {
    	super();
	}

    public DictTypeVO(String json) throws JsonParseException, JsonMappingException, IOException {
    	DictTypeVO dictTypeVO = new ObjectMapper().readValue(json, DictTypeVO.class);
		this.dictTypeId = dictTypeVO.dictTypeId;
		this.dictTypeNm = dictTypeVO.dictTypeNm;
		this.createUser = dictTypeVO.createUser;
		this.createUserNm = dictTypeVO.createUserNm;
		this.createTms = dictTypeVO.createTms;
		this.updateUser = dictTypeVO.updateUser;
		this.updateUserNm = dictTypeVO.updateUserNm;
		this.updateTm = dictTypeVO.updateTm;
		this.dictTypeVOs = dictTypeVO.dictTypeVOs;
	}





	public String getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getDictTypeNm() {
        return dictTypeNm;
    }

    public void setDictTypeNm(String dictTypeNm) {
        this.dictTypeNm = dictTypeNm;
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
