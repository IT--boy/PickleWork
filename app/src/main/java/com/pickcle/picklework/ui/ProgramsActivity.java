package com.pickcle.picklework.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.justcan.library.dialog.CBDialogBuilder;
import com.justcan.library.utils.common.AppUtil;
import com.justcan.library.utils.common.StringUtils;
import com.justcan.library.utils.common.ToastUtil;
import com.pickcle.picklework.R;
import com.pickcle.picklework.adapter.ProgramsAppAdapter;
import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.pickcle.picklework.model.bean.AppInfor;
import com.pickcle.picklework.model.bean.AppInfosStorage;
import com.pickcle.picklework.model.bean.MainResponse;
import com.pickcle.picklework.model.http.api.AppMainApi;
import com.pickcle.picklework.model.http.request.MainRequest;
import com.pickcle.picklework.util.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stardust.autojs.core.storage.LocalStorage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 方案生成界面
 */
public class ProgramsActivity extends BaseTitleCompatActivity {
    @BindView(R.id.progressLoad)
    ProgressBar progressLoad;
    @BindView(R.id.noDataLayout)
    TextView noDataLayout;
    @BindView(R.id.errorLayout)
    LinearLayout errorLayout;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.listView)
    ListView listView;

    private ProgramsAppAdapter adapter;

    private List<AppInfor> appInforList;
    @BindView(R.id.bottomLayout)
    FrameLayout bottomLayout;
    @BindView(R.id.btnFinish)
    TextView btnFinish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
        setData();
    }

    private void initData() {
        btnFinish.setText("恢复默认");
        loadAppList();
    }

    private void initView() {
        setTitleText("自定义方案");
        setBackView();

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
    }

    @Override
    public void onResume() {
        super.onResume();
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
                if (appInforList == null) {
                    refreshLayout.setVisibility(View.GONE);
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
                if (model != null && model.getTotalSize() != 0) {
                    appInforList = model.getDataList();
                    bottomLayout.setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.VISIBLE);
                    setData(model);
                } else {
                    bottomLayout.setVisibility(View.GONE);
                    refreshLayout.setVisibility(View.GONE);
                    noDataLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(String message) {
                super.onError(message);
                if (appInforList == null) {
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
        adapter = new ProgramsAppAdapter(this, model.getDataList());
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
        return R.layout.activity_programs_layout;
    }


    public void showAppTimesDialog(AppInfor appInfor) {
        final CBDialogBuilder builder = new CBDialogBuilder(this, 1f);
        builder.setDialogAnimation(CBDialogBuilder.DIALOG_ANIM_SLID_BOTTOM);
        builder.setTouchOutSideCancelable(true);
        builder.setDialoglocation(CBDialogBuilder.DIALOG_LOCATION_BOTTOM);

        View customView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_input_times_layout, null);

        TextView btnFinish = customView.findViewById(R.id.btnFinish);
        ImageView btnClose = customView.findViewById(R.id.btnClose);

        final EditText invitationCode = customView.findViewById(R.id.invitationCode);
        invitationCode.setText(appInfor.getAppTimes() + "");

        builder.setView(customView);
        final Dialog dialog = builder.create();
        dialog.show();

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtils.isEmpty(invitationCode.getText().toString().trim())) {
                    ToastUtil.showToast(getContext(), "请输入执行次数");
                } else {
                    if (Integer.valueOf(invitationCode.getText().toString()) > 0) {
                        appInfor.setAppTimes(Integer.valueOf(invitationCode.getText().toString()));
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    } else {
                        ToastUtil.showToast(getContext(), "输入执行次数需大于0");
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

    public TextView getBtnFinish() {
        return btnFinish;
    }

    @OnClick(R.id.btnFinish)
    public void btnFinish(View view) {
        LocalStorage localStorage = new LocalStorage(getContext(), "pickle_work");
        if (btnFinish.getText().equals("完成")) {
            if (adapter.getAppInforList() != null && adapter.getAppInforList().size() > 0) {

                List<AppInfor> appInforList = new ArrayList<>();
                for (AppInfor appInfor : adapter.getAppInforList()) {

                    if (appInfor.isCheckFlag()) {
                        AppInfor appInforTemp = new AppInfor();
                        appInforTemp.setAppTimes(appInfor.getAppTimes());
                        appInforTemp.setAppShortName(appInfor.getAppShortName());

                        appInforList.add(appInforTemp);
                    }

                }
                AppInfosStorage appInfosStorage = new AppInfosStorage();
                appInfosStorage.setAppInfos(appInforList);
                localStorage.put("programs", JSON.toJSONString(appInfosStorage));
                LogUtil.e("---->", localStorage.getString("programs"));
                ToastUtil.showToast(getContext(), "自定义成功，请重新执行脚本");
            } else {
                localStorage.put("programs", "");
                ToastUtil.showToast(getContext(), "重置成功，请重新执行脚本");
            }
        } else {
            localStorage.put("programs", null);
            ToastUtil.showToast(getContext(), "重置成功，请重新执行脚本");
        }
        finish();

    }
}
