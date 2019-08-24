package com.pickcle.picklework.model.bean;

public class QrCodeInfoResponse {
    //收款码地址
    private String qrCodeUrl;
    //收款类型
    private Integer qrCodeType;
    //收款方式
    private Integer paymentTerm;
    //备注
    private String remark;
    //收款金额
    private Integer amount;
    //收款码id
    private Long qrCodeId;

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public Integer getQrCodeType() {
        return qrCodeType;
    }

    public void setQrCodeType(Integer qrCodeType) {
        this.qrCodeType = qrCodeType;
    }

    public Integer getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(Integer paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getQrCodeId() {
        return qrCodeId;
    }

    public void setQrCodeId(Long qrCodeId) {
        this.qrCodeId = qrCodeId;
    }
}
