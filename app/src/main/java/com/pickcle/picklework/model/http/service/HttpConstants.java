package com.pickcle.picklework.model.http.service;

/**
 * Created by justcan on 2018/1/15.
 * 请求地址常量
 */

public class HttpConstants {
    public static final String RELEASE_URL = "http://154.209.4.20:80/";
    public static final String TEST_URL = "http://192.168.1.6:80/";      //旧测试环境


    /**
     * 获取版本相关信息
     */
    public static final String APP_VERSION = "app/version";
    public static final String APP_VERSION_N = "S10001";
    /**
     * 主界面查询接口
     */
    public static final String APP_MAIN = "app/main";
    public static final String APP_MAIN_N = "S10002";
    /**
     * 导航栏查询接口
     */
    public static final String APP_BANNER_LIST = "app/banner/list";
    public static final String APP_BANNER_LIST_N = "S10003";
    /**
     * 充值记录列表查询接口
     */
    public static final String APP_ORDER_LIST = "app/order/list";
    public static final String APP_ORDER_LIST_N = "S10004";
    /**
     * 收款信息查询接口
     */
    public static final String APP_QR_CODE_INFO = "app/qrcode/info";
    public static final String APP_QR_CODE_INFO_N = "S10005";
    /**
     * 订单提交接口
     */
    public static final String APP_ORDER_SUBMIT = "app/order/submit";
    public static final String APP_ORDER_SUBMIT_N = "S10006";
    /**
     * 设备注册接口
     */
    public static final String APP_DEVICE_REGISTER = "app/device/register";
    public static final String APP_DEVICE_REGISTER_N = "S10007";
    /**
     * 获取设备信息接口
     */
    public static final String APP_DEVICE_INFO = "app/device/info";
    public static final String APP_DEVICE_INFO_N = "S10008";
}
