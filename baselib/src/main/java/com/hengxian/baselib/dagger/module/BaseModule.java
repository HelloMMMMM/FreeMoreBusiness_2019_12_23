package com.hengxian.baselib.dagger.module;

import android.app.Application;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hengxian.baselib.BuildConfig;
import com.hengxian.baselib.config.ConfigBuilder;
import com.hengxian.baselib.http.converter.gson.GsonWrapperConverterFactory;
import com.hengxian.baselib.utils.SharedPreferencesPlus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 提供全局实例对象
 *
 * @author wfq
 * @date 2018/6/1
 */
@Module
public class BaseModule {

    private ConfigBuilder configBuilder;

    public BaseModule(@Nullable ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
    }

    /**
     * 提供全局 Gson 实例
     *
     * @return Gson
     */
    @Singleton
    @Provides
    public Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        if (configBuilder != null) {
            configBuilder.buildGson(builder);
        }
        return builder.create();
    }

    /**
     * 提供全局OKHttpClient实例
     * 已配置日志拦截器
     * 其他属性由 ConfigBuilder 实现类提供
     *
     * @return OkHttpClient
     */
    @Singleton
    @Provides
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (configBuilder != null) {
            configBuilder.buildOkHttp(builder);
        }
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        return builder.build();
    }

    /**
     * 提供全局Retrofit实例
     * 已配置 GsonConverterFactory、RxJava2CallAdapterFactory
     * 其他属性由 ConfigBuilder 实现类提供
     *
     * @param okHttpClient 由 provideOkHttpClient() 方法提供
     * @return Retrofit
     */
    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .client(okHttpClient);
        if (configBuilder != null) {
            configBuilder.buildRetrofit(builder);
        }
        builder.addConverterFactory(GsonWrapperConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build();
    }

    /**
     * 提供全局 SharedPreferencesPlus 实例，定义 SharedPreferences 文件名：common
     *
     * @param application 创建 SharedPreferences 实例
     * @param gson        全局配置的 Gson 实例，用于保存对象
     * @return SharedPreferencesPlus
     */
    @Singleton
    @Provides
    public SharedPreferencesPlus provideSharedPreferencesPlus(Application application, Gson gson) {
        return SharedPreferencesPlus.createDefault(application, gson);
    }
}
