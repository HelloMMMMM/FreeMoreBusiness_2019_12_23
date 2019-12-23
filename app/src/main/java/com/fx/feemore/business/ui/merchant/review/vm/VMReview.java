package com.fx.feemore.business.ui.merchant.review.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.util.ConstantValue;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:运营审核列表
 * author: frj
 * modify date: 2019/5/14
 */
public class VMReview extends ViewModel
{

    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> failRes = new MutableLiveData<>();

    public MutableLiveData<List<InterestBean>> interestsRes = new MutableLiveData<>();

    public MutableLiveData<ResponseBean> operationRes = new MutableLiveData<>();

    //当前页码
    public int currPage;

    @Inject
    public VMReview(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取权益包申请记录（店铺内待审核的记录）
     *
     * @param storyId 店铺id
     * @param page    页码
     */
    public void getData(String storyId, int page)
    {
        //审核状态，-2：店铺内待审核
        dataSource.getInterestList(storyId, -2, page, ConstantValue.PAGE_SIZE).subscribe(new ApiCallback<List<InterestBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                failRes.setValue(httpFail);
            }

            @Override
            public void onNext(List<InterestBean> interestBeans)
            {
                currPage = page;
                interestsRes.setValue(interestBeans);
            }
        });
    }

    /**
     * 权益包店铺内审核通过
     *
     * @param storyId    店铺id
     * @param account    审核帐号
     * @param interestId 权益包id
     */
    public void interestPass(String storyId, String account, String interestId)
    {
        dataSource.interestPass(storyId, account, interestId).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                failRes.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                operationRes.setValue(responseBean);
            }
        });
    }

    /**
     * 权益包店铺内审核不通过
     *
     * @param storeId    店铺id
     * @param account    审核帐号
     * @param interestId 权益包id
     * @param reason     审核不通过原因
     */
    public void interestRefuse(String storeId, String account, String interestId, String reason)
    {
        dataSource.interestRefuse(storeId, account, interestId, reason).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                failRes.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                operationRes.setValue(responseBean);
            }
        });
    }
}
