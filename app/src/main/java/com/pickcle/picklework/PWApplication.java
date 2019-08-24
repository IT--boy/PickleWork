package com.pickcle.picklework;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.multidex.MultiDex;
import android.view.View;
import android.widget.ImageView;

import com.pickcle.picklework.autojs.AutoJs;
import com.pickcle.picklework.autojs.GlobalKeyObserver;
import com.pickcle.picklework.model.http.service.HttpConstants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.justcan.library.RxRetrofitApp;
import com.pickcle.picklework.util.SdcardUtils;
import com.stardust.app.GlobalAppContext;
import com.stardust.autojs.core.ui.inflater.ImageLoader;
import com.stardust.autojs.core.ui.inflater.util.Drawables;

import org.xutils.x;

public class PWApplication extends Application {
    private static Context context;
    private static String requestUrl;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        SdcardUtils.initSdcardFolders(getContext());
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
        initUrl();
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
