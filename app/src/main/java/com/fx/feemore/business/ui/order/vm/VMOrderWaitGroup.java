package com.fx.feemore.business.ui.order.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.bean.OrderRefundBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:订单待成团
 * author: frj
 * modify date: 2019/1/18
 */
public class VMOrderWaitGroup extends ViewModel
{

    private DataSource dataSource;
    public int currPage;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();

    public MutableLiveData<List<OrderBean>> resOrderBean = new MutableLiveData<>();

    @Inject
    public VMOrderWaitGroup(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取待成团订单数据
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     */
    public void getData(String storeId, int page, int pageSize)
    {
        dataSource.getWaitGroupOrder(storeId, page, pageSize).subscribe(new ApiCallback<List<OrderBean>>()
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
