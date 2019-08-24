package com.pickcle.picklework.model.bean;

import java.util.List;

public class MainResponse {
    //导航集合
    private List<BannerInfo> bannerList;
    private Integer totalSize;
    //app信息集合
    private List<AppInfor> dataList;

    public List<BannerInfo> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerInfo> bannerList) {
        this.bannerList = bannerList;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public List<AppInfor> getDataList() {
        return dataList;
    }

    public void setDataList(List<AppInfor> dataList) {
        this.dataList = dataList;
    }
}
