package com.pickcle.picklework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.pickcle.picklework.PWApplication;
import com.pickcle.picklework.R;
import com.pickcle.picklework.model.bean.BannerInfo;
import com.pickcle.picklework.util.PicUtil;
import com.zhouwei.mzbanner.holder.MZViewHolder;

public class MainBannerAdapter implements MZViewHolder<BannerInfo> {
    private ImageView pic;

    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main_banner_item_layout, null);
        pic = (ImageView) view.findViewById(R.id.pic);
        return view;
    }

    @Override
    public void onBind(Context context, int position, BannerInfo bannerInfo) {
        // 数据绑定
        PicUtil.display(pic, PWApplication.getRequestUrl() + bannerInfo.getPicUrl());
    }
}
