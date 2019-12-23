package com.fx.feemore.business.ui.merchant.account.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import javax.inject.Inject;

/**
 * function:新增子账号ViewModel
 * author: frj
 * modify date: 2019/5/14
 */
public class VMAddAccount extends ViewModel
{

    private DataSource dataSource;

    public MutableLiveData<ResponseBean> addRes = new MutableLiveData<>();
    public MutableLiveData<HttpFailBean> failRes = new MutableLiveData<>();

    @Inject
    public VMAddAccount(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 添加子账号
     *
     * @param storeId 店铺id
     * @param account 帐号
     * @param type    账号类型，1运营者，2操作者
     * @param name    账号名称
     */
    public void addAccount(String storeId, String account, int type, String name)
    {
        dataSource.addAccount(storeId, type, account, name).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                failRes.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                addRes.setValue(responseBean);
            }
        });
    }
}
