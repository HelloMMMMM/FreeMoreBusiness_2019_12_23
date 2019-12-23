package com.fx.feemore.business.ui.order.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.ui.order.bean.CommentBean;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import javax.inject.Inject;

/**
 * function:订单评论详情
 * author: frj
 * modify date: 2019/1/18
 */
public class VMCommentDetail extends ViewModel {

    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();

    public MutableLiveData<CommentBean> resCommentBean = new MutableLiveData<>();

    public MutableLiveData<ResponseBean> resResponse = new MutableLiveData<>();

    @Inject
    public VMCommentDetail(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 评论详情
     *
     * @param storeId   店铺id
     * @param commentId 评论id
     */
    public void getCommentInfo(String storeId, String commentId) {
        dataSource.getCommentInfo(storeId, commentId).subscribe(new ApiCallback<CommentBean>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(CommentBean commentBean) {
                resCommentBean.setValue(commentBean);
            }
        });
    }

    /**
     * 回复评论内容
     *
     * @param storeId   店铺id
     * @param commentId 评论id
     * @param text      回复内容
     */
    public void answerComment(String storeId, String commentId, String text) {
        dataSource.answerComment(storeId, commentId, text).subscribe(new ApiCallback<ResponseBean>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean) {
                resResponse.setValue(responseBean);
            }
        });
    }
}
