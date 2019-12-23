package com.fx.feemore.business.ui.order.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.OrderRefundBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:维权订单详情
 * author: frj
 * modify date: 2018/12/30
 */
public class VMOrderRightsProtection extends ViewModel
{
    private DataSource dataSource;
    public int currPage;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();

    public MutableLiveData<List<OrderRefundBean>> resOrderRefundBean = new MutableLiveData<>();

    public MutableLiveData<ResponseBean> resResponseBean = new MutableLiveData<>();

    @Inject
    public VMOrderRightsProtection(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取维权订单列表
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     */
    public void getOrders(String storeId, int page, int pageSize)
    {
        dataSource.getOrderRightProtections(storeId, page, pageSize).subscribe(new ApiCallback<List<OrderRefundBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(List<OrderRefundBean> orderRefundBeans)
            {
                currPage = page;
                resOrderRefundBean.setValue(orderRefundBeans);
            }
        });
    }

    /**
     * 同意维权订单退款
     *
     * @param storeId 店铺id
     * @param orderId 订单id
     */
    public void agreeRefund(String storeId, String orderId)
    {
        dataSource.agreeRightProtectionRefund(storeId, orderId).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                resResponseBean.setValue(responseBean);
            }
        });
    }

    /**
     * 驳回维权订单退款
     *
     * @param storeId 店铺id
     * @param orderId 订单id
     */
    public void refusedRefund(String storeId, String orderId, String reason)
    {
        dataSource.refusedRightProtectionRefund(storeId, orderId, reason).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                resResponseBean.setValue(responseBean);
            }
        });
    }
}
