package gov.cnao.ao.ai.bfs.entity;

import java.io.Serializable;
import java.util.Date;

public class DictType implements Serializable {
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
    private String createUserId;

    /**
     * 创建人名称
     */
    private String createUserNm;

    /**
     * 创建时间
     */
    private Date createTm;

    /**
     * 更新人
     */
    private String updateUserId;

    /**
     * 更新人名称
     */
    private String updateUserNm;

    /**
     * 更新时间
     */
    private Date updateTm;

    private static final long serialVersionUID = 1L;

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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserNm() {
        return createUserNm;
    }

    public void setCreateUserNm(String createUserNm) {
        this.createUserNm = createUserNm;
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
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