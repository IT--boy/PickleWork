package com.pickcle.picklework.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pickcle.picklework.PWApplication;
import com.pickcle.picklework.R;
import com.pickcle.picklework.model.bean.BannerInfo;
import com.pickcle.picklework.ui.BannerInfoActivity;
import com.pickcle.picklework.util.PicUtil;
import com.zhouwei.mzbanner.holder.MZViewHolder;

public class MainBannerAdapter implements MZViewHolder<BannerInfo> {
    private ImageView pic;
    private TextView title;

    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.activity_main_banner_item_layout, null);
        pic = (ImageView) view.findViewById(R.id.pic);
        title = view.findViewById(R.id.title);

        return view;
    }

    @Override
    public void onBind(Context context, int position, BannerInfo bannerInfo) {
        // 数据绑定
        title.setText(bannerInfo.getTitle());
        PicUtil.display(pic, PWApplication.getRequestUrl() + bannerInfo.getPicUrl());
        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoNext = new Intent(context, BannerInfoActivity.class);
                gotoNext.putExtra("data", bannerInfo);
                context.startActivity(gotoNext);
            }
        });
    }
}
