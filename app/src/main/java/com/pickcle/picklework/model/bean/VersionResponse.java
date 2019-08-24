package com.pickcle.picklework.model.bean;

public class VersionResponse {
    /**
     * 版本信息
     */
    private VersionInfo versionInfo;

    /**
     * 设备信息
     */
    private DeviceInfor deviceInfor;

    public VersionInfo getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfo versionInfo) {
        this.versionInfo = versionInfo;
    }

    public DeviceInfor getDeviceInfor() {
        return deviceInfor;
    }

    public void setDeviceInfor(DeviceInfor deviceInfor) {
        this.deviceInfor = deviceInfor;
    }
}
