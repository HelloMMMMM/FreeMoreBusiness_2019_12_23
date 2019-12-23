package com.fx.feemore.business.ui.commodity.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.ConsumeRecordRes;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import javax.inject.Inject;

/**
 * function:商品消费记录
 * author: frj
 * modify date: 2019/3/1
 */
public class VMCommodityConsumeRecord extends ViewModel
{

    private DataSource dataSource;

    public int currPage = -1;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();

    public MutableLiveData<ConsumeRecordRes> resConsumeRecord = new MutableLiveData<>();

    @Inject
    public VMCommodityConsumeRecord(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取消费记录数据
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     */
    public void getData(String storeId, int page, int pageSize)
    {
        dataSource.getConsumeRecord(storeId, page, pageSize).subscribe(new ApiCallback<ConsumeRecordRes>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(ConsumeRecordRes consumeRecordRes)
            {
                currPage = page;
                resConsumeRecord.setValue(consumeRecordRes);
            }
        });
    }
}
