package com.fx.feemore.business.ui.order.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.ui.order.bean.CommentBean;
import com.fx.feemore.business.ui.order.bean.CommentListBean;
import com.hengxian.baselib.bean.HttpFailBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:订单评价ViewModel
 * author: frj
 * modify date: 2019/1/17
 */
public class VMOrderComment extends ViewModel {

    private DataSource dataSource;

    public int currPage = -1;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();

    public MutableLiveData<List<CommentBean>> resComments = new MutableLiveData<>();

    @Inject
    public VMOrderComment(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取评论列表
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     */
    public void getComments(String storeId, int page, int pageSize) {
        dataSource.getComments(storeId, page, pageSize).subscribe(new ApiCallback<CommentListBean>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(CommentListBean commentListBean) {
                currPage = page;
                resComments.setValue(commentListBean.getListComment());
            }
        });
    }


}
