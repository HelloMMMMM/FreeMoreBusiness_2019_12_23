package com.fx.feemore.business.ui.order.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.ConsumeRes;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import javax.inject.Inject;

/**
 * function:订单核销界面
 * author: frj
 * modify date: 2019/2/26
 */
public class VMOrderWriteOff extends ViewModel
{

    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();
    public MutableLiveData<ConsumeRes> resConsume = new MutableLiveData<>();
    public MutableLiveData<ResponseBean> resSucc = new MutableLiveData<>();

    @Inject
    public VMOrderWriteOff(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取订单核销信息
     *
     * @param url 订单核销url
     */
    public void writeOffConsume(String url)
    {
        dataSource.writeOffConsume(url, AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID()).subscribe(new ApiCallback<ConsumeRes>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(ConsumeRes consumeBean)
            {
                resConsume.setValue(consumeBean);
            }
        });
    }

    /**
     * 订单核销
     *
     * @param url     核销确认链接
     * @param numbers 当次使用次数
     */
    public void orderWriteOff(String url, String numbers)
    {
        dataSource.orderWriteOff(url, numbers).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                resSucc.setValue(responseBean);
            }
        });
    }
}
