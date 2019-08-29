package com.pickcle.picklework.model.bean;

import java.io.Serializable;

public class BannerInfo implements Serializable {
    //展示图地址
    private String picUrl;
    //标题内容
    private String title;
    //类型
    private Integer bannerType;
    //备注
    private String remark;
    //链接
    private String url;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getBannerType() {
        return bannerType;
    }

    public void setBannerType(Integer bannerType) {
        this.bannerType = bannerType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
