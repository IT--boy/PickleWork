package com.pickcle.picklework.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.justcan.library.activity.RxAppActivity;
import com.justcan.library.dialog.CBDialogBuilder;
import com.justcan.library.utils.common.AppUtil;
import com.justcan.library.utils.common.InputUtils;
import com.justcan.library.utils.common.StringUtils;
import com.justcan.library.utils.common.ToastUtil;
import com.pickcle.picklework.PWApplication;
import com.pickcle.picklework.Pref;
import com.pickcle.picklework.R;
import com.pickcle.picklework.constant.Constants;
import com.pickcle.picklework.http.HttpManager;
import com.pickcle.picklework.http.download.DownInfo;
import com.pickcle.picklework.http.download.DownState;
import com.pickcle.picklework.http.download.HttpDownManager;
import com.pickcle.picklework.http.listener.HttpDownOnNextListener;
import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.pickcle.picklework.model.bean.VersionInfo;
import com.pickcle.picklework.model.bean.VersionResponse;
import com.pickcle.picklework.model.event.EmptyEvent;
import com.pickcle.picklework.model.http.api.AppVersionApi;
import com.pickcle.picklework.model.http.request.VersionRequest;
import com.pickcle.picklework.util.SdcardUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

import static android.content.pm.PackageManager.PERMISSION_DENIED;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;


/**
 * Created by James on 2016/9/22.
 * Note:启动界面
 */
