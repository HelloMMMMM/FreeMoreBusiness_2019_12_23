package com.fx.feemore.business.ui.login.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.TextView;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.StoryUserBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.util.CountDownButtonHelper;
import com.fx.feemore.business.util.JPushUtil;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;

import javax.inject.Inject;

/**
 * function:手机号验证
 * author: frj
 * modify date: 2018/12/23
 */
public class VMPhoneVerify extends ViewModel
{
    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> failRes = new MutableLiveData<>();
    public MutableLiveData<StoryUserBean> storyUserSucc = new MutableLiveData<>();

    public MutableLiveData<ResponseBean> getCodeSuccRes = new MutableLiveData<>();

    private CountDownButtonHelper countDownButtonHelper;//倒计时帮助类


    @Inject
    public VMPhoneVerify(DataSource dataSource)
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
                failRes.setValue(httpFail);
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
     * 登录
     *
     * @param phone 手机号
     * @param code  验证码
     */
    public void login(String phone, String code)
    {
        dataSource.login(phone, code).subscribe(new ApiCallback<StoryUserBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                failRes.setValue(httpFail);
            }

            @Override
            public void onNext(StoryUserBean storyUserBean)
            {
                if (storyUserBean != null)
                {
                    JPushUtil.jpushSetAlias(storyUserBean.getSTORE_ID());
                }
                storyUserSucc.setValue(storyUserBean);
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
