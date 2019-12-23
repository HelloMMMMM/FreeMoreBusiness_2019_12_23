package com.fx.feemore.business.ui.finance.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.WithdrawRecordBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import javax.inject.Inject;

/**
 * function:提现详情ViewModel
 * author: frj
 * modify date: 2018/12/31
 */
public class VMWithdrawDetail extends ViewModel
{

    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> resHttpFail = new MutableLiveData<>();
    public MutableLiveData<WithdrawRecordBean> resRecord = new MutableLiveData<>();

    @Inject
    public VMWithdrawDetail(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取数据
     *
     * @param withdrawId 提现id
     */
    public void getData(String withdrawId)
    {
        dataSource.getWithdrawInfo(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), withdrawId).subscribe(new ApiCallback<WithdrawRecordBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(WithdrawRecordBean withdrawRecordBean)
            {
                resRecord.setValue(withdrawRecordBean);
            }
        });
    }
}
