package com.pickcle.picklework.model.bean;

import java.util.List;

public class OrderListResponse {
    private Integer totalSize;
    //订单集合
    private List<OrderInfo> dataList;

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public List<OrderInfo> getDataList() {
        return dataList;
    }

    public void setDataList(List<OrderInfo> dataList) {
        this.dataList = dataList;
    }
}
