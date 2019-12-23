package com.fx.feemore.business.ui.apply.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.util.ConstantValue;
import com.hengxian.baselib.bean.HttpFailBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:申请项ViewModel
 * author: frj
 * modify date: 2019/1/18
 */
public class VMApplyItem extends ViewModel
{
    /**
     * 审核状态：申请中
     */
    public static final int STATUS_APPLICATION = 1;
    /**
     * 审核状态：审核通过
     */
    public static final int STATUS_PASS = 3;
    /**
     * 审核状态：拒绝，被锁定
     */
    public static final int STATUS_REFUSED = 2;

    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> failRes = new MutableLiveData<>();

    public MutableLiveData<List<InterestBean>> interestsRes = new MutableLiveData<>();

    //当前页码
    public int currPage;

    @Inject
    public VMApplyItem(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取权益包申请记录
     *
     * @param storyId 店铺id
     * @param status  申请状态
     * @param page    页码
     */
    public void getData(String storyId, int status, int page)
    {
        dataSource.getInterestListByType(storyId, status, page, ConstantValue.PAGE_SIZE).subscribe(new ApiCallback<List<InterestBean>>()
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
}
