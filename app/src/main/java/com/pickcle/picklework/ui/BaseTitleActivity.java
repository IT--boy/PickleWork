package com.pickcle.picklework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.pickcle.picklework.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by James on 2016/8/2.
 * Note:
 */
public abstract class BaseTitleActivity extends BaseActivity {
    @BindView(R.id.titleText)
    public TextView titleText;
    @BindView(R.id.btnLeftImg)
    ImageView btnBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("NowActivityIs", getClass().getSimpleName());
    }

    public void setTitleText(int resId) {
        if (titleText != null) {
            titleText.setText(resId);
        }
    }

    public void setTitleTextColor(int textColor) {
        if (titleText != null) {
            titleText.setTextColor(ContextCompat.getColor(getContext(), textColor));
        }
    }

    public void setTitleText(String str) {
        if (titleText != null) {
            titleText.setText(str);
        }
    }

    public void setBackView() {
        if (btnBack != null) {
            btnBack.setVisibility(View.VISIBLE);
        }
    }

    public void setLeftImage(int resId) {
        if (btnBack != null) {
            btnBack.setImageResource(resId);
        }
    }

    @OnClick(R.id.btnLeftImg)
    public void back(View view) {
        finish();
    }

    @Override
    public int getContentViewId() {
        return getLayoutId();
    }

    public abstract int getLayoutId();

    @Override
    public void onBackPressed() {
        finish();
    }
}
