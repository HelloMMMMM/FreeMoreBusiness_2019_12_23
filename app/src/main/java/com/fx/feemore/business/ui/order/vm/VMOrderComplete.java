package com.fx.feemore.business.ui.order.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:已完成订单详情ViewModel
 * author: frj
 * modify date: 2018/12/30
 */
public class VMOrderComplete extends ViewModel
{

    private DataSource dataSource;
    public int currPage;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();

    public MutableLiveData<List<OrderBean>> resOrderBean = new MutableLiveData<>();

    @Inject
    public VMOrderComplete(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取已完成订单数据
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     */
    public void getData(String storeId, int page, int pageSize)
    {
//        storeId="27b488293f5f02e8";
        dataSource.getFinishedOrder(storeId, page, pageSize).subscribe(new ApiCallback<List<OrderBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(List<OrderBean> orderBeans)
            {
                currPage = page;
                resOrderBean.setValue(orderBeans);
            }
        });
    }
}
