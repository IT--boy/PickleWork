package com.pickcle.picklework.model.http.request;

/**
 * 订单提交
 */
public class OrderSubmitRequest extends BaseRequest {
    //收款码id
    private Long qrCodeId;
    //付款人名称
    private String payUserName;

    public Long getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(Long qrCodeId) {
        this.qrCodeId = qrCodeId;
    }

    public String getPayUserName() {
        return payUserName;
    }

    public void setPayUserName(String payUserName) {
        this.payUserName = payUserName;
    }
}
