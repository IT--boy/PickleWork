package com.pickcle.picklework.http.inteceptor;

import com.pickcle.picklework.constant.Constants;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by justcan on 2017/11/30.
 * 统一请求参数
 */

public class ParamInterceptor implements Interceptor {
    private boolean isAuth;
    private String hmpCode;


    public ParamInterceptor(boolean isAuth, String hmpCode) {
        this.isAuth = isAuth;
        this.hmpCode = hmpCode;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = null;

        if (isAuth) {
            url = originalHttpUrl.newBuilder()
                    .addQueryParameter("appKey", Constants.APP_KEY)
                    .build();

        } else {
            url = originalHttpUrl.newBuilder()
                    .addQueryParameter("appKey", Constants.APP_KEY)
                    .build();
        }
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
