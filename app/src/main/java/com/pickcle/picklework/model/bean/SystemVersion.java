package com.pickcle.picklework.model.bean;

/**
 * Created by admin on 2016/10/9.
 * app 更新返回实体
 */
public class SystemVersion {
    /**
     *是否升级
     */
    private int isUpdate;
    /**
     *是否强制升级
     */
    private int forceUpdate;
    /**
     *版本名称
     */
    private String versionName;
    /**
     * 更新内容
     */
    private String remark;
    /**
     *下载地址
     */
    private String url;

    public int getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(int isUpdate) {
        this.isUpdate = isUpdate;
    }
    public int getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(int forceUpdate) {
        this.forceUpdate = forceUpdate;
    }
    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
