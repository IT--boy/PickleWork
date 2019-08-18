package com.pickcle.picklework.http.inteceptor;

import com.pickcle.picklework.PWApplication;
import com.justcan.library.utils.common.AppUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by justcan on 2017/11/30.
 */

public class HeaderInterceptor implements Interceptor {

    private String hmpCode;

    public HeaderInterceptor(String hmpCode) {
        this.hmpCode = hmpCode;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Hmp-Code", this.hmpCode)
                .addHeader("Content-Type", "application/json")
                .addHeader("charset", "utf-8")
                .addHeader("device", android.os.Build.MANUFACTURER + "-" + android.os.Build.MODEL)
                .addHeader("deviceVersion", android.os.Build.VERSION.RELEASE + "-" + android.os.Build.VERSION.SDK_INT)
                .addHeader("appVersion", AppUtil.getVersion(PWApplication.getContext()))
                .build();

        return chain.proceed(request);
    }
}
