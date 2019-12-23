package com.hengxian.baselib.dagger;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.hengxian.baselib.config.ConfigBuilder;
import com.hengxian.baselib.dagger.component.BaseComponent;
import com.hengxian.baselib.dagger.component.DaggerBaseComponent;
import com.hengxian.baselib.dagger.module.BaseModule;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * 初始化 BaseComponent 组件, 统一实现 Activity 与 Fragment 注入
 *
 * @author wfq
 * @date 2018/7/2
 */
public class BaseInjector {

    public static BaseComponent init(@NonNull Application application, @NonNull ConfigBuilder configBuilder) {
        BaseComponent baseComponent = DaggerBaseComponent.builder()
                .application(application)
                .AppModule(new BaseModule(configBuilder))
                .build();

        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                handleActivity(activity);
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
            }
        });

        return baseComponent;
    }

    /**
     * 处理Activity 与 Fragment 注入
     *
     * @param activity Activity
     */
    private static void handleActivity(Activity activity) {
        if (activity instanceof HasSupportFragmentInjector || activity instanceof Injectable) {
            AndroidInjection.inject(activity);
        }
        if (activity instanceof FragmentActivity) {
            ((FragmentActivity) activity).getSupportFragmentManager()
                    .registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                        @Override
                        public void onFragmentPreCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
                            if (f instanceof Injectable) {
                                AndroidSupportInjection.inject(f);
                            }
                        }
                    }, true);
        }
    }
}
