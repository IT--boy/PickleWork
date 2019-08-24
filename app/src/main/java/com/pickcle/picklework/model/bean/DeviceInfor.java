package com.pickcle.picklework.model.bean;


public class DeviceInfor {
    //打工标志
    private Integer workFlag;
    //打工有效期
    private String workExpiryDate;
    //学习标志
    private Integer studyFlag;
    //学习有效期
    private String studyExpiryDate;
    //自己的邀请码
    private String invitationCode;
    //类型
    private Integer type;

    public Integer getWorkFlag() {
        return workFlag;
    }

    public void setWorkFlag(Integer workFlag) {
        this.workFlag = workFlag;
    }

    public String getWorkExpiryDate() {
        return workExpiryDate;
    }

    public void setWorkExpiryDate(String workExpiryDate) {
        this.workExpiryDate = workExpiryDate;
    }

    public Integer getStudyFlag() {
        return studyFlag;
    }

    public void setStudyFlag(Integer studyFlag) {
        this.studyFlag = studyFlag;
    }

    public String getStudyExpiryDate() {
        return studyExpiryDate;
    }

    public void setStudyExpiryDate(String studyExpiryDate) {
        this.studyExpiryDate = studyExpiryDate;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
