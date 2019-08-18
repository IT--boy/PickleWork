package com.pickcle.picklework.util;

/**
 * 网络请求返回接口
 * Created by Administrator on 2018/10/9.
 */

public interface IHttpResponse {
    void onStart();

    void onEnd();

    void OnHttpData(String data);

    void OnHttpDataError(Exception e);


}
