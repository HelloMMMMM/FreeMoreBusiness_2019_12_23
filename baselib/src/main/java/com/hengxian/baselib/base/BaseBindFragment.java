package com.hengxian.baselib.base;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hengxian.baselib.dagger.Injectable;
import com.hengxian.baselib.dagger.ViewModelFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.inject.Inject;

/**
 * function:基础的数据绑定Fragment
 * author: frj
 * modify date: 2018/6/13
 */
public abstract class BaseBindFragment<T extends ViewDataBinding, K extends ViewModel> extends BaseFragment implements Injectable
{

    @Inject
    protected ViewModelFactory viewModelFactory;

    protected K viewModel;
    protected T binding;

    /**
     * 起始页
     */
    public static final int PAGE_START = 0;

    /**
     * 分页时，每页的数据量
     */
    protected static final int PAGE_NUM = 10;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // 当fragment结合 ViewPager 使用时，一些生命周期函数会随着页面滑动重复调用
        // ViewModel 的初始化尽量在 onCreate() 等只会调用一次的函数中
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getTypeClass());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return binding.getRoot();
    }

    /**
     * 获取布局Id
     *
     * @return
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * 获取泛型类型
     */
    private Class<K> getTypeClass()
    {

        //这里获得到的是类的泛型的类型
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        if (type != null)
        {
            Type[] actualTypeArguments = type.getActualTypeArguments();
            return (Class<K>) actualTypeArguments[1];
        } else
        {
            return null;
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}
