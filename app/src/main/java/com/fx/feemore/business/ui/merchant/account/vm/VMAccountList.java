package com.fx.feemore.business.ui.merchant.account.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.AccountBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:子账号列表
 * author: frj
 * modify date: 2019/5/15
 */
public class VMAccountList extends ViewModel
{

    private DataSource dataSource;

    public MutableLiveData<List<AccountBean>> accountBeansRes = new MutableLiveData<>();
    public MutableLiveData<ResponseBean> delRes = new MutableLiveData<>();
    public MutableLiveData<HttpFailBean> failRes = new MutableLiveData<>();

    public int currPage;

    @Inject
    public VMAccountList(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取子账号列表
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     */
    public void getAccounts(String storeId, int page, int pageSize)
    {
        dataSource.getAccounts(storeId, page, pageSize).subscribe(new ApiCallback<List<AccountBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                failRes.setValue(httpFail);
            }

            @Override
            public void onNext(List<AccountBean> accountBeans)
            {
                currPage = page;
                accountBeansRes.setValue(accountBeans);
            }
        });
    }

    /**
     * 删除子账号
     *
     * @param storeId     店铺id
     * @param storeUserId 店铺子账号用户id
     */
    public void delAccount(String storeId, String storeUserId)
    {
        dataSource.delAccount(storeId, storeUserId).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                failRes.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                delRes.setValue(responseBean);
            }
        });
    }

}
