package com.pickcle.picklework.ui;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.justcan.library.dialog.CBDialogBuilder;
import com.justcan.library.utils.common.AppUtil;
import com.justcan.library.utils.common.InputUtils;
import com.justcan.library.utils.common.PermissionPageUtils;
import com.justcan.library.utils.common.StringUtils;
import com.justcan.library.utils.common.ToastUtil;
import com.pickcle.picklework.PWApplication;
import com.pickcle.picklework.Pref;
import com.pickcle.picklework.R;
import com.pickcle.picklework.SettingsActivity;
import com.pickcle.picklework.adapter.MainBannerAdapter;
import com.pickcle.picklework.autojs.script.ScriptFile;
import com.pickcle.picklework.autojs.script.Scripts;
import com.pickcle.picklework.http.HttpManager;
import com.pickcle.picklework.http.download.DownInfo;
import com.pickcle.picklework.http.download.DownState;
import com.pickcle.picklework.http.download.HttpDownManager;
import com.pickcle.picklework.http.listener.HttpDownOnNextListener;
import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.pickcle.picklework.model.bean.BannerListResponse;
import com.pickcle.picklework.model.bean.DeviceInfoResponse;
import com.pickcle.picklework.model.bean.DeviceInfor;
import com.pickcle.picklework.model.bean.VersionInfo;
import com.pickcle.picklework.model.bean.VersionResponse;
import com.pickcle.picklework.model.http.api.AppBannerListApi;
import com.pickcle.picklework.model.http.api.AppDeviceInfoApi;
import com.pickcle.picklework.model.http.api.AppVersionApi;
import com.pickcle.picklework.model.http.request.BaseRequest;
import com.pickcle.picklework.model.http.request.VersionRequest;
import com.pickcle.picklework.util.SdcardUtils;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主界面
 */
public class MainWorkActivity extends BaseTitleCompatActivity {
    public static final Class<JoinActivity> CLS = JoinActivity.class;
    @BindView(R.id.banner)
    MZBannerView bannerView;
    @BindView(R.id.progressLoad)
    ProgressBar progressLoad;
    @BindView(R.id.noDataLayout)
    TextView noDataLayout;
    @BindView(R.id.errorLayout)
    LinearLayout errorLayout;
    @BindView(R.id.contentLayout)
    FrameLayout contentLayout;

    @BindView(R.id.btnRightImg)
    ImageView btnSet;
    @BindView(R.id.btnLeftImg)
    ImageView gotoApp;
    //邀请码
    @BindView(R.id.invitationCode)
    TextView invitationCode;

    @BindView(R.id.workMLayout)
    RelativeLayout workMLayout;
    @BindView(R.id.studyMLayout)
    RelativeLayout studyMLayout;

    @BindView(R.id.workLayout)
    LinearLayout workLayout;
    @BindView(R.id.btnStartWork)
    TextView btnStartWork;
    @BindView(R.id.btnStopWork)
    TextView btnStopWork;
    @BindView(R.id.workDate)
    TextView workDate;
    @BindView(R.id.studyLayout)
    LinearLayout studyLayout;
    @BindView(R.id.btnStartStudy)
    TextView btnStartStudy;
    @BindView(R.id.btnStopStudy)
    TextView btnStopStudy;
    @BindView(R.id.studyDate)
    TextView studyDate;

    @BindView(R.id.bottomLayout)
    LinearLayout bottomLayout;
    @BindView(R.id.topLayout)
    LinearLayout topLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
        setData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.deviceInfor != null) {
            loadDeviceInfo();
        }
    }

    private void initData() {

    }

    private void initView() {
        setTitleText("咸菜打工");
        btnSet.setVisibility(View.VISIBLE);
        btnSet.setImageResource(R.drawable.icon_set);
        gotoApp.setVisibility(View.VISIBLE);
        gotoApp.setImageResource(R.drawable.icon_home);
    }

    private void setData() {
        loadBannerList();
    }

    private void loadBannerList() {
        BaseRequest request = new BaseRequest();
        request.setDeviceNo(AppUtil.getDeviceNo(this));

        AppBannerListApi api = new AppBannerListApi(new HttpOnNextListener<BannerListResponse>() {
            @Override
            public void onStart() {
                super.onStart();
                contentLayout.setVisibility(View.GONE);
                errorLayout.setVisibility(View.GONE);
                progressLoad.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                progressLoad.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(BannerListResponse model) {
                if (model != null) {
                    contentLayout.setVisibility(View.VISIBLE);
                    setData(model);
                } else {
                    noDataLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(String message) {
                super.onError(message);
                errorLayout.setVisibility(View.VISIBLE);
            }
        }, this);
        api.addRequstBody(request);
        httpManager.doHttpDealF(api);
    }

    private DeviceInfor deviceInfor;

    private void setData(BannerListResponse model) {
        if (model.getBannerList() != null && model.getBannerList().size() > 0) {
            bannerView.setVisibility(View.VISIBLE);
            bannerView.setPages(model.getBannerList(), new MZHolderCreator<MainBannerAdapter>() {
                @Override
                public MainBannerAdapter createViewHolder() {
                    return new MainBannerAdapter();
                }
            });
        } else {
            bannerView.setVisibility(View.GONE);
        }

        DeviceInfor deviceInfor = model.getDeviceInfor();
        if (deviceInfor != null) {
            this.deviceInfor = deviceInfor;
            if (deviceInfor.getWorkFlag() != null && deviceInfor.getWorkFlag() == 1) {
                workLayout.setVisibility(View.VISIBLE);
                btnStartWork.setVisibility(View.VISIBLE);
                btnStopWork.setVisibility(View.GONE);
            } else {
                workLayout.setVisibility(View.GONE);
            }
            if (deviceInfor.getStudyFlag() != null && deviceInfor.getStudyFlag() == 1) {
                studyLayout.setVisibility(View.VISIBLE);
                btnStartStudy.setVisibility(View.VISIBLE);
                btnStopStudy.setVisibility(View.GONE);
            } else {
                studyLayout.setVisibility(View.GONE);
            }
            if (deviceInfor.getType() != null && deviceInfor.getType() == 1) {
                workMLayout.setVisibility(View.VISIBLE);
                studyMLayout.setVisibility(View.GONE);
                studyLayout.setVisibility(View.GONE);
            } else {
                if (deviceInfor.getType() != null && deviceInfor.getType() == 2) {
                    workMLayout.setVisibility(View.GONE);
                    studyMLayout.setVisibility(View.VISIBLE);
                    workLayout.setVisibility(View.GONE);
                } else {
                    workMLayout.setVisibility(View.VISIBLE);
                    studyMLayout.setVisibility(View.VISIBLE);
                }
            }
            if (!StringUtils.isEmpty(deviceInfor.getWorkExpiryDate())) {
                workDate.setText("有效期：" + deviceInfor.getWorkExpiryDate());
            } else {
                workDate.setText("无效");
            }
            if (!StringUtils.isEmpty(deviceInfor.getStudyExpiryDate())) {
                studyDate.setText("有效期：" + deviceInfor.getStudyExpiryDate());
            } else {
                studyDate.setText("无效");
            }
            if (!StringUtils.isEmpty(deviceInfor.getInvitationCode())) {
                invitationCode.setText("邀请码：" + deviceInfor.getInvitationCode());
            } else {
                invitationCode.setText("邀请码：无");
            }
        } else {
            topLayout.setVisibility(View.GONE);
            bottomLayout.setVisibility(View.GONE);
        }
    }

    private void loadDeviceInfo() {
        BaseRequest request = new BaseRequest();
        request.setDeviceNo(AppUtil.getDeviceNo(this));

        AppDeviceInfoApi api = new AppDeviceInfoApi(new HttpOnNextListener<DeviceInfoResponse>() {

            @Override
            public void onSuccess(DeviceInfoResponse model) {
                if (model != null) {
                    setData(model);
                } else {
                }
            }

        }, this);
        api.addRequstBody(request);
        httpManager.doHttpDealF(api);
    }

    private void setData(DeviceInfoResponse model) {
        DeviceInfor deviceInfor = model.getDeviceInfor();
        if (deviceInfor != null) {
            this.deviceInfor = deviceInfor;
            if (deviceInfor.getWorkFlag() != null && deviceInfor.getWorkFlag() == 1) {
                workLayout.setVisibility(View.VISIBLE);
                btnStartWork.setVisibility(View.VISIBLE);
                btnStopWork.setVisibility(View.GONE);
            } else {
                workLayout.setVisibility(View.GONE);
            }
            if (deviceInfor.getStudyFlag() != null && deviceInfor.getStudyFlag() == 1) {
                studyLayout.setVisibility(View.VISIBLE);
                btnStartStudy.setVisibility(View.VISIBLE);
                btnStopStudy.setVisibility(View.GONE);
            } else {
                studyLayout.setVisibility(View.GONE);
            }
            if (deviceInfor.getType() != null && deviceInfor.getType() == 1) {
                workMLayout.setVisibility(View.VISIBLE);
                studyMLayout.setVisibility(View.GONE);
                studyLayout.setVisibility(View.GONE);
            } else {
                if (deviceInfor.getType() != null && deviceInfor.getType() == 2) {
                    workMLayout.setVisibility(View.GONE);
                    studyMLayout.setVisibility(View.VISIBLE);
                    workLayout.setVisibility(View.GONE);
                } else {
                    workMLayout.setVisibility(View.VISIBLE);
                    studyMLayout.setVisibility(View.VISIBLE);
                }

            }
            if (!StringUtils.isEmpty(deviceInfor.getWorkExpiryDate())) {
                workDate.setText("有效期：" + deviceInfor.getWorkExpiryDate());
            } else {
                workDate.setText("无效");
            }
            if (!StringUtils.isEmpty(deviceInfor.getStudyExpiryDate())) {
                studyDate.setText("有效期：" + deviceInfor.getStudyExpiryDate());
            } else {
                studyDate.setText("无效");
            }
            if (!StringUtils.isEmpty(deviceInfor.getInvitationCode())) {
                invitationCode.setText("邀请码：" + deviceInfor.getInvitationCode());
            } else {
                invitationCode.setText("邀请码：无");
            }
        } else {
            topLayout.setVisibility(View.GONE);
            bottomLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_work_layout;
    }

    @OnClick(R.id.btnRetryLoad)
    public void btnRetryLoad(View view) {
        loadBannerList();
    }

    @OnClick(R.id.btnRightImg)
    public void gotoSet(View view) {
        Intent gotoSet = new Intent(getContext(), SettingsActivity.class);
        startActivity(gotoSet);
    }

    @OnClick(R.id.btnLeftImg)
    public void gotoApp(View view) {
        Intent gotoApp = new Intent(getContext(), MainActivity.class);
        startActivity(gotoApp);
    }

    //复制
    @OnClick(R.id.btnCopy)
    public void btnCopy(View view) {
        if (deviceInfor != null && !StringUtils.isEmpty(deviceInfor.getInvitationCode())) {
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData mClipData = ClipData.newPlainText("Label", deviceInfor.getInvitationCode());
            cm.setPrimaryClip(mClipData);
            ToastUtil.showToast(getContext(), "复制成功");
        }

    }

    //订单列表
    @OnClick(R.id.gotoOrder)
    public void gotoOrder(View view) {
        Intent gotoOrder = new Intent(getContext(), OrderListActivity.class);
        startActivity(gotoOrder);
    }

    //打工充值
    @OnClick(R.id.gotoQrInfoWork)
    public void gotoQrInfoWork(View view) {
        Intent gotoQrInfoWork = new Intent(getContext(), QrCodeInfoActivity.class);
        gotoQrInfoWork.putExtra("type", 1);
        startActivity(gotoQrInfoWork);
    }

    //学习充值
    @OnClick(R.id.gotoQrInfoStudy)
    public void gotoQrInfoStudy(View view) {
        Intent gotoQrInfoStudy = new Intent(getContext(), QrCodeInfoActivity.class);
        gotoQrInfoStudy.putExtra("type", 2);
        startActivity(gotoQrInfoStudy);
    }

    //权限设置
    @OnClick(R.id.gotoPer)
    public void gotoPer(View view) {
        PermissionPageUtils.getIntence(getContext()).jumpPermissionPage();
    }

    //文字教程
    @OnClick(R.id.gotoStudy)
    public void gotoStudy(View view) {
        ToastUtil.showToast(getContext(),"敬请期待");
        /*Intent gotoStudy = new Intent(getContext(), CourseActivity.class);
        startActivity(gotoStudy);*/
    }

    //代理推广
    @OnClick(R.id.gotoJoin)
    public void gotoJoin(View view) {
        ToastUtil.showToast(getContext(),"敬请期待");
        /*Intent gotoJoin = new Intent(getContext(), CLS);
        startActivity(gotoJoin);*/
    }

    //开始打工
    @OnClick(R.id.btnStartWork)
    public void btnStartWork(View view) {
        loadUpdateInfo(2);

    }

    //停止打工
    @OnClick(R.id.btnStopWork)
    public void btnStopWork(View view) {

    }

    //开始学习
    @OnClick(R.id.btnStartStudy)
    public void btnStartStudy(View view) {
        loadUpdateInfo(3);
    }

    //停止学习
    @OnClick(R.id.btnStopStudy)
    public void btnStopStudy(View view) {

    }

    private void loadUpdateInfo(Integer type) {
        VersionRequest request = new VersionRequest();
        if (type == 2) {
            request.setVersionNo(Pref.getJsWorkVersionCode());
        } else if (type == 3) {
            request.setVersionNo(Pref.getJsStudyVersionCode());
        }
        request.setType(type);
        request.setDeviceNo(AppUtil.getDeviceNo(getBaseContext()));

        AppVersionApi api = new AppVersionApi(new HttpOnNextListener<VersionResponse>() {
            @Override
            public void onSuccess(VersionResponse model) {
                if (model != null) {
                    if (model.getDeviceInfor() != null) {
                        Pref.putCode(model.getDeviceInfor().getInvitationCode());
                    }
                    if (model.getVersionInfo() != null && !StringUtils.isEmpty(model.getVersionInfo().getDownUrl())) {
                        if (type == 2) {
                            Pref.putJsWorkVersionCode(model.getVersionInfo().getVersionNo());
                        } else if (type == 3) {
                            Pref.putJsStudyVersionCode(model.getVersionInfo().getVersionNo());
                        }
                        filePath = SdcardUtils.sdPath + "pickle_work/pw_js_" + model.getVersionInfo().getVersionName() + ".js";
                        File file = new File(filePath);
                        if (model.getVersionInfo().getUpdateFlag() == 1 || !file.exists()) {
                            showDownloadDialog(model.getVersionInfo());
                        } else {
                            runScript();
                        }
                    } else {
                        ToastUtil.showToast(getContext(), "数据异常");
                    }
                } else {
                    ToastUtil.showToast(getContext(), "数据异常");
                }

            }

            @Override
            public void onError(String message) {
            }
        }, this);
        api.setShowProgress(true);
        api.setLoadContent("加载中");
        api.addRequstBody(request);

        HttpManager.getInstance().doHttpDealF(api);
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
        downloadApk(update);

    }

    /**
     * 下载apk
     */
    private String filePath;

    private void downloadApk(final VersionInfo update) {
        filePath = SdcardUtils.sdPath + "pickle_work/pw_js_" + update.getVersionName() + ".js";
        File file = new File(filePath);
        DownInfo downInfo = new DownInfo(PWApplication.getRequestUrl() + update.getDownUrl());
        downInfo.setSavePath(file.getAbsolutePath());
        downInfo.setState(DownState.START);
        downInfo.setListener(httpDownOnNextListener);

        HttpDownManager.getInstance().startDown(downInfo);
    }

    private void runScript() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ScriptFile scriptFile = new ScriptFile(filePath);
                Scripts.run(scriptFile);
            }
        }).start();

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
            runScript();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            keyCode = KeyEvent.KEYCODE_HOME;
        }
        return super.onKeyDown(keyCode, event);
    }
}
