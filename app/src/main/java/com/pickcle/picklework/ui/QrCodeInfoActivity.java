package com.pickcle.picklework.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.justcan.library.utils.common.AppUtil;
import com.justcan.library.utils.common.StringUtils;
import com.justcan.library.utils.common.ToastUtil;
import com.pickcle.picklework.PWApplication;
import com.pickcle.picklework.R;
import com.pickcle.picklework.http.listener.HttpOnNextListener;
import com.pickcle.picklework.model.bean.QrCodeInfoResponse;
import com.pickcle.picklework.model.http.api.AppOrderSubmitApi;
import com.pickcle.picklework.model.http.api.AppQrInfoApi;
import com.pickcle.picklework.model.http.request.OrderSubmitRequest;
import com.pickcle.picklework.model.http.request.QrCodeInfoRequest;
import com.pickcle.picklework.util.PicUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 充值界面
 */
public class QrCodeInfoActivity extends BaseTitleCompatActivity {
    @BindView(R.id.progressLoad)
    ProgressBar progressLoad;
    @BindView(R.id.noDataLayout)
    TextView noDataLayout;
    @BindView(R.id.errorLayout)
    LinearLayout errorLayout;
    @BindView(R.id.contentLayout)
    FrameLayout contentLayout;

    private int type;

    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.pic)
    ImageView pic;
    @BindView(R.id.remark)
    TextView remark;
    @BindView(R.id.payUserName)
    EditText payUserName;

    private QrCodeInfoResponse response;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
        setData();
    }

    private void initData() {
        type = getIntent().getIntExtra("type", 1);
    }

    private void initView() {
        setBackView();
        if (type == 1) {
            setTitleText("打工月卡充值");
        } else if (type == 2) {
            setTitleText("学习月卡充值");
        } else {
            setTitleText("类型异常");
        }

    }

    private void setData() {
        loadQrcodeInfo();
    }

    //确认支付
    @OnClick(R.id.btnConfirm)
    public void btnConfirm(View view) {
        if (!StringUtils.isEmpty(payUserName.getText().toString())) {
            if (response != null && response.getQrCodeId() != null) {
                loadOrderSubmit(payUserName.getText().toString(), response.getQrCodeId());
            } else {
                ToastUtil.showToast(getContext(), "参数异常");
            }
        } else {
            ToastUtil.showToast(getContext(), "请输入付款账号的名称");
        }
    }

    private void loadQrcodeInfo() {
        QrCodeInfoRequest request = new QrCodeInfoRequest();
        request.setDeviceNo(AppUtil.getDeviceNo(this));
        request.setPaymentTerm(1);
        request.setQrCodeType(type);

        AppQrInfoApi api = new AppQrInfoApi(new HttpOnNextListener<QrCodeInfoResponse>() {
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
            public void onSuccess(QrCodeInfoResponse model) {
                if (model != null) {
                    response = model;
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

    private void setData(QrCodeInfoResponse model) {
        if (model.getAmount() != null) {
            amount.setText("支付金额：" + model.getAmount() + "元");
        } else {
            amount.setText("支付金额：" + 0 + "元");
        }
        PicUtil.display(pic, PWApplication.getRequestUrl() + model.getQrCodeUrl());
        if (!StringUtils.isEmpty(model.getRemark())) {
            remark.setText(model.getRemark());
        } else {
            remark.setText("1、目前仅支持微信支付，截屏保存到手机\n" +
                    "2、打开微信，扫一扫本地图片\n" +
                    "3、底下填写付款的微信昵称，并点击确认支付\n" +
                    "4、确认成功后，将再1~2天确认通过\n" +
                    "5、有疑问请加QQ：203744680咨询");
        }
    }

    private void loadOrderSubmit(String payUserName, Long qrId) {
        OrderSubmitRequest request = new OrderSubmitRequest();
        request.setDeviceNo(AppUtil.getDeviceNo(this));
        request.setPayUserName(payUserName);
        request.setQrCodeId(qrId);


        AppOrderSubmitApi api = new AppOrderSubmitApi(new HttpOnNextListener<String>() {

            @Override
            public void onSuccess(String model) {
                ToastUtil.showToast(getContext(), "提交成功");
                Intent gotoOrder = new Intent(getContext(), OrderListActivity.class);
                startActivity(gotoOrder);
                finish();
            }

            @Override
            public void showMsg(String content) {
            }
        }, this);
        api.setShowProgress(true);
        api.setLoadContent("提交中");
        api.addRequstBody(request);
        httpManager.doHttpDealF(api);

    }
    @OnClick(R.id.btnRetryLoad)
    public void btnRetryLoad(View view) {
        loadQrcodeInfo();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_qrcode_info_layout;
    }
}
