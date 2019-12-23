package com.fx.feemore.business.config;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.http.HeaderInterceptor;
import com.fx.feemore.business.http.ServiceUrlUtil;
import com.google.gson.GsonBuilder;
import com.hengxian.baselib.config.ConfigBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * function:基础配置
 * author: frj
 * modify date: 2018/12/23
 */
public class BaseConfig implements ConfigBuilder {
    @Override
    public void buildGson(GsonBuilder builder) {

    }

    @Override
    public void buildOkHttp(OkHttpClient.Builder builder) {
        builder
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(new Cache(new File(AppContext.getInstanceBase().getCacheDir(), "okHttpCache"), 10 * 1024 * 1024))
                //设置Cookie管理
                .addInterceptor(new HeaderInterceptor());
    }

    @Override
    public void buildRetrofit(Retrofit.Builder builder) {
        builder.baseUrl(ServiceUrlUtil.getDefault());
    }
}
