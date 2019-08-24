package com.pickcle.picklework.model.http.service;

import com.pickcle.picklework.model.bean.BannerListResponse;
import com.pickcle.picklework.model.bean.DeviceInfoResponse;
import com.pickcle.picklework.model.bean.DeviceRegisterResponse;
import com.pickcle.picklework.model.bean.MainResponse;
import com.pickcle.picklework.model.bean.OrderListResponse;
import com.pickcle.picklework.model.bean.QrCodeInfoResponse;
import com.pickcle.picklework.model.bean.VersionResponse;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by justcan on 2018/1/15.
 * 基础接口
 */

public interface AppService {
    /**
     * App升级接口
     */
    @POST(HttpConstants.APP_VERSION)
    Observable<VersionResponse> appVersion(@Body RequestBody param);

    /**
     * 主界面查询接口
     */
    @POST(HttpConstants.APP_MAIN)
    Observable<MainResponse> appMain(@Body RequestBody param);

    /**
     * 导航栏查询接口
     */
    @POST(HttpConstants.APP_BANNER_LIST)
    Observable<BannerListResponse> appBannerList(@Body RequestBody param);

    /**
     * 充值记录列表查询接口
     */
    @POST(HttpConstants.APP_ORDER_LIST)
    Observable<OrderListResponse> appOrderList(@Body RequestBody param);

    /**
     * 收款信息查询接口
     */
    @POST(HttpConstants.APP_QR_CODE_INFO)
    Observable<QrCodeInfoResponse> appQrCodeInfo(@Body RequestBody param);

    /**
     * 订单提交接口
     */
    @POST(HttpConstants.APP_ORDER_SUBMIT)
    Observable<String> appOrderSubmit(@Body RequestBody param);

    /**
     * 设备注册接口
     */
    @POST(HttpConstants.APP_DEVICE_REGISTER)
    Observable<DeviceRegisterResponse> appDeviceRegister(@Body RequestBody param);

    /**
     * 获取设备信息接口
     */
    @POST(HttpConstants.APP_DEVICE_INFO)
    Observable<DeviceInfoResponse> appDeviceInfo(@Body RequestBody param);
}
