package com.pickcle.picklework.model.bean;

import java.util.List;

public class BannerListResponse {
    //导航集合
    private List<BannerInfo> bannerList;
    private DeviceInfor deviceInfor;

    public List<BannerInfo> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerInfo> bannerList) {
        this.bannerList = bannerList;
    }

    public DeviceInfor getDeviceInfor() {
        return deviceInfor;
    }

    public void setDeviceInfor(DeviceInfor deviceInfor) {
        this.deviceInfor = deviceInfor;
    }
}
