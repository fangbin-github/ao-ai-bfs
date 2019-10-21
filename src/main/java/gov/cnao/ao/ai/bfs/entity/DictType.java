package gov.cnao.ao.ai.bfs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DictType implements Serializable {
	
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
     * 数据字典字典项
     */
//    private List<DictInfo> dictInfos;
//
//    public List<DictInfo> getDictInfos() {
//		return dictInfos;
//	}
//
//	public void setDictInfos(List<DictInfo> dictInfos) {
//		this.dictInfos = dictInfos;
//	}

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