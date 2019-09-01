package com.pickcle.picklework.ui;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.justcan.library.dialog.CBDialogBuilder;
import com.justcan.library.utils.common.AppUtil;
import com.justcan.library.utils.common.InputUtils;
import com.justcan.library.utils.common.StringUtils;
import com.justcan.library.utils.common.ToastUtil;
import com.pickcle.picklework.PWApplication;
import com.pickcle.picklework.Pref;
import com.pickcle.picklework.R;
import com.pickcle.picklework.SettingsActivity;
import com.pickcle.picklework.adapter.MainAppAdapter;
import com.pickcle.picklework.adapter.MainBannerAdapter;
import com.pickcle.picklework.constant.Constants;
import com.pickcle.picklework.http.download.DownInfo;
import com.pickcle.picklework.http.download.DownState;
import com.pickcle.picklework.http.download.HttpDownManager;
import com.pickcle.picklework.http.listener.HttpDownOnNextListener;
import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.pickcle.picklework.model.bean.AppInfor;
import com.pickcle.picklework.model.bean.BannerInfo;
import com.pickcle.picklework.model.bean.DeviceRegisterResponse;
import com.pickcle.picklework.model.bean.MainResponse;
import com.pickcle.picklework.model.http.api.AppDeviceRegisterApi;
import com.pickcle.picklework.model.http.api.AppMainApi;
import com.pickcle.picklework.model.http.request.DeviceRegisterRequest;
import com.pickcle.picklework.model.http.request.MainRequest;
import com.pickcle.picklework.util.SdcardUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主界面
 */
public class MainActivity extends BaseTitleCompatActivity {
    @BindView(R.id.banner)
    MZBannerView bannerView;
    @BindView(R.id.progressLoad)
    ProgressBar progressLoad;
    @BindView(R.id.noDataLayout)
    TextView noDataLayout;
    @BindView(R.id.errorLayout)
    LinearLayout errorLayout;
    @BindView(R.id.contentLayout)
    LinearLayout contentLayout;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.listView)
    ListView listView;

    private MainAppAdapter adapter;
    private List<BannerInfo> bannerInfoList = new ArrayList<>();

    @BindView(R.id.btnRegister)
    TextView btnRegister;
    @BindView(R.id.gotoWork)
    TextView gotoWork;

    @BindView(R.id.btnRightImg)
    ImageView btnSet;

    @BindView(R.id.bottomLayout)
    FrameLayout bottomLayout;

    private MainResponse mainResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
        setData();
    }

    private void initData() {
        loadAppList();
    }

    private void initView() {
        setTitleText("咸菜打工");
        btnSet.setVisibility(View.VISIBLE);
        btnSet.setImageResource(R.drawable.icon_set);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadAppList();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadMoreAppList();
            }
        });

    }

    private void setData() {

    }

    @Override
    public void onPause() {
        super.onPause();
        bannerView.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        bannerView.start();//开始轮播
    }

    @OnClick(R.id.btnRetryLoad)
    public void btnRetryLoad(View view) {
        loadAppList();
    }

    private MainRequest request;

    private void loadAppList() {
        request = new MainRequest();
        request.setDeviceNo(AppUtil.getDeviceNo(this));

        AppMainApi api = new AppMainApi(new HttpOnNextListener<MainResponse>() {
            @Override
            public void onStart() {
                super.onStart();
                if (mainResponse == null) {
                    contentLayout.setVisibility(View.GONE);
                    errorLayout.setVisibility(View.GONE);
                    progressLoad.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                refreshLayout.finishRefresh();
                progressLoad.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(MainResponse model) {
                mainResponse = model;
                if (model != null && model.getTotalSize() != 0) {
                    contentLayout.setVisibility(View.VISIBLE);
                    bottomLayout.setVisibility(View.VISIBLE);
                    if (!StringUtils.isEmpty(Pref.getCode())) {
                        btnRegister.setVisibility(View.GONE);
                        gotoWork.setVisibility(View.VISIBLE);
                    } else {
                        btnRegister.setVisibility(View.VISIBLE);
                        gotoWork.setVisibility(View.GONE);
                    }
                    setData(model);
                } else {
                    refreshLayout.setVisibility(View.GONE);
                    noDataLayout.setVisibility(View.VISIBLE);
                    bottomLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onError(String message) {
                super.onError(message);
                if (mainResponse == null) {
                    errorLayout.setVisibility(View.VISIBLE);
                }
            }
        }, this);
        api.addRequstBody(request);
        httpManager.doHttpDealF(api);
    }

    private void setData(MainResponse model) {
        request.setTotalSize(model.getTotalSize());
        request.setTotalPage();
        if (request.getPageNum() == request.getTotalPage()) {
            refreshLayout.setEnableLoadMore(false);
        } else {
            refreshLayout.setEnableLoadMore(true);
        }
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

        adapter = new MainAppAdapter(this, model.getDataList());
        listView.setAdapter(adapter);
    }

    private void loadMoreAppList() {
        request.setPageNum(request.getPageNum() + 1);
        AppMainApi api = new AppMainApi(new HttpOnNextListener<MainResponse>() {
            @Override
            public void onCompleted() {
                super.onCompleted();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onSuccess(MainResponse model) {
                if (model != null && model.getDataList() != null && model.getDataList().size() > 0) {
                    setMoreData(model);
                }
            }

        }, this);

        api.addRequstBody(request);
        httpManager.doHttpDealF(api);
    }

    private void setMoreData(MainResponse model) {
        if (request.getPageNum() == request.getTotalPage()) {
            refreshLayout.setEnableLoadMore(false);
        } else {
            refreshLayout.setEnableLoadMore(true);
        }
        adapter.loadMoreData(model.getDataList());
        request.setTotalSize(model.getTotalSize());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_layout;
    }

    /**
     * 显示软件下载对话框
     */
    private ProgressBar progressBarDownload;
    private TextView content;
    private Dialog dialog;
    private AppInfor appInfor;

    public void showDownloadDialog(final AppInfor appInfor) {
        this.appInfor = appInfor;
        final CBDialogBuilder builder = new CBDialogBuilder(this);
        builder.setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM);
        builder.setTouchOutSideCancelable(false);

        View customView = LayoutInflater.from(PWApplication.getContext()).inflate(R.layout.dialog_content_progress_bar_layout, null);

        content = customView.findViewById(R.id.content);
        content.setText("正在下载");

        progressBarDownload = customView.findViewById(R.id.progressBarDownload);

        builder.setView(customView);
        dialog = builder.create();
        dialog.show();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
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
                downloadApk(appInfor);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }

    /**
     * 下载apk
     */
    private String filePath;

    private void downloadApk(final AppInfor appInfor) {
        filePath = SdcardUtils.sdPath + "app_run/" + appInfor.getAppName() + ".apk";
        File file = new File(filePath);
        DownInfo downInfo = null;
        if (!StringUtils.isEmpty(appInfor.getAppDownUrlQuick())) {
            downInfo = new DownInfo(appInfor.getAppDownUrlQuick());
        } else {
            downInfo = new DownInfo(PWApplication.getRequestUrl() + appInfor.getAppDownUrl());
        }
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
            if(adapter!=null){
                adapter.notifyDataSetChanged();

            }
            ToastUtil.showToast(getContext(), "下载成功");
            installApk(filePath);
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

    public void installApk(String filePath) {
        File file = new File(filePath);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //判读版本是否在7.0以上
            // 参数1 上下文, 参数2 Provider主机地址 和清单配置文件中保持一致
            // 参数2 = android:authorities="应用包名.fileprovider"属性值
            // 参数3 = 上一步中共享的apk文件
            Uri apkUri = FileProvider.getUriForFile(getContext(), "com.pickcle.picklework.fileprovider", file);
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
                downloadApk(appInfor);
            } else {
                ToastUtil.showToast(this, R.string.no_use_prompt_text);
            }
        }
    }

    @OnClick(R.id.btnRegister)
    public void btnRegister(View view) {
        showInputInvitationCodeDialog();
    }

    @OnClick(R.id.gotoWork)
    public void goWork(View view) {
        Intent gotoWork = new Intent(getContext(), MainWorkActivity.class);
        startActivity(gotoWork);
        finish();
    }

    @OnClick(R.id.btnRightImg)
    public void gotoSet(View view) {
        Intent gotoSet = new Intent(getContext(), SettingsActivity.class);
        startActivity(gotoSet);
    }

    private void showInputInvitationCodeDialog() {
        final CBDialogBuilder builder = new CBDialogBuilder(this, 1f);
        builder.setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM);
        builder.setTouchOutSideCancelable(true);
        builder.setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_BOTTOM);

        View customView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_input_invitationcode_layout, null);

        TextView btnFinish = customView.findViewById(R.id.btnFinish);
        ImageView btnClose = customView.findViewById(R.id.btnClose);

        final EditText feelContent = customView.findViewById(R.id.invitationCode);


        builder.setView(customView);
        final Dialog dialog = builder.create();
        dialog.show();

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(feelContent.getText().toString().trim())) {
                    ToastUtil.showToast(getContext(), "请输入邀请码");
                } else {
                    if (feelContent.getText().toString().length() == 8) {
                        loadDeviceRegister(feelContent.getText().toString(), dialog);
                    } else {
                        ToastUtil.showToast(getContext(), "输入邀请码长度有误");
                    }
                }
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void loadDeviceRegister(String invitationCode, final Dialog dialog) {
        DeviceRegisterRequest request = new DeviceRegisterRequest();
        request.setDeviceNo(AppUtil.getDeviceNo(this));
        request.setInvitationCode(invitationCode);


        final AppDeviceRegisterApi api = new AppDeviceRegisterApi(new HttpOnNextListener<DeviceRegisterResponse>() {

            @Override
            public void onSuccess(DeviceRegisterResponse model) {
                dialog.dismiss();
                if (model != null && !StringUtils.isEmpty(model.getInvitationCode())) {
                    loadAppList();
                    Pref.putCode(model.getInvitationCode());
                    btnRegister.setVisibility(View.GONE);
                    gotoWork.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void showMsg(String content) {
//                ToastUtils.showToast(getContext(), content);
            }
        }, this);
        api.setShowProgress(true);
        api.setLoadContent("验证中");
        api.addRequstBody(request);
        httpManager.doHttpDealF(api);

    }
}
