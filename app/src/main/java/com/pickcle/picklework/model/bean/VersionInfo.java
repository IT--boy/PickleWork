package com.pickcle.picklework.model.bean;

public class VersionInfo {
    //app是否升级
    private Integer updateFlag = 0;
    //app是否强制更新
    private Integer forceUpate;
    private String versionName;
    private Integer versionNo;
    //app更新地址
    private String downUrl;
    //更新内容
    private String remark;

    public Integer getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    public Integer getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Integer updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Integer getForceUpate() {
        return forceUpate;
    }

    public void setForceUpate(Integer forceUpate) {
        this.forceUpate = forceUpate;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
