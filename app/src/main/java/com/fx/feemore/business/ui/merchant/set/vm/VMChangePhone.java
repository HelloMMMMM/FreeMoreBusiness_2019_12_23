package com.fx.feemore.business.ui.merchant.set.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
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
 * function:更改手机号ViewModel
 * author: frj
 * modify date: 2019/1/22
 */
public class VMChangePhone extends ViewModel
{

    private DataSource dataSource;

    public ObservableField<Boolean> firstStep = new ObservableField<>();

    public MutableLiveData<HttpFailBean> resHttpFail = new MutableLiveData<>();
    public MutableLiveData<ResponseBean> resSendCodeSucc = new MutableLiveData<>();
    public MutableLiveData<ResponseBean> resCheckCodeSucc = new MutableLiveData<>();
    public MutableLiveData<ResponseBean> resChangePhone = new MutableLiveData<>();

    private CountDownButtonHelper old_countDown;
    private CountDownButtonHelper new_countDown;

    @Inject
    public VMChangePhone(DataSource dataSource)
    {
        this.dataSource = dataSource;
        firstStep.set(true);
    }

    /**
     * 初始化当前手机号获取验证码时的计时器
     *
     * @param textView
     */
    public void initOldCountDown(@NonNull TextView textView)
    {
        old_countDown = new CountDownButtonHelper(textView, "获取验证码", 60, 1, textView.getContext());
    }

    /**
     * 初始化新手机号获取验证码时的计时器
     *
     * @param textView
     */
    public void initNewCountDown(@NonNull TextView textView)
    {
        new_countDown = new CountDownButtonHelper(textView, "获取验证码", 60, 1, textView.getContext());
    }

    /**
     * 获取手机号的验证码
     *
     * @param phone 当前手机号
     */
    public void sendVerifyCode(@NonNull String phone)
    {
        dataSource.sendVerificationCode(phone).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                if (phone.equals(AppContext.getInstanceBase().getStoryUserBean().getACCOUNT()))
                {
                    if (old_countDown != null)
                    {
                        old_countDown.start();
                    }
                } else
                {
                    if (new_countDown != null)
                    {
                        new_countDown.start();
                    }
                }

                resSendCodeSucc.setValue(responseBean);
            }
        });
    }

    /**
     * 校验验证码是否输入正确
     *
     * @param phone 手机号
     * @param code  验证码
     */
    public void checkVerificationCode(String phone, String code)
    {
        dataSource.checkVerificationCode(phone, code).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                resCheckCodeSucc.setValue(responseBean);
            }
        });
    }

    /**
     * 更改手机号
     *
     * @param phone 新手机号
     * @param code  验证码
     */
    public void changePhone(String phone, String code)
    {
        dataSource.changePhone(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), phone, code).subscribe(new ApiCallback<ResponseBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean)
            {
                resChangePhone.setValue(responseBean);
            }
        });
    }

    /**
     * 销毁
     */
    public void destroy()
    {
        if (old_countDown != null)
        {
            old_countDown.destroy();
        }
        if (new_countDown != null)
        {
            new_countDown.destroy();
        }
    }
}
