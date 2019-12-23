package com.fx.feemore.business.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.fx.feemore.business.BuildConfig;
import com.fx.feemore.business.bean.StoryUserBean;
import com.fx.feemore.business.config.BaseConfig;
import com.fx.feemore.business.di.DaggerAppComponent;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.BaseAppManager;
import com.hengxian.baselib.base.BaseApplication;
import com.hengxian.baselib.dagger.BaseInjector;
import com.hengxian.baselib.dagger.component.BaseComponent;
import com.hengxian.baselib.utils.DataCacheUtils;
import com.hengxian.baselib.utils.SharedPreferencesPlus;
import com.hengxian.common.ActivityManagerUtil;
import com.hengxian.common.ToastUtil;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.Bugly;

import java.util.ArrayList;

import javax.inject.Inject;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

/**
 * function:Application
 * author: frj
 * modify date: 2018/12/23
 */
public class AppContext extends BaseApplication {

    public static AppContext instance;

    private StoryUserBean mStoryUserBean;

    @Inject
    OkHttpClient okHttpClient;

    @Inject
    SharedPreferencesPlus sharedPreferencesPlus;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            //该方法专用于LeakCanary为堆分析。
            //在这个过程中你不应该启动你的应用程序。
            return;
        }
        LeakCanary.install(this);

        BaseComponent baseComponent = BaseInjector.init(this, new BaseConfig());
        DaggerAppComponent.builder()
                .baseComponent(baseComponent)
                .build().inject(instance);

        ToastUtil.init(this);
        //极光
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        Bugly.init(getApplicationContext(), "8a935672e5", BuildConfig.DEBUG);

        handleActivity();
        BaseAppManager.mOkHttpClient = okHttpClient;
    }

    public static AppContext getInstanceBase() {
        return instance;
    }

    /**
     * 处理Activity生命周期事件
     */
    private void handleActivity() {
        instance.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityManagerUtil.getInstance().pushActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityManagerUtil.getInstance().popActivity(activity);
            }
        });
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public StoryUserBean getStoryUserBean() {
        if (mStoryUserBean == null) {
            ArrayList<StoryUserBean> list = DataCacheUtils.loadListCache(this, DataCacheUtils.FILE_USERINFO);
            if (VerificationUtil.noEmpty(list)) {
                mStoryUserBean = list.get(0);
            } else {
                mStoryUserBean = new StoryUserBean();
            }
        }
        return mStoryUserBean;
    }

    /**
     * 设置用户信息
     *
     * @param mStoryUserBean
     */
    public void setStoryUserBean(StoryUserBean mStoryUserBean) {
        if (mStoryUserBean == null) {
            this.mStoryUserBean = new StoryUserBean();
        } else {
            this.mStoryUserBean = mStoryUserBean.clone();
        }
        DataCacheUtils.saveListCache(this, mStoryUserBean, DataCacheUtils.FILE_USERINFO);
    }

    /**
     * 保存值至Sharepreference文件中
     *
     * @param key   键
     * @param value 值
     */
    public void putValueToShare(String key, String value) {
        if (sharedPreferencesPlus != null) {
            sharedPreferencesPlus.putString(key, value);
        }
    }

    /**
     * 保存值至Sharepreference文件中
     *
     * @param key   键
     * @param value 值
     */
    public void putValueToShare(String key, boolean value) {
        if (sharedPreferencesPlus != null) {
            sharedPreferencesPlus.putBoolean(key, value);
        }
    }

}
