package com.fx.feemore.business.ui.apply.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.InterestInfoBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import javax.inject.Inject;

/**
 * function:申请详情
 * author: frj
 * modify date: 2019/1/19
 */
public class VMApplyDetail extends ViewModel
{

    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> failRes = new MutableLiveData<>();

    public MutableLiveData<InterestInfoBean> interestInfoRes = new MutableLiveData<>();

    @Inject
    public VMApplyDetail(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取权益包详情
     *
     * @param storyId 店铺id
     * @param cardId  权益包id
     */
    public void getInfo(String storyId, String cardId)
    {
        dataSource.getInterestInfo(storyId, cardId).subscribe(new ApiCallback<InterestInfoBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                failRes.setValue(httpFail);
            }

            @Override
            public void onNext(InterestInfoBean interestInfoBean)
            {
                interestInfoRes.setValue(interestInfoBean);
            }
        });
    }
}
