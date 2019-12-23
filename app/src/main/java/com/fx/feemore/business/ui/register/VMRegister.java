package com.fx.feemore.business.ui.register;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.TextView;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.util.CountDownButtonHelper;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;
import com.hengxian.baselib.utils.RxSchedules;

import javax.inject.Inject;

/**
 * function:注册
 * author: frj
 * modify date: 2018/12/23
 */
public class VMRegister extends ViewModel
{
    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> httpFailRes = new MutableLiveData();

    public MutableLiveData<ResponseBean> getCodeSuccRes = new MutableLiveData();

    public MutableLiveData<ResponseBean> checkCodeSuccRes = new MutableLiveData();


    private CountDownButtonHelper countDownButtonHelper;//倒计时帮助类

    @Inject
    public VMRegister(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 初始化倒计时工具类
     *
     * @param textView 点击获取按钮
     */
    public void initCountDownHelper(TextView textView)
    {
        countDownButtonHelper = new CountDownButtonHelper(textView, "获取验证码", 60, 1, AppContext.getInstanceBase());
    }

    /**
     * 发送验证码
     */
    public void sendVerificationCode(String phone)
    {
        dataSource.sendVerificationCode(phone).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                httpFailRes.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                if (countDownButtonHelper != null)
                {
                    countDownButtonHelper.start();
                }
                getCodeSuccRes.setValue(responseBean);
            }
        });
    }

    /**
     * 校验验证码
     */
    public void checkVerificationCode(String phone, String code)
    {
        dataSource.checkVerificationCode(phone, code).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                httpFailRes.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                checkCodeSuccRes.setValue(responseBean);
            }
        });
    }

    /**
     * 销毁倒计时工具类
     */
    public void destroy()
    {
        if (countDownButtonHelper != null)
        {
            countDownButtonHelper.destroy();
        }
    }

}
