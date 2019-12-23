package com.hengxian.baselib.dagger.component;

import android.app.Application;

import com.google.gson.Gson;
import com.hengxian.baselib.dagger.module.BaseModule;
import com.hengxian.baselib.utils.SharedPreferencesPlus;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * 全局基础组件
 *
 * @author wfq
 * @date 2018/6/29
 */
@Singleton
@Component(modules = {BaseModule.class, AndroidSupportInjectionModule.class})
public interface BaseComponent {

    /**
     * 提供 Application 实例依赖
     *
     * @return Application 实例
     */
    Application provideApplication();

    SharedPreferencesPlus provideSharedPreferencesPlus();

    Gson provideGson();

    OkHttpClient provideOkHttpClient();

    Retrofit provideRetrofit();

    @Component.Builder
    interface Builder {

        /**
         * 绑定 application 实例，提供给外部依赖
         *
         * @param application Application及子类
         */
        @BindsInstance
        Builder application(Application application);

        Builder AppModule(BaseModule baseModule);

        /**
         * 构建 BaseComponent 抽象方法
         *
         * @return BaseComponent 实例
         */
        BaseComponent build();
    }
}
