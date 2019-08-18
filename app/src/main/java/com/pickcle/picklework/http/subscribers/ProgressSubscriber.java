package com.pickcle.picklework.http.subscribers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;

import com.pickcle.picklework.R;
import com.pickcle.picklework.http.HttpManager;
import com.pickcle.picklework.http.api.PostApi;
import com.pickcle.picklework.http.cookie.CookieResult;
import com.pickcle.picklework.http.exception.CodeException;
import com.pickcle.picklework.http.exception.CodeFailException;
import com.pickcle.picklework.http.exception.EmptyException;
import com.pickcle.picklework.http.exception.HttpTimeException;
import com.pickcle.picklework.http.exception.IgnoreException;
import com.pickcle.picklework.http.exception.ServerException;
import com.pickcle.picklework.http.exception.SpecialException;
import com.pickcle.picklework.http.exception.TimestampException;
import com.pickcle.picklework.http.exception.TokenException;
import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.pickcle.picklework.http.utils.CookieDbUtil;
import com.pickcle.picklework.model.event.EmptyEvent;
import com.pickcle.picklework.util.LogUtil;
import com.justcan.library.RxRetrofitApp;
import com.justcan.library.activity.RxAppActivity;
import com.justcan.library.activity.RxFragmentActivity;
import com.justcan.library.utils.common.AppUtil;
import com.justcan.library.utils.common.StringUtils;
import com.justcan.library.utils.common.ToastUtil;
import com.justcan.library.view.DialogRequestLoad;

import java.lang.ref.SoftReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import de.greenrobot.event.EventBus;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Created by WZG on 2016/7/16.
 */
public class ProgressSubscriber<T> extends Subscriber<T> {
    /*是否弹框*/
    private boolean showPorgress = true;
    /* 软引用回调接口*/
    private HttpOnNextListener mSubscriberOnNextListener;
    /*软引用反正内存泄露*/
    private SoftReference<RxAppActivity> mActivity;
    private SoftReference<RxFragmentActivity> rxFragmentActivity;
    /*加载框可自己定义*/
    private DialogRequestLoad pd;
    /*请求数据*/
    private PostApi api;


    /**
     * 构造
     *
     * @param api
     */
    public ProgressSubscriber(PostApi api) {
        this.api = api;
        this.mSubscriberOnNextListener = api.getListener();
        this.mActivity = new SoftReference<>(api.getRxActivity());
        this.rxFragmentActivity = new SoftReference<>(api.getRxFragmentActivity());
        setShowPorgress(api.isShowProgress());
        if (api.isShowProgress()) {
            initProgressDialog(api.isCancel(), api.getLoadContent());
        }
    }


