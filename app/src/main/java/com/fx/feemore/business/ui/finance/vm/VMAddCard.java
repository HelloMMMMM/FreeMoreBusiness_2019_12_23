package com.fx.feemore.business.ui.finance.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.util.CountDownButtonHelper;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import javax.inject.Inject;

/**
 * function:绑定银行卡ViewModel
 * author: frj
 * modify date: 2018/12/31
 */
public class VMAddCard extends ViewModel
{

    private DataSource dataSource;
    private CountDownButtonHelper countDownButtonHelper;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();
    public MutableLiveData<ResponseBean> resSucc = new MutableLiveData<>();
    public MutableLiveData<ResponseBean> resSendSucc = new MutableLiveData<>();

    @Inject
    public VMAddCard(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }


    public void initCountDown(@NonNull TextView textView)
    {
        countDownButtonHelper = new CountDownButtonHelper(textView, "获取验证码", 60, 1, textView.getContext());
    }

    /**
     * 添加银行卡
     *
     * @param bankCard 银行卡号
     * @param name     持卡人姓名
     * @param phone    手机号
     * @param code     验证码
     */
    public void addBankCard(String bankCard, String name, String phone, String code)
    {
        dataSource.addBankCard(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), bankCard, name, phone, code).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                resSucc.setValue(responseBean);
            }
        });
    }

    /**
     * 获取手机验证码
     *
     * @param phone 手机号
     */
    public void getCode(String phone)
    {
        dataSource.sendVerificationCode(phone).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                if (countDownButtonHelper != null)
                {
                    countDownButtonHelper.start();
                }
                resSendSucc.setValue(responseBean);
            }
        });
    }

    /**
     * 销毁计时器
     */
    public void destroy()
    {
        if (countDownButtonHelper != null)
        {
            countDownButtonHelper.destroy();
        }
    }
}
