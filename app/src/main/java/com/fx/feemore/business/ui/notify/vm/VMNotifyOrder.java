package com.fx.feemore.business.ui.notify.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.NotifyBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:订单通知ViewModel
 * author: frj
 * modify date: 2019/1/17
 */
public class VMNotifyOrder extends ViewModel
{
    private DataSource dataSource;

    public int currPage;

    public MutableLiveData<List<NotifyBean>> notifyBeansData = new MutableLiveData<>();
    public MutableLiveData<HttpFailBean> failData = new MutableLiveData<>();

    @Inject
    public VMNotifyOrder(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取通知列表信息
     *
     * @param page     页码
     * @param pageSize 页数据大小
     */
    public void getData(int page, int pageSize)
    {
        dataSource.getNotifyList(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), page, pageSize).subscribe(new ApiCallback<List<NotifyBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                failData.setValue(httpFail);
            }

            @Override
            public void onNext(List<NotifyBean> notifyBeans)
            {
                currPage = page;
                notifyBeansData.setValue(notifyBeans);
            }
        });
    }

}
