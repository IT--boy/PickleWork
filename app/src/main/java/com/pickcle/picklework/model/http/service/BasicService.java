package com.pickcle.picklework.model.http.service;

import com.pickcle.picklework.model.bean.SystemVersion;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by justcan on 2018/1/15.
 * 基础接口
 */

public interface BasicService {
    /**
     * App升级接口
     */
    @POST(HttpConstants.SYSTEM_VERSION)
    Observable<SystemVersion> checkUpdate(@Body RequestBody param);

}
