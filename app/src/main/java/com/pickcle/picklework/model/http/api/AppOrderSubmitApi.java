package com.pickcle.picklework.model.http.api;

import com.justcan.library.activity.RxAppActivity;
import com.justcan.library.activity.RxFragmentActivity;
import com.pickcle.picklework.http.api.PostApi;
import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.pickcle.picklework.model.http.service.AppService;
import com.pickcle.picklework.model.http.service.HttpConstants;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by justcan on 2017/12/6.
 */

public class AppOrderSubmitApi extends PostApi {

    public AppOrderSubmitApi(HttpOnNextListener listener, RxAppActivity rxActivity) {
        super(listener, rxActivity);
        setAuth(false);
        setHmpCode(HttpConstants.APP_ORDER_SUBMIT_N);
    }

    public AppOrderSubmitApi(HttpOnNextListener listener, RxFragmentActivity rxActivity) {
        super(listener, rxActivity);
        setAuth(false);
        setHmpCode(HttpConstants.APP_ORDER_SUBMIT_N);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        AppService appService = retrofit.create(AppService.class);
        return appService.appOrderSubmit(getRequestBody());
    }

}
