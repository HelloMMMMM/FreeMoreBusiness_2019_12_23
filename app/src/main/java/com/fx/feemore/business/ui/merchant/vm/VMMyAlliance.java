package com.fx.feemore.business.ui.merchant.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.AllianceBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:我发起的联盟ViewModel
 * author: frj
 * modify date: 2019/1/2
 */
public class VMMyAlliance extends ViewModel
{

    private DataSource dataSource;
    public int currPage;

    public MutableLiveData<HttpFailBean> resHttpFail = new MutableLiveData<>();
    public MutableLiveData<List<AllianceBean>> resAlliances = new MutableLiveData<>();

    @Inject
    public VMMyAlliance(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取数据
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     */
    public void getData(String storeId, int page, int pageSize)
    {
        dataSource.getAlliances(storeId, page, pageSize, "1").subscribe(new ApiCallback<List<AllianceBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(List<AllianceBean> allianceBeans)
            {
                currPage = page;
                resAlliances.setValue(allianceBeans);
            }
        });
    }
}
