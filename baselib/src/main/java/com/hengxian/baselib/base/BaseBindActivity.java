package com.hengxian.baselib.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hengxian.baselib.dagger.Injectable;
import com.hengxian.baselib.dagger.ViewModelFactory;
import com.hengxian.common.StatusBarUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.inject.Inject;

/**
 * function:基础的数据绑定Activity
 * author: frj
 * modify date: 2018/6/8
 */
public abstract class BaseBindActivity<T extends ViewDataBinding, K extends ViewModel> extends BaseActivity implements Injectable {

    @Inject
    protected ViewModelFactory viewModelFactory;

    protected K viewModel;
    protected T binding;

    /**
     * 起始页
     */
    protected static final int PAGE_START = 0;

    /**
     * 分页时，每页的数据量
     */
    protected static final int PAGE_NUM = 10;

    @Override
    public void handleCreate(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getTypeClass());
        permissionInit();
    }

    /**
     * 获取泛型类型
     */
    private Class<K> getTypeClass() {

        //这里获得到的是类的泛型的类型
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        if (type != null) {
            Type[] actualTypeArguments = type.getActualTypeArguments();
            return (Class<K>) actualTypeArguments[1];
        } else {
            return null;
        }
    }
}
