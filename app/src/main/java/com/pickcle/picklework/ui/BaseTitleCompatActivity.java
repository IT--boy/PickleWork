package com.pickcle.picklework.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pickcle.picklework.R;
import com.pickcle.picklework.model.event.StopEvent;


import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by James on 2016/8/2.
 * Note:
 */
public abstract class BaseTitleCompatActivity extends BaseCompatActivity {
    @BindView(R.id.titleText)
    public TextView titleText;
    @BindView(R.id.btnLeftImg)
    public ImageView btnBack;
    @BindView(R.id.subItem)
    public View subItem;
    private boolean isShowFlag = false;
    protected boolean isDialogFlag = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            subItem.setVisibility(View.VISIBLE);
        } else {
            subItem.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        isShowFlag = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isShowFlag = false;
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

    //停止工作
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void stopEvent(StopEvent stopEvent) {
        if (isShowFlag && !isDialogFlag) {
            showTipsDialog(stopEvent.getMessage());
        }
    }

    private void showTipsDialog(String message) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(this);
        normalDialog.setTitle("温馨提示");
        normalDialog.setMessage(message);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        normalDialog.show();
    }
}
