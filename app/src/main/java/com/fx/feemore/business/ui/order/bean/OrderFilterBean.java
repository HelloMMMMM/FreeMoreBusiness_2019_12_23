package com.fx.feemore.business.ui.order.bean;

public class OrderFilterBean {

    private String filter;
    private int filterFlag;

    public OrderFilterBean(String filter, int filterFlag) {
        this.filter = filter;
        this.filterFlag = filterFlag;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getFilterFlag() {
        return filterFlag;
    }

    public void setFilterFlag(int filterFlag) {
        this.filterFlag = filterFlag;
    }
}
