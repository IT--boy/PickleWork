package com.pickcle.picklework;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.multidex.MultiDex;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.justcan.library.RxRetrofitApp;
import com.pgyersdk.crash.PgyCrashManager;
import com.pickcle.picklework.autojs.AutoJs;
import com.pickcle.picklework.autojs.GlobalKeyObserver;
import com.pickcle.picklework.model.http.service.HttpConstants;
import com.pickcle.picklework.util.SdcardUtils;
import com.stardust.app.GlobalAppContext;
import com.stardust.autojs.core.ui.inflater.ImageLoader;
import com.stardust.autojs.core.ui.inflater.util.Drawables;
import com.tencent.tinker.entry.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

import org.xutils.x;

public class PWApplication extends Application {
    private static Context context;
    private static String requestUrl;
    private ApplicationLike tinkerApplicationLike;

    @Override
    public void onCreate() {
        super.onCreate();
        initThinker();

        context = getApplicationContext();

        SdcardUtils.initSdcardFolders(getContext());

        initLibs();

        initUrl();
    }

    private void initThinker() {
        // 我们可以从这里获得Tinker加载过程的信息
        tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();

        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
        TinkerPatch.init(tinkerApplicationLike)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3);

        TinkerPatch.with().fetchPatchUpdate(true);
        // 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();
    }

    private void initLibs() {
        PgyCrashManager.register(this);

        x.Ext.init(this);
        GlobalAppContext.set(this);
        AutoJs.initInstance(this);
        GlobalKeyObserver.init();
        Drawables.setDefaultImageLoader(new ImageLoader() {
            @Override
            public void loadInto(ImageView imageView, Uri uri) {
                Glide.with(PWApplication.this)
                        .load(uri)
                        .into(imageView);
            }

            @Override
            public void loadIntoBackground(View view, Uri uri) {
                Glide.with(PWApplication.this)
                        .load(uri)
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                view.setBackground(resource);
                            }
                        });
            }

            @Override
            public Drawable load(View view, Uri uri) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void load(View view, Uri uri, DrawableCallback drawableCallback) {
                Glide.with(PWApplication.this)
                        .load(uri)
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                drawableCallback.onLoaded(resource);
                            }
                        });
            }

            @Override
            public void load(View view, Uri uri, BitmapCallback bitmapCallback) {
                Glide.with(PWApplication.this)
                        .asBitmap()
                        .load(uri)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                bitmapCallback.onLoaded(resource);
                            }
                        });
            }
        });

    }

    private void initUrl() {
        if (BuildConfig.API_ENV) {
            requestUrl = HttpConstants.RELEASE_URL;
            RxRetrofitApp.init(this, false);
        } else {
            requestUrl = HttpConstants.TEST_URL;
            RxRetrofitApp.init(this, true);
        }
    }
    public static Context getContext() {
        return context;
    }

    public static String getRequestUrl() {
        return requestUrl;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
