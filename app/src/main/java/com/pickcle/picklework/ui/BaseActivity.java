package com.pickcle.picklework.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;

import com.pickcle.picklework.model.event.EmptyEvent;
import com.justcan.library.activity.RxAppActivity;


import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by justcan on 2018/1/11.
 */

public abstract class BaseActivity extends RxAppActivity {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(getContentViewId());

        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        context = this;
    }

    public Context getContext() {
        return context;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    public abstract int getContentViewId();

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void noneGoEvent(EmptyEvent emptyEvent) {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
