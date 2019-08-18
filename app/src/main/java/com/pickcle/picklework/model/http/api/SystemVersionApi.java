package com.pickcle.picklework.model.http.api;

import com.pickcle.picklework.http.api.PostApi;
import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.pickcle.picklework.model.http.service.BasicService;
import com.pickcle.picklework.model.http.service.HttpConstants;
import com.justcan.library.activity.RxAppActivity;
import com.justcan.library.activity.RxFragmentActivity;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by justcan on 2017/12/6.
 */

public class SystemVersionApi extends PostApi {

    public SystemVersionApi(HttpOnNextListener listener, RxAppActivity rxActivity) {
        super(listener, rxActivity);
        setAuth(false);
        setHmpCode(HttpConstants.SYSTEM_VERSION_N);
    }
    public SystemVersionApi(HttpOnNextListener listener, RxFragmentActivity rxActivity) {
        super(listener, rxActivity);
        setAuth(false);
        setHmpCode(HttpConstants.SYSTEM_VERSION_N);
    }
    @Override
    public Observable getObservable(Retrofit retrofit) {
        BasicService baseService = retrofit.create(BasicService.class);
        return baseService.checkUpdate(getRequestBody());
    }

}
