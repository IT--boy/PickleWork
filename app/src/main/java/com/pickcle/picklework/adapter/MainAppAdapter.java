package com.pickcle.picklework.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pickcle.picklework.PWApplication;
import com.pickcle.picklework.R;
import com.pickcle.picklework.model.bean.AppInfor;
import com.pickcle.picklework.ui.MainActivity;
import com.pickcle.picklework.util.AppUtil;
import com.pickcle.picklework.util.PicUtil;
import com.pickcle.picklework.util.SdcardUtils;
import com.pickcle.picklework.util.ViewHolder;

import java.io.File;
import java.util.List;

public class MainAppAdapter extends BaseAdapter {
    private MainActivity context;
    private List<AppInfor> appInforList;
    private LayoutInflater inflater;

    public MainAppAdapter(MainActivity context, List<AppInfor> appInforList) {
        this.context = context;
        this.appInforList = appInforList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return appInforList == null ? 0 : appInforList.size();
    }

    @Override
    public Object getItem(int i) {
        return appInforList == null ? null : appInforList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_main_app_item_layout, null);
        }
        ImageView pic = ViewHolder.get(view, R.id.pic);
        ImageView recommentFlag = ViewHolder.get(view, R.id.recommentFlag);
        TextView title = ViewHolder.get(view, R.id.title);
        TextView titleSub = ViewHolder.get(view, R.id.titleSub);
        TextView mark = ViewHolder.get(view, R.id.mark);
        TextView btnDownload = ViewHolder.get(view, R.id.btnDownload);
        TextView btnOpen = ViewHolder.get(view, R.id.btnOpen);
        View line = ViewHolder.get(view, R.id.line);


        AppInfor appInfor = appInforList.get(i);
        PicUtil.display(pic, PWApplication.getRequestUrl() + appInfor.getAppIcon());
        title.setText(appInfor.getAppName());
        titleSub.setText("可挣" + appInfor.getDayAmount() + "分/天，apk包大小" + appInfor.getAppSize() + "M");
        mark.setText(appInfor.getWithdrawRule());
        if (appInfor.getRecommendFlag() != null && appInfor.getRecommendFlag() == 1) {
            recommentFlag.setVisibility(View.VISIBLE);
        } else {
            recommentFlag.setVisibility(View.GONE);
        }
        String filePath = SdcardUtils.sdPath + "app_run/" + appInfor.getAppName() + ".apk";

        if (AppUtil.checkAppInstalled(context, appInfor.getAppPackageName())) {
            btnOpen.setVisibility(View.GONE);
            btnDownload.setVisibility(View.GONE);
        } else {
            if (new File(filePath).exists()) {
                btnOpen.setVisibility(View.VISIBLE);
                btnDownload.setVisibility(View.GONE);
            } else {
                btnOpen.setVisibility(View.GONE);
                btnDownload.setVisibility(View.VISIBLE);
            }

        }
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.showDownloadDialog(appInfor);
            }
        });
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = SdcardUtils.sdPath + "/app_run";
                AppUtil.openAssignFolder(context, path);
            }
        });
        if (i == appInforList.size() - 1) {
            line.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
        }
        return view;
    }

    public void loadMoreData(List<AppInfor> dataList) {
        if (appInforList != null && dataList != null) {
            appInforList.addAll(dataList);
            notifyDataSetChanged();
        }
    }
}
