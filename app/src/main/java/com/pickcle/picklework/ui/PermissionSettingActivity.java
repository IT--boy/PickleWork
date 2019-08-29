package com.pickcle.picklework.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;

import com.justcan.library.utils.common.PermissionPageUtils;
import com.pickcle.picklework.R;
import com.pickcle.picklework.autojs.AccessibilityServiceTool;

import butterknife.OnClick;

public class PermissionSettingActivity extends BaseTitleCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
        setData();
    }

    private void initData() {

    }

    private void initView() {
        setBackView();
        setTitleText("权限设置");
    }

    private void setData() {

    }

    //权限设置
    @OnClick(R.id.gotoPermission1)
    public void gotoPermission1(View view) {
        PermissionPageUtils.getIntence(getContext()).jumpPermissionPage();
    }

    @OnClick(R.id.gotoPermission2)
    public void gotoPermission2(View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            return;
        }
        startActivity(new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS));
    }

    @OnClick(R.id.gotoPermission3)
    public void gotoPermission3(View view) {
        startActivity(new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS));
    }

    @OnClick(R.id.gotoPermission4)
    public void gotoPermission4(View view) {
        AccessibilityServiceTool.goToAccessibilitySetting();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_permission_settin_layout;
    }
}
