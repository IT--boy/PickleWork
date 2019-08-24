package com.pickcle.picklework.model.http.request;

/**
 * 列表请求实体
 */
public class ListRequest extends BaseRequest {
    /**
     * 总记录数
     */
    private Integer totalSize;
    /**
     * 当前页码
     */
    private Integer pageNum = 1;
    /**
     * 页码
     */
    private Integer pageSize = 20;
    /**
     * 总页数
     */
    private Integer totalPage;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
    public int setTotalPage() {
        if (pageSize != 0) {
            return this.totalPage = (totalSize - 1) / pageSize + 1;
        }
        return 0;
    }
}
