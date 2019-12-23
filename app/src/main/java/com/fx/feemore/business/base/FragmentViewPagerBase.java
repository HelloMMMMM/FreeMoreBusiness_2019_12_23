package com.fx.feemore.business.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ViewDataBinding;

import com.hengxian.baselib.base.BaseBindFragment;
import com.hengxian.baselib.dagger.Injectable;

/**
 * function:运用于使用在ViewPager中的Fragment基类
 * author: frj
 * modify date: 2017/6/30
 */

public abstract class FragmentViewPagerBase<T extends ViewDataBinding, K extends ViewModel> extends BaseBindFragment<T, K> implements Injectable
{
    /**
     * 表示ViewPager显示当前的Fragment
     */
    public abstract void onStarShow();

}
