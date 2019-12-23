package com.fx.feemore.business.di;

import com.fx.feemore.business.app.AppContext;
import com.hengxian.baselib.dagger.component.BaseComponent;
import com.hengxian.baselib.dagger.scope.ApplicationScope;

import dagger.Component;

/**
 * 组件
 *
 * @author wfq
 * @date 2018/6/1
 */
@ApplicationScope
@Component(modules = {
        ActivityModule.class,
        ViewModelModule.class,
        DataModule.class}, dependencies = BaseComponent.class)
public interface AppComponent
{

    /**
     * 将所有模块注入application
     *
     * @param application 一般是实现了
     */
    void inject(AppContext application);
}
