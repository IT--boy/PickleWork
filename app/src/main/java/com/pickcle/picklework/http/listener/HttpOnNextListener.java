package com.pickcle.picklework.http.listener;

import rx.Observable;

/**
 * 成功回调处理
 * Created by WZG on 2016/7/16.
 */
public abstract class HttpOnNextListener<T> {
    /**
     * 开始
     */
    public void onStart() {

    }

    /**
     * 结束
     */
    public void onCompleted() {

    }

    /**
     * 成功后回调方法
     *
     * @param model
     */
    public abstract void onSuccess(T model);

    /**
     * 緩存回調結果
     *
     * @param string
     */
    public void onCacheNext(String string) {

    }

    /**
     * 成功后的ober返回，扩展链接式调用
     *
     * @param observable
     */
    public void onNext(Observable observable) {

    }

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     *
     * @param message
     */
    public void onError(String message) {

    }

    /**
     * 取消回調
     */
    public void onCancel() {

    }

    /**
     * 特殊处理
     */
    public void specialNext(int code) {

    }
    public void showMsg(String content) {

    }

}
