package com.pickcle.picklework.model.http.request;

/**
 * 获取版本请求实体
 */
public class VersionRequest extends BaseRequest{
    /**
     * app版本号
     */
    private Integer versionNo;
    /**
     * 更新类型
     */
    private Integer type;

    public Integer getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
