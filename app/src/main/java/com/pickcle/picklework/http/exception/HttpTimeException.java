package com.pickcle.picklework.http.exception;

/**
 * 自定义错误信息，统一处理返回处理
 * Created by WZG on 2016/7/16.
 */
public class HttpTimeException extends RuntimeException {


    public HttpTimeException(String detailMessage) {
        super(detailMessage);
    }

}

