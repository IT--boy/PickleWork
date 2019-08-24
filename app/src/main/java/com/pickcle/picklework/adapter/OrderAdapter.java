package com.pickcle.picklework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pickcle.picklework.R;
import com.pickcle.picklework.model.bean.OrderInfo;
import com.pickcle.picklework.util.ViewHolder;

import java.util.List;

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<OrderInfo> orderInfoList;
    private LayoutInflater inflater;

    public OrderAdapter(Context context, List<OrderInfo> orderInfoList) {
        this.context = context;
        this.orderInfoList = orderInfoList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return orderInfoList == null ? 0 : orderInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return orderInfoList == null ? null : orderInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_order_list_item_layout, null);
        }
        TextView orderNo = ViewHolder.get(view, R.id.orderNo);
        TextView date = ViewHolder.get(view, R.id.date);
        TextView amount = ViewHolder.get(view, R.id.amount);
        TextView type = ViewHolder.get(view, R.id.type);
        TextView state = ViewHolder.get(view, R.id.state);
        View line = ViewHolder.get(view, R.id.line);
        OrderInfo orderInfo = orderInfoList.get(i);
        orderNo.setText("订单号：" + orderInfo.getOrderNo());
        date.setText(orderInfo.getCreateTime());
        amount.setText("金额：" + orderInfo.getAmount() + "元");
        if (orderInfo.getOrderType() != null) {
            if (orderInfo.getOrderType() == 1) {
                type.setText("类型：打工");
            } else if (orderInfo.getOrderType() == 2) {
                type.setText("类型：学习");
            } else {
                type.setText("类型：异常");
            }
        } else {
            type.setText("类型：异常");
        }
        if (orderInfo.getState() != null) {
            if (orderInfo.getState() == 1) {
                state.setText("状态：待处理");
            } else if (orderInfo.getState() == 2) {
                state.setText("状态：处理成功");
            } else if (orderInfo.getState() == 2) {
                state.setText("状态：处理失败，" + orderInfo.getRemark());
            } else {
                state.setText("状态：异常");
            }
        } else {
            state.setText("状态：异常");
        }
        if (i == orderInfoList.size() - 1) {
            line.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
        }
        return view;
    }

    public void loadMoreData(List<OrderInfo> dataList) {
        if (orderInfoList != null && dataList != null) {
            orderInfoList.addAll(dataList);
            notifyDataSetChanged();
        }
    }
}
