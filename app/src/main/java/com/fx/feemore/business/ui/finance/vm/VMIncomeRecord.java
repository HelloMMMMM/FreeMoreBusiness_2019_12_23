package com.fx.feemore.business.ui.finance.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.RevenueRecordBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:收益记录ViewModel
 * author: frj
 * modify date: 2018/12/31
 */
public class VMIncomeRecord extends ViewModel
{

    private DataSource dataSource;
    //当前页码
    public int currPage;
    //开始时间
    public String startDate;

    public MutableLiveData<HttpFailBean> resHttpFail = new MutableLiveData<>();
    public MutableLiveData<List<RevenueRecordBean>> resRecords = new MutableLiveData<>();

    @Inject
    public VMIncomeRecord(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取数据
     *
     * @param page     页码
     * @param pageSize 页数据大小
     */
    public void getData(int page, int pageSize)
    {
        dataSource.getRevenueRecords(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), page, pageSize, startDate, null).subscribe(new ApiCallback<List<RevenueRecordBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(List<RevenueRecordBean> revenueRecordBeans)
            {
                currPage = page;
                resRecords.setValue(revenueRecordBeans);
            }
        });
    }
}
