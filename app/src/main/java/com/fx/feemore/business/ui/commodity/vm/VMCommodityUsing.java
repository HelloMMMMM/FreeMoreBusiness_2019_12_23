package com.fx.feemore.business.ui.commodity.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.InterestListBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import javax.inject.Inject;

/**
 * function:商品管理-发布中
 * author: frj
 * modify date: 2019/1/18
 */
public class VMCommodityUsing extends ViewModel
{
    private DataSource dataSource;

    public int currPage = -1;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();

    public MutableLiveData<InterestListBean> resInterestList = new MutableLiveData<>();

    public MutableLiveData<ResponseBean> resResponseBean = new MutableLiveData<>();

    @Inject
    public VMCommodityUsing(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取发布中的权益包列表
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     */
    public void getInterestList(String storeId, int page, int pageSize)
    {
        //状态：1：审核通过；-99:已下架
        dataSource.getInterestGoodList(storeId, 1, page, pageSize).subscribe(new ApiCallback<InterestListBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(InterestListBean interestListBean)
            {
                currPage = page;
                resInterestList.setValue(interestListBean);
            }
        });
    }

    /**
     * 下架权益包
     *
     * @param storeId 店铺id
     * @param cardId  权益包id
     */
    public void removeInterest(String storeId, String cardId)
    {
        dataSource.obtainedInterest(storeId, cardId).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                resResponseBean.setValue(responseBean);
            }
        });
    }
}
