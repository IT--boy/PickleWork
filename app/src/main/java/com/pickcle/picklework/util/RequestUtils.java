/**
 */

package com.pickcle.picklework.util;


import android.content.Context;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.pickcle.picklework.PWApplication;
import com.pickcle.picklework.model.bean.ErrorModel;
import com.pickcle.picklework.model.event.StopEvent;
import com.justcan.library.utils.common.StringUtils;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;


public class RequestUtils {
    private Context context;

    public RequestUtils(Context context) {
        this.context = context;
    }

    public RequestUtils() {
    }

    public static RequestUtils getInstance(Context context) {
        RequestUtils utils = new RequestUtils(context);
        return utils;
    }

    public static RequestUtils getInstance() {
        RequestUtils utils = new RequestUtils();
        return utils;
    }

    public void getRequest(final String url, final IHttpResponse httpResponse) {
        Log.i("请求参数", url);
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(10000);
        params.setReadTimeout(10000);
        params.setMaxRetryCount(3);
        params.addHeader("App-Name", "Cashing Node");
        params.addHeader("App-Version", AppUtil.getLocalVersionName(PWApplication.getContext()));

        if (httpResponse != null) {
            httpResponse.onStart();
        }

        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("返回日志：", "response: " + result);
                if (httpResponse != null) {
                    httpResponse.OnHttpData(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                setErrorData(httpResponse, ex);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (httpResponse != null) {
                    httpResponse.onEnd();
                }
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    public synchronized void postRequest(final String url, final Object object, final IHttpResponse httpResponse) {
        final String json = JSON.toJSONString(object);
        Log.e("请求参数", url + "\n" + json);
//        LogUtil.writeLog(String.format("支付回调请求内容 --- %s", json), "node_log");
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(10000);
        params.setReadTimeout(10000);
        params.setMaxRetryCount(3);
        params.addHeader("App-Name", "Cashing Node");
        params.addHeader("App-Version", AppUtil.getLocalVersionName(PWApplication.getContext()));

        params.setAsJsonContent(true);
        params.setBodyContent(json);
        if (httpResponse != null) {
            httpResponse.onStart();
        }

        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("返回日志：", "response: " + result);
//                LogUtil.writeLog(String.format("支付回调返回 --- %s", result), "node_log");
                if (httpResponse != null) {
                    httpResponse.OnHttpData(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                setErrorData(httpResponse, ex);

            }

            @Override
            public void onCancelled(CancelledException cex) {
                if (httpResponse != null) {
                    httpResponse.OnHttpDataError(new Exception(cex.getMessage()));
                }
            }

            @Override
            public void onFinished() {
                if (httpResponse != null) {
                    httpResponse.onEnd();
                }
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
    public synchronized void postRequestLog(final String url, final Object object, final IHttpResponse httpResponse) {
        final String json = JSON.toJSONString(object);
        Log.e("请求参数", url + "\n" + json);
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(10000);
        params.setReadTimeout(10000);
        params.setMaxRetryCount(3);
        params.addHeader("App-Name", "Cashing Node");
        params.addHeader("App-Version", AppUtil.getLocalVersionName(PWApplication.getContext()));

        params.setAsJsonContent(true);
        params.setBodyContent(json);
        if (httpResponse != null) {
            httpResponse.onStart();
        }

        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("返回日志：", "response: " + result);
                if (httpResponse != null) {
                    httpResponse.OnHttpData(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                setErrorData(httpResponse, ex);

            }

            @Override
            public void onCancelled(CancelledException cex) {
                if (httpResponse != null) {
                    httpResponse.OnHttpDataError(new Exception(cex.getMessage()));
                }
            }

            @Override
            public void onFinished() {
                if (httpResponse != null) {
                    httpResponse.onEnd();
                }
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }
    public void uploadFileRequest(final String url, File file, final IHttpResponse httpResponse) {
        Log.e("请求参数", url);
        RequestParams params = new RequestParams(url);
        params.addHeader("App-Name", "Cashing Node");
        params.setConnectTimeout(10000);
        params.setReadTimeout(10000);
        params.setMaxRetryCount(3);

        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("qrCodeFile", file));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);
        if (httpResponse != null) {
            httpResponse.onStart();
        }
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("返回日志：", "response: " + result);
                if (httpResponse != null) {
                    httpResponse.OnHttpData(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                setErrorData(httpResponse, ex);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (httpResponse != null) {
                    httpResponse.onEnd();
                }
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    public void uploadFileRequest(final String url, Map<String, Object> mapParams, final IHttpResponse httpResponse) {
        Log.i("请求参数", url);
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(10000);
        params.setReadTimeout(10000);
        params.setMaxRetryCount(3);
        params.addHeader("App-Name", "Cashing Node");
        params.addHeader("App-Version", AppUtil.getLocalVersionName(PWApplication.getContext()));

        List<KeyValue> list = new ArrayList<>();
        for (Map.Entry<String, Object> entry : mapParams.entrySet()) {
            list.add(new KeyValue(entry.getKey(), entry.getValue()));
        }
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);
        if (httpResponse != null) {
            httpResponse.onStart();
        }
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("返回日志：", "response: " + result);
                if (httpResponse != null) {
                    httpResponse.OnHttpData(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                setErrorData(httpResponse, ex);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (httpResponse != null) {
                    httpResponse.onEnd();
                }
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    public void uploadFileRequest(String fileName, final String url, File file, final IHttpResponse httpResponse) {
        Log.i("请求参数", url);
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(10000);
        params.setReadTimeout(10000);
        params.setMaxRetryCount(3);
        params.addHeader("App-Name", "Cashing Node");
        params.addHeader("App-Version", AppUtil.getLocalVersionName(PWApplication.getContext()));

        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue(fileName, file));
        MultipartBody body = new MultipartBody(list, "UTF-8");
        params.setRequestBody(body);
        if (httpResponse != null) {
            httpResponse.onStart();
        }
        x.http().post(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("返回日志：", "response: " + result);
                if (httpResponse != null) {
                    httpResponse.OnHttpData(result);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                setErrorData(httpResponse, ex);

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                if (httpResponse != null) {
                    httpResponse.onEnd();
                }
            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
    }

    private void setErrorData(IHttpResponse httpResponse, Throwable ex) {
        Log.e("错误日志：", ex.toString());
        if (ex instanceof HttpException) {
            ErrorModel errorModel = JSON.parseObject(((HttpException) ex).getResult(), ErrorModel.class);
            if (((HttpException) ex).getErrorCode().equals("400")) {
                if (errorModel != null && !StringUtils.isEmpty(errorModel.getErrCode()) && "CASHING_NODE_HEARTBEAT_ERROR".equals(errorModel.getErrCode())) {
                    EventBus.getDefault().post(new StopEvent(errorModel.getErrMessage()));
                }
                if (httpResponse != null) {
                    httpResponse.OnHttpDataError(new Exception(errorModel.getErrMessage()));
                }
            } else {
                if (httpResponse != null) {
                    httpResponse.OnHttpDataError(new Exception(errorModel.getMessage()));
                }
            }
        } else {
            if (httpResponse != null) {
                httpResponse.OnHttpDataError(new Exception(ex.getMessage()));
            }
        }
    }
}