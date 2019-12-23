package com.fx.feemore.business.ui.order.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.OrderRefundBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import javax.inject.Inject;


/**
 * function:订单维权详情
 * author: frj
 * modify date: 2019/1/18
 */
public class VMRightsProtectionDetail extends ViewModel
{

    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();
    public MutableLiveData<OrderRefundBean> resBean = new MutableLiveData<>();
    public MutableLiveData<ResponseBean> resResponseBean = new MutableLiveData<>();

    @Inject
    public VMRightsProtectionDetail(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取维权订单详情
     *
     * @param storeId 店铺id
     * @param orderId 订单id
     */
    public void getOrderDetail(String storeId, String orderId)
    {
        dataSource.getOrderRightProtectionInfo(storeId, orderId).subscribe(new ApiCallback<OrderRefundBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(OrderRefundBean orderRefundBean)
            {
                resBean.setValue(orderRefundBean);
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
    public void refusedRefund(String storeId, String orderId,String reason)
    {
        dataSource.refusedRightProtectionRefund(storeId, orderId,reason).subscribe(new ApiCallback<ResponseBean>()
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
