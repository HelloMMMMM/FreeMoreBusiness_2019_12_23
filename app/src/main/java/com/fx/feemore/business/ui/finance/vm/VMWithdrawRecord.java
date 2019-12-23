package com.fx.feemore.business.ui.finance.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.WithdrawRecordBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:提现记录
 * author: frj
 * modify date: 2019/1/20
 */
public class VMWithdrawRecord extends ViewModel
{

    private DataSource dataSource;
    public int currPage;

    public MutableLiveData<HttpFailBean> resHttpFail = new MutableLiveData<>();
    public MutableLiveData<List<WithdrawRecordBean>> resRecords = new MutableLiveData<>();

    @Inject
    public VMWithdrawRecord(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取数据
     *
     * @param page  页码
     * @param count 数据大小
     */
    public void getData(int page, int count)
    {
        dataSource.getWithdrawRecords(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), page, count, 0).subscribe(new ApiCallback<List<WithdrawRecordBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(List<WithdrawRecordBean> withdrawRecordBeans)
            {
                currPage = page;
                resRecords.setValue(withdrawRecordBeans);
            }
        });
    }
}
