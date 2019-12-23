package com.fx.feemore.business.ui.finance.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.BankCardBean;
import com.fx.feemore.business.bean.WithdrawIntroBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * function:提现ViewModel
 * author: frj
 * modify date: 2018/12/31
 */
public class VMWithdraw extends ViewModel
{

    private DataSource dataSource;
    public MutableLiveData<HttpFailBean> resBankHttpFail = new MutableLiveData<>();
    public MutableLiveData<ArrayList<BankCardBean>> resBankCards = new MutableLiveData<>();

    public MutableLiveData<ResponseBean> resSucc = new MutableLiveData<>();
    public MutableLiveData<HttpFailBean> resHttpFail = new MutableLiveData<>();

    public MutableLiveData<WithdrawIntroBean> resWithdrawIntro = new MutableLiveData<>();

    @Inject

    public VMWithdraw(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取银行卡列表
     */
    public void getBankCards()
    {
        dataSource.getBankCards(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID()).subscribe(new ApiCallback<ArrayList<BankCardBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resBankHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(ArrayList<BankCardBean> bankCardBeans)
            {
                resBankCards.setValue(bankCardBeans);
            }
        });
    }

    /**
     * 获取提现说明
     */
    public void getWithdrawIntro()
    {
        dataSource.getWithdrawIntro(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID()).subscribe(new ApiCallback<WithdrawIntroBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(WithdrawIntroBean withdrawIntroBean)
            {
                resWithdrawIntro.setValue(withdrawIntroBean);
            }
        });
    }

    /**
     * 提现
     *
     * @param bankCard 银行卡号
     * @param amount   提现金额
     * @param account 用户帐号
     */
    public void withdraw(String bankCard, String amount,String account)
    {
        dataSource.withdraw(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), bankCard, amount,account).subscribe(new ApiCallback<ResponseBean>()
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
