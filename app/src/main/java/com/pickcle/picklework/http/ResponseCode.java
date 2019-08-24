package com.pickcle.picklework.http;

/**
 * Created by justcan on 2018/1/10.
 */

public class ResponseCode {
    /**
     * 失败
     */
    public final static int OPERATE_FAILURE = 500;
    /***
     * 成功
     */
    public final static int OPERATE_SUCCESS = 0;
    /**
     * 时间戳不匹配
     */
    public final static int TIMESTAMP_NOT_MATCH = 2;
    /**
     * 授权码为空
     */
    public final static int TOKEN_EMPTY = 3;
    /**
     * 请求体为空
     */
    public final static int REQUEST_EMPTY = 4;
    /**
     * 授权码过期
     */
    public final static int TOKEN_INVALID = 5;
    /**
     * 流水号重复提交
     */
    public static final int SERIAL_NUMBER_REPEAT = 6;
    /**
     * 运动计划概览过期
     */
    public static final int MOTION_PLAN_OUT_OF_DATE = 53003;
    /**
     * 已存在运动计划
     */
    public static final int MOTION_PLAN_EXIST = 52002;
    /**
     * 用户锁定
     */
    public static final int USER_LOCK = 4005;
    /**
     * 频繁请求
     */
    public final static int REQUEST_MORE = 7;
    /**
     * 请求参数类型不匹配
     */
    public final static int PARAM_NOT_MATCH = 400;
    /**
     * 手机号未绑定
     */
    public final static int MOBILE_NO_BIND = 6003;
}
