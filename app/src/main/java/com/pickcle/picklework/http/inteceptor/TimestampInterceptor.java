package com.pickcle.picklework.http.inteceptor;

import com.alibaba.fastjson.JSONObject;
import com.pickcle.picklework.http.ResponseCode;
import com.pickcle.picklework.http.api.BaseResultEntity;

import java.io.IOException;
import java.net.ConnectException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by justcan on 2018/1/10.
 * 时间戳不匹配
 */

public class TimestampInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Response response = chain.proceed(original);

        if (response.code() == 200) {
            BaseResultEntity baseResultEntity = JSONObject.parseObject(response.body().string(), BaseResultEntity.class);
            if (baseResultEntity != null && baseResultEntity.getCode() == ResponseCode.TIMESTAMP_NOT_MATCH) {

            }
            return chain.proceed(original);
        } else {
            throw new ConnectException();
        }
    }


}
