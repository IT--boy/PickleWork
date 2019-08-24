package com.pickcle.picklework.model.http.request;

public class QrCodeInfoRequest extends BaseRequest{
    //类型
    private Integer qrCodeType;
    //收款方式
    private Integer paymentTerm;

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
}
