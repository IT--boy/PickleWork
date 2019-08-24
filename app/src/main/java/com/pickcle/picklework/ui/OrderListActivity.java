package com.pickcle.picklework.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.justcan.library.utils.common.AppUtil;
import com.pickcle.picklework.R;
import com.pickcle.picklework.adapter.OrderAdapter;
import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.pickcle.picklework.model.bean.OrderListResponse;
import com.pickcle.picklework.model.http.api.AppOrderListApi;
import com.pickcle.picklework.model.http.request.ListRequest;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 订单列表界面
 */
public class OrderListActivity extends BaseTitleCompatActivity {
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

    private OrderAdapter adapter;

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
        setTitleText("月卡充值记录");

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                loadOrderList();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadMoreOrderList();
            }
        });

    }

    private void setData() {
        loadOrderList();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_list_layout;
    }

    @OnClick(R.id.btnRetryLoad)
    public void btnRetryLoad(View view) {
        loadOrderList();
    }

    private ListRequest request;

    private void loadOrderList() {
        request = new ListRequest();
        request.setDeviceNo(AppUtil.getDeviceNo(this));

        AppOrderListApi api = new AppOrderListApi(new HttpOnNextListener<OrderListResponse>() {
            @Override
            public void onStart() {
                super.onStart();
                refreshLayout.setVisibility(View.GONE);
                errorLayout.setVisibility(View.GONE);
                progressLoad.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                refreshLayout.finishRefresh();
                progressLoad.setVisibility(View.GONE);
            }

            @Override
            public void onSuccess(OrderListResponse model) {
                if (model != null && model.getTotalSize() != 0) {
                    refreshLayout.setVisibility(View.VISIBLE);
                    setData(model);
                } else {
                    refreshLayout.setVisibility(View.GONE);
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

    private void setData(OrderListResponse model) {
        request.setTotalSize(model.getTotalSize());
        request.setTotalPage();
        if (request.getPageNum() == request.getTotalPage()) {
            refreshLayout.setEnableLoadMore(false);
        } else {
            refreshLayout.setEnableLoadMore(true);
        }
        adapter = new OrderAdapter(this, model.getDataList());
        listView.setAdapter(adapter);
    }

    private void loadMoreOrderList() {
        request.setPageNum(request.getPageNum() + 1);
        AppOrderListApi api = new AppOrderListApi(new HttpOnNextListener<OrderListResponse>() {
            @Override
            public void onCompleted() {
                super.onCompleted();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onSuccess(OrderListResponse model) {
                if (model != null && model.getDataList() != null && model.getDataList().size() > 0) {
                    setMoreData(model);
                }
            }

        }, this);

        api.addRequstBody(request);
        httpManager.doHttpDealF(api);
    }

    private void setMoreData(OrderListResponse model) {
        if (request.getPageNum() == request.getTotalPage()) {
            refreshLayout.setEnableLoadMore(false);
        } else {
            refreshLayout.setEnableLoadMore(true);
        }
        adapter.loadMoreData(model.getDataList());
        request.setTotalSize(model.getTotalSize());
    }
}
