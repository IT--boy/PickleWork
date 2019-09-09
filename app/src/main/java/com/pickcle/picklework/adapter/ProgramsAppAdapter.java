package com.pickcle.picklework.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pickcle.picklework.R;
import com.pickcle.picklework.model.bean.AppInfor;
import com.pickcle.picklework.ui.ProgramsActivity;
import com.pickcle.picklework.util.ViewHolder;

import java.util.List;

public class ProgramsAppAdapter extends BaseAdapter {
    private ProgramsActivity context;
    private List<AppInfor> appInforList;
    private LayoutInflater inflater;

    public ProgramsAppAdapter(ProgramsActivity context, List<AppInfor> appInforList) {
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
            view = inflater.inflate(R.layout.activity_programs_app_item_layout, null);
        }
        TextView title = ViewHolder.get(view, R.id.title);
        TextView titleSub = ViewHolder.get(view, R.id.titleSub);
        ImageView checkbox = ViewHolder.get(view, R.id.checkbox);
        TextView btnEdit = ViewHolder.get(view, R.id.btnEdit);
        View line = ViewHolder.get(view, R.id.line);


        AppInfor appInfor = appInforList.get(i);
        title.setText("项目名称：" + appInfor.getAppName());
        titleSub.setText("执行次数：" + appInfor.getAppTimes() + "次");

        if (appInfor.isCheckFlag()) {
            checkbox.setSelected(true);
        } else {
            checkbox.setSelected(false);
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.showAppTimesDialog(appInfor);
            }
        });
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (appInfor.isCheckFlag()) {
                    appInfor.setCheckFlag(false);
                } else {
                    appInfor.setCheckFlag(true);
                }
                if (isOneCheck()) {
                    context.getBtnFinish().setText("完成");
                } else {
                    context.getBtnFinish().setText("恢复默认");
                }
                notifyDataSetChanged();
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

    public List<AppInfor> getAppInforList() {
        return appInforList;
    }

    private boolean isOneCheck() {
        if (appInforList != null && appInforList.size() > 0) {
            for (AppInfor appInfor : appInforList) {
                if (appInfor.isCheckFlag()) {
                    return true;
                }
            }
        }
        return false;
    }
}
