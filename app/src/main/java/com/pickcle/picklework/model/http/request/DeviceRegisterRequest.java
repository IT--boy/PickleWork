package com.pickcle.picklework.model.http.request;

/**
 * 设备注册请求
 */
public class DeviceRegisterRequest extends BaseRequest {
    //邀请码
    private String invitationCode;

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }
}
