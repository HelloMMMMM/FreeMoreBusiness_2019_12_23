package com.fx.feemore.business.ui.order.bean;

import java.util.List;

public class CommentListBean {

    private int commenTotal;
    private List<CommentBean> listComment;

    public int getCommenTotal() {
        return commenTotal;
    }

    public void setCommenTotal(int commenTotal) {
        this.commenTotal = commenTotal;
    }

    public List<CommentBean> getListComment() {
        return listComment;
    }

    public void setListComment(List<CommentBean> listComment) {
        this.listComment = listComment;
    }
}
