package com.pickcle.picklework.model.http.request;

/**
 * Created by admin on 2016/10/9.
 * 检查更新版本请求实体
 */
public class SystemVersionRequest {
    /**
     * 验证的类型
     */
    private Integer type;
    /**
     * 包名
     */
    private String packageName;
    /**
     * 版本号
     */
    private Integer version;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
