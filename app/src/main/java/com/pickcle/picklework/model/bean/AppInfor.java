package com.pickcle.picklework.model.bean;

public class AppInfor {
    //app名称
    private String appName;
    //app大小
    private Integer appSize;
    //app图标
    private String appIcon;
    //app版本
    private String appVersion;
    //app包名
    private String appPackageName;
    //app下载地址
    private String appDownUrl;
    //app每天收益
    private Integer dayAmount;
    //提现规则
    private String withdrawRule;
    //备注
    private String remark;
    //是否推荐
    private Integer recommendFlag;
    //外接下载地址
    private String appDownUrlQuick;
    //简称
    private String appShortName;
    //次数
    private Integer appTimes;

    private boolean checkFlag;


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppSize() {
        return appSize;
    }

    public void setAppSize(Integer appSize) {
        this.appSize = appSize;
    }

    public String getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(String appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public String getAppDownUrl() {
        return appDownUrl;
    }

    public void setAppDownUrl(String appDownUrl) {
        this.appDownUrl = appDownUrl;
    }

    public Integer getDayAmount() {
        return dayAmount;
    }

    public void setDayAmount(Integer dayAmount) {
        this.dayAmount = dayAmount;
    }

    public String getWithdrawRule() {
        return withdrawRule;
    }

    public void setWithdrawRule(String withdrawRule) {
        this.withdrawRule = withdrawRule;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getRecommendFlag() {
        return recommendFlag;
    }

    public void setRecommendFlag(Integer recommendFlag) {
        this.recommendFlag = recommendFlag;
    }

    public String getAppDownUrlQuick() {
        return appDownUrlQuick;
    }

    public void setAppDownUrlQuick(String appDownUrlQuick) {
        this.appDownUrlQuick = appDownUrlQuick;
    }

    public String getAppShortName() {
        return appShortName;
    }

    public void setAppShortName(String appShortName) {
        this.appShortName = appShortName;
    }

    public Integer getAppTimes() {
        return appTimes;
    }

    public void setAppTimes(Integer appTimes) {
        this.appTimes = appTimes;
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }
}