    /**
     * 初始化加载框
     */
    private void initProgressDialog(boolean cancel, String messageContent) {
        Context context = null;
        if (mActivity.get() != null) {
            context = mActivity.get();
        } else if (rxFragmentActivity.get() != null) {
            context = rxFragmentActivity.get();
        }
        if (pd == null && context != null) {
            pd = DialogRequestLoad.getInstance(context, messageContent, false, null);
            pd.setCancelable(cancel);
            if (cancel) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        if (mSubscriberOnNextListener != null) {
                            mSubscriberOnNextListener.onCancel();
                        }
                        onCancelProgress();
                    }
                });
            }
        }
    }


    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        if (!isShowPorgress()) return;
        Context context = null;
        if (mActivity.get() != null) {
            context = mActivity.get();
        } else if (rxFragmentActivity.get() != null) {
            context = rxFragmentActivity.get();
        }
        if (pd == null || context == null) return;
        if (!pd.isShowing()) {
            pd.show();
        }
    }


    /**
     * 隐藏
     */
    private void dismissProgressDialog() {
        if (!isShowPorgress()) return;
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }


    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onStart();
        }
        /*缓存并且有网*/
        if (api.isCache() && AppUtil.isNetworkAvailable(RxRetrofitApp.getApplication())) {
             /*获取缓存数据*/
            CookieResult cookieResulte = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
            if (cookieResulte != null) {
                long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                if (time < api.getCookieNetWorkTime()) {
                    if (mSubscriberOnNextListener != null) {
                        mSubscriberOnNextListener.onCacheNext(cookieResulte.getResulte());
                    }
                    onCompleted();
                    unsubscribe();
                }
            }
        }
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onCompleted();
        }
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param throwable
     */
    @Override
    public void onError(Throwable throwable) {
        dismissProgressDialog();
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onCompleted();
        }
        /*需要緩存并且本地有缓存才返回*/
        if (api.isCache()) {
            Observable.just(api.getUrl()).subscribe(new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    errorDo(e.getMessage());
                }

                @Override
                public void onNext(String s) {
                    /*获取缓存数据*/
                    CookieResult cookieResulte = CookieDbUtil.getInstance().queryCookieBy(s);
                    if (cookieResulte == null) {
                        throw new HttpTimeException("网络错误");
                    }
                    long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                    if (time < api.getCookieNoNetWorkTime()) {
                        if (mSubscriberOnNextListener != null) {
                            mSubscriberOnNextListener.onCacheNext(cookieResulte.getResulte());
                        }
                    } else {
                        CookieDbUtil.getInstance().deleteCookie(cookieResulte);
                        throw new HttpTimeException("网络错误");
                    }
                }
            });
        } else {
            Activity context = null;
            if (mActivity.get() != null) {
                context = mActivity.get();
            } else if (rxFragmentActivity.get() != null) {
                context = rxFragmentActivity.get();
            }
            if (context == null || context.isDestroyed() || context.isFinishing())
                return;
            if (throwable instanceof TimestampException) {
                if (api.getRxFragmentActivity() != null) {
                    HttpManager.getInstance().doHttpDealF(api);
                }
                if (api.getRxActivity() != null) {
                    HttpManager.getInstance().doHttpDeal(api);
                }
            } else if (throwable instanceof TokenException) {
                EventBus.getDefault().post(new EmptyEvent());

            } else if (throwable instanceof IgnoreException) {
                errorDo(throwable.getMessage());
            } else if (throwable instanceof ConnectException || throwable instanceof UnknownHostException || throwable instanceof SocketTimeoutException) {
                ToastUtil.showToast(context, R.string.network_wrong_tip);
                errorDo(throwable.getMessage());
            } else if (throwable instanceof SpecialException) {
                SpecialException specialException = (SpecialException) throwable;
                if (mSubscriberOnNextListener != null) {
                    mSubscriberOnNextListener.specialNext(specialException.getReturnCode());
                }
            } else if (throwable instanceof CodeException) {
                CodeException codeException = (CodeException) throwable;
                if (!StringUtils.isEmpty(codeException.getMessage())) {
                    ToastUtil.showToast(context, codeException.getMessage());
                }
                if (mSubscriberOnNextListener != null) {
                    mSubscriberOnNextListener.showMsg(codeException.getMessage());
                }
                errorDo(throwable.getMessage());
            } else if (throwable instanceof HttpException || throwable instanceof ServerException) {
                ToastUtil.showToast(context, R.string.http_error_server_down);
                errorDo(throwable.getMessage());
            } else if (throwable instanceof EmptyException) {
                EmptyException codeException = (EmptyException) throwable;
                if (mSubscriberOnNextListener != null) {
                    mSubscriberOnNextListener.showMsg(codeException.getMessage());
                    mSubscriberOnNextListener.onSuccess(null);
                }
            } else if (throwable instanceof CodeFailException) {
                CodeFailException codeFailException = (CodeFailException) throwable;
                if (!StringUtils.isEmpty(codeFailException.getMessage())) {
                    ToastUtil.showToast(context, codeFailException.getMessage());
                }
                errorDo(throwable.getMessage());
            } else if (throwable instanceof HttpTimeException) {

            } else {
                ToastUtil.showToast(context, R.string.app_error_text);
                errorDo(throwable.getMessage());
                LogUtil.e("错误提示-->", throwable.getMessage());
                throwable.printStackTrace();
            }
        }
    }

    /*错误统一处理*/
    private void errorDo(String message) {

        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onError(message);
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onSuccess(t);
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }


    public boolean isShowPorgress() {
        return showPorgress;
    }

    /**
     * 是否需要弹框设置
     *
     * @param showPorgress
     */
    public void setShowPorgress(boolean showPorgress) {
        this.showPorgress = showPorgress;
    }
}