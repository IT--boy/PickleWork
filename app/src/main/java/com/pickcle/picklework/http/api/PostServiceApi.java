package com.pickcle.picklework.http.api;

import android.app.Service;

import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.pickcle.picklework.http.utils.AesUtils;
import com.pickcle.picklework.util.LogUtil;
import com.google.gson.Gson;
import com.justcan.library.utils.common.StringUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by justcan on 2017/12/1.
 */

public abstract class PostServiceApi extends BaseServiceApi {
    private RequestBody requestBody;

    public PostServiceApi(HttpOnNextListener listener, Service service) {
        super(listener, service);
    }

    /**
     * 明文发送
     *
     * @param body
     */
    public void addRequstBody(Object body) {

        if (body != null) {
            String jsonStr = new Gson().toJson(body);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonStr);
            setRequestBody(requestBody);

            LogUtil.e("request", jsonStr);
        }

    }

    /**
     * 明文发送
     *
     * @param jsonbody
     */
    public void addRequstBody(String jsonbody) {

        if (!StringUtils.isEmpty(jsonbody)) {

            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonbody);
            setRequestBody(requestBody);

            LogUtil.e("request", jsonbody);
        }
    }


    /**
     * 加密发送
     */
    public void addEncryptRequestBody(Object body) {

        try {
            String jsonStr = new Gson().toJson(body);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), AesUtils.encryptBase64(jsonStr, AesUtils.KEY));
            setRequestBody(requestBody);

            LogUtil.e("request", jsonStr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

}
