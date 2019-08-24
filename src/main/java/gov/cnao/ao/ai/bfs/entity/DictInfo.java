package gov.cnao.ao.ai.bfs.entity;

import java.io.Serializable;
import java.util.Date;

public class DictInfo implements Serializable {
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

    /**
     * 数据字典类型代码
     */
    private String dictTypeId;

    /**
     * 字典项代码
     */
    private String dictCd;

    private static final long serialVersionUID = 1L;

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