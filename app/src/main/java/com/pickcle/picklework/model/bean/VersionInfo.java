package com.pickcle.picklework.model.bean;

public class VersionInfo {
    //app是否升级
    private Integer appUpdateFlag = 0;
    //app是否强制更新
    private Integer appForceUpate;
    private String appVersionName;
    private Integer appVersionCode;
    //js是否升级
    private Integer jsUpdateFlag = 0;
    //js是否强制更新
    private Integer jsForceUpate;
    private String jsVersionName;
    private Integer jsVersionCode;
    //app更新地址
    private String appUrl;
    //js更新地址
    private String jsUrl;
    //更新内容
    private String remark;

    public Integer getAppUpdateFlag() {
        return appUpdateFlag;
    }

    public void setAppUpdateFlag(Integer appUpdateFlag) {
        this.appUpdateFlag = appUpdateFlag;
    }

    public Integer getAppForceUpate() {
        return appForceUpate;
    }

    public void setAppForceUpate(Integer appForceUpate) {
        this.appForceUpate = appForceUpate;
    }

    public Integer getJsUpdateFlag() {
        return jsUpdateFlag;
    }

    public void setJsUpdateFlag(Integer jsUpdateFlag) {
        this.jsUpdateFlag = jsUpdateFlag;
    }

    public Integer getJsForceUpate() {
        return jsForceUpate;
    }

    public void setJsForceUpate(Integer jsForceUpate) {
        this.jsForceUpate = jsForceUpate;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getJsUrl() {
        return jsUrl;
    }

    public void setJsUrl(String jsUrl) {
        this.jsUrl = jsUrl;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getJsVersionName() {
        return jsVersionName;
    }

    public void setJsVersionName(String jsVersionName) {
        this.jsVersionName = jsVersionName;
    }

    public Integer getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(Integer appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public Integer getJsVersionCode() {
        return jsVersionCode;
    }

    public void setJsVersionCode(Integer jsVersionCode) {
        this.jsVersionCode = jsVersionCode;
    }
}
