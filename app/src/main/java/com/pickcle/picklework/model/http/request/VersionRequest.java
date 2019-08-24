package com.pickcle.picklework.model.http.request;

/**
 * 获取版本请求实体
 */
public class VersionRequest extends BaseRequest{
    /**
     * app版本号
     */
    private Integer appVersion;
    /**
     * js版本号
     */
    private Integer jsVersion;

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getJsVersion() {
        return jsVersion;
    }

    public void setJsVersion(Integer jsVersion) {
        this.jsVersion = jsVersion;
    }
}
