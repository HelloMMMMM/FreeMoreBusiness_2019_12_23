package com.hengxian.baselib.config;

import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 为第三方库初始化配置
 *
 * @author wfq
 * @date 2018/6/1
 */
public interface ConfigBuilder {

    /**
     * 配置Gson 各项
     * @param builder 由 BaseModule 提供
     */
    void buildGson(GsonBuilder builder);

    /**
     * 配置 OkHttpClient 各项参数
     * @param builder OkHttpClient.Builder； 由 BaseModule 提供
     */
    void buildOkHttp(OkHttpClient.Builder builder);

    /**
     * 配置 Retrofit 各项参数
     * @param builder Retrofit.Builder； 由 BaseModule 提供
     */
    void buildRetrofit(Retrofit.Builder builder);
}