public class LaunchActivity extends RxAppActivity {
    private static final int PERMISSION_REQUEST_CODE = 11186;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.LunchTheme);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.account_launch_layout);
        EventBus.getDefault().register(this);

        if (!Pref.isFirstUsing()) {
            main();
            return;
        }

        loadUpdateInfo();
    }

    private void main() {
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE);
    }


    private void runScript() {
        loadUpdateInfo();
    }

    protected void checkPermission(String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] requestPermissions = getRequestPermissions(permissions);
            if (requestPermissions.length > 0) {
                requestPermissions(requestPermissions, PERMISSION_REQUEST_CODE);
            } else {
                runScript();
            }
        } else {
            int[] grantResults = new int[permissions.length];
            Arrays.fill(grantResults, PERMISSION_GRANTED);
            onRequestPermissionsResult(PERMISSION_REQUEST_CODE, permissions, grantResults);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private String[] getRequestPermissions(String[] permissions) {
        List<String> list = new ArrayList<>();
        for (String permission : permissions) {
            if (checkSelfPermission(permission) == PERMISSION_DENIED) {
                list.add(permission);
            }
        }
        return list.toArray(new String[list.size()]);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void ChangeRestCountDown() {
        if (count <= 0) {
            if (!StringUtils.isEmpty(Pref.getCode())) {
                Intent gotoNext = new Intent(this, MainWorkActivity.class);
                startActivity(gotoNext);
            } else {
                Intent gotoNext = new Intent(this, MainActivity.class);
                startActivity(gotoNext);
            }

            finish();
            return;
        }
        count--;
        handler.sendEmptyMessageDelayed(HANDLER_CODE, 2000);
    }

    private int count = Constants.LAUCH_COUNT_DOWN;
    private static final int HANDLER_CODE = 100086;
    /**
     * 倒计时
     */
    private Handler handler = new CountDownHandler(this);

    private static class CountDownHandler extends Handler {
        private final WeakReference<LaunchActivity> mActivity;

        public CountDownHandler(LaunchActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivity.get() == null) {
                return;
            }
            switch (msg.what) {
                case HANDLER_CODE:

                    mActivity.get().ChangeRestCountDown();

                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        handler.removeCallbacksAndMessages(null);
    }

    private static VersionInfo versionInfo = null;

    private void loadUpdateInfo() {
        VersionRequest request = new VersionRequest();
        request.setVersionNo(AppUtil.getVersionCode(getBaseContext()));
        request.setType(1);
        String deviceNo = AppUtil.getDeviceNo(getBaseContext());
        if (StringUtils.isEmpty(deviceNo)) {
            main();
            return;
        } else {
            request.setDeviceNo(deviceNo);
        }

        AppVersionApi api = new AppVersionApi(new HttpOnNextListener<VersionResponse>() {
            @Override
            public void onSuccess(VersionResponse model) {
                if (model != null && model.getDeviceInfor() != null) {
                    Pref.putCode(model.getDeviceInfor().getInvitationCode());
                }
                if (model != null && model.getVersionInfo() != null) {
                    versionInfo = model.getVersionInfo();

                    if (versionInfo.getUpdateFlag() == 0) {
                        handler.sendEmptyMessage(HANDLER_CODE);
                    } else {
                        showNoticeDialog(model.getVersionInfo());
                    }

                } else {
                    handler.sendEmptyMessage(HANDLER_CODE);
                }
            }

            @Override
            public void onError(String message) {
                handler.sendEmptyMessage(HANDLER_CODE);
            }
        }, this);
        api.addRequstBody(request);

        HttpManager.getInstance().doHttpDeal(api);
    }


    /**
     * 显示软件更新对话框
     */

    private void showNoticeDialog(final VersionInfo update) {
        final CBDialogBuilder builder = new CBDialogBuilder(this);
        builder.setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM);
        builder.setTouchOutSideCancelable(false);

        View customView = LayoutInflater.from(PWApplication.getContext()).inflate(R.layout.dialog_title_content_confirm_layout, null);
        TextView title = customView.findViewById(R.id.title);
        TextView content = customView.findViewById(R.id.content);
        title.setText("检测到版本更新");
        if (StringUtils.isEmpty(update.getRemark())) {
            content.setVisibility(View.GONE);
        } else {
            content.setVisibility(View.VISIBLE);
            content.setText(update.getRemark());
        }
        TextView btnCancel = customView.findViewById(R.id.btnCancel);
        TextView btnConfirm = customView.findViewById(R.id.btnConfirm);

        btnCancel.setTextColor(ContextCompat.getColor(this, R.color.dialog_confirm_text_color));
        btnConfirm.setTextColor(ContextCompat.getColor(this, R.color.dialog_cancel_text_color));

        if (update.getForceUpate() == 1) {
            btnCancel.setText("退出咸菜打工");
        } else {
            btnCancel.setText("取消");
        }

        builder.setView(customView);
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (update.getUpdateFlag() == 1) {
                        return true;
                    }
                }
                return false;
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showDownloadDialog(update);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (update.getForceUpate() == 1) {
                    finish();
                } else {
                    handler.sendEmptyMessage(HANDLER_CODE);
                }
            }
        });
    }

    /**
     * 显示软件下载对话框
     */
    private ProgressBar progressBarDownload;
    private TextView content;
    private Dialog dialog;

    private void showDownloadDialog(final VersionInfo update) {
        final CBDialogBuilder builder = new CBDialogBuilder(this);
        builder.setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM);
        builder.setTouchOutSideCancelable(false);

        View customView = LayoutInflater.from(PWApplication.getContext()).inflate(R.layout.dialog_content_progress_bar_layout, null);

        content = customView.findViewById(R.id.content);
        content.setText("正在更新");

        progressBarDownload = customView.findViewById(R.id.progressBarDownload);

        builder.setView(customView);
        dialog = builder.create();
        dialog.show();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (update.getUpdateFlag() == 1) {
                        return true;
                    }
                }
                return false;
            }
        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    Constants.WRITE_EXTERNAL_STORAGE_TASK_CODE);
        } else {
            try {
                downloadApk(update);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }

    /**
     * 下载apk
     */
    private String filePath;

    private void downloadApk(final VersionInfo update) {
        filePath = SdcardUtils.sdPath + "pickle_work/pw_app_" + update.getVersionName() + ".apk";
        File file = new File(filePath);
        DownInfo downInfo = new DownInfo(PWApplication.getRequestUrl() + update.getDownUrl());
        downInfo.setSavePath(file.getAbsolutePath());
        downInfo.setState(DownState.START);
        downInfo.setListener(httpDownOnNextListener);

        HttpDownManager.getInstance().startDown(downInfo);
    }

    /**
     * 下载监听
     */
    private HttpDownOnNextListener httpDownOnNextListener = new HttpDownOnNextListener<DownInfo>() {
        @Override
        public void onNext(DownInfo downInfo) {
            if (dialog != null) {
                dialog.dismiss();
            }
            installApk();
            handler.sendEmptyMessage(HANDLER_CODE);
        }

        @Override
        public void updateProgress(long readLength, long countLength) {
            updateProgressUi(readLength, countLength);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            e.printStackTrace();
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    };

    /**
     * 更新进度条
     */

    private void updateProgressUi(long finishedSize, long totalSize) {
        finishedSize = Math.min(finishedSize, totalSize);

        Object[] arrobject = new Object[]{StringUtils.formatSize(finishedSize), StringUtils.formatSize(totalSize)};
        content.setText("正在下载" + arrobject[0] + "/" + arrobject[1]);
        progressBarDownload.setProgress(InputUtils.getPercentage(finishedSize, totalSize));
    }

    /**
     * 安装apk
     */

    private void installApk() {
        File file = new File(filePath);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //判读版本是否在7.0以上
            // 参数1 上下文, 参数2 Provider主机地址 和清单配置文件中保持一致
            // 参数2 = android:authorities="应用包名.fileprovider"属性值
            // 参数3 = 上一步中共享的apk文件
            Uri apkUri = FileProvider.getUriForFile(getBaseContext(), "com.pickcle.picklework.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }


    /**
     * 选择结果接收
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == Constants.WRITE_EXTERNAL_STORAGE_TASK_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                downloadApk(versionInfo);
            } else {
                ToastUtil.showToast(this, R.string.no_use_prompt_text);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void noneGoEvent(EmptyEvent emptyEvent) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            finish();

            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);

    }

}
