package com.fx.feemore.business.http;

import com.fx.feemore.business.app.AppContext;
import com.hengxian.baselib.utils.SharedPreferencesPlus;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Header 拦截器
 * 在header中插入token值
 */
public class HeaderInterceptor implements Interceptor
{
    private SharedPreferencesPlus sharedPreferencesPlus;
    //用户token值，发生改变即更新
    private String token;

    public HeaderInterceptor()
    {
        initSharePreference();
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request originalRequest = chain.request();
        return chain.proceed(originalRequest);
    }

    /**
     * 初始化SharePreference
     */
    private void initSharePreference()
    {
        sharedPreferencesPlus = SharedPreferencesPlus.createDefault(AppContext.getInstanceBase());
    }


}
