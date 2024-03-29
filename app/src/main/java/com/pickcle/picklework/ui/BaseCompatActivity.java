package com.pickcle.picklework.ui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.pgyersdk.crash.PgyCrashManager;
import com.pickcle.picklework.PWApplication;
import com.pickcle.picklework.http.HttpManager;
import com.pickcle.picklework.model.event.EmptyEvent;
import com.justcan.library.activity.RxFragmentActivity;



import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by James on 2016/9/8.
 * Note:
 */
public abstract class BaseCompatActivity extends RxFragmentActivity {
    private Context context;
    public HttpManager httpManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(getContentViewId());

        initHttp();

        ButterKnife.bind(this);

        EventBus.getDefault().register(this);
        PgyCrashManager.register(PWApplication.getContext());

        context = this;
    }

    public Context getContext() {
        return context;
    }
    /**
     * 实例化
     */
    private void initHttp() {
        httpManager = HttpManager.getInstance();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        PgyCrashManager.unregister();
    }

    public abstract int getContentViewId();

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void noneGoEvent(EmptyEvent emptyEvent) {
        finish();
    }
}
