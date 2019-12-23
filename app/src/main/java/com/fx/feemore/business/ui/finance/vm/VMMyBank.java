package com.fx.feemore.business.ui.finance.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.BankCardBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:我的银行卡ViewModel
 * author: frj
 * modify date: 2019/1/20
 */
public class VMMyBank extends ViewModel
{

    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> resHttpFail = new MutableLiveData<>();
    public MutableLiveData<List<BankCardBean>> resBankCards = new MutableLiveData<>();
    public MutableLiveData<ResponseBean> resSucc = new MutableLiveData<>();

    @Inject
    public VMMyBank(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取数据
     */
    public void getData()
    {
        dataSource.getBankCards(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID()).subscribe(new ApiCallback<List<BankCardBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(List<BankCardBean> bankCardBeans)
            {
                resBankCards.setValue(bankCardBeans);
            }
        });
    }

    /**
     * 删除银行卡
     *
     * @param bankCardId 银行卡id
     */
    public void delBankCard(String bankCardId)
    {
        dataSource.delBankCard(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), bankCardId).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                resSucc.setValue(responseBean);
            }
        });
    }
}
