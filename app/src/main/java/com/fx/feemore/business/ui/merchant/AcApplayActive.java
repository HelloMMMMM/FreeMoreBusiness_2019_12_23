package com.fx.feemore.business.ui.merchant;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.AcApplyActiveBinding;
import com.fx.feemore.business.ui.home.adapter.AdHomeItem;
import com.fx.feemore.business.ui.merchant.vm.VMApplyActive;
import com.fx.feemore.business.ui.merchant.vm.VMApplyActiveItem;
import com.hengxian.baselib.base.BaseBindActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * function:活动申请进度
 * author: frj
 * modify date: 2019/1/2
 */
public class AcApplayActive extends BaseBindActivity<AcApplyActiveBinding, VMApplyActive> implements HasSupportFragmentInjector
{
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_apply_active;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.apply_active_title);
        initTabs();
    }

    private void initTabs()
    {
        final List<FragmentViewPagerBase> list = new ArrayList<>();
        list.add(FApplyActiveItem.newInstance(VMApplyActiveItem.TYPE_PASS));
        list.add(FApplyActiveItem.newInstance(VMApplyActiveItem.TYPE_APPLING));
        list.add(FApplyActiveItem.newInstance(VMApplyActiveItem.TYPE_REJECT));

        AdHomeItem adapter = new AdHomeItem(getSupportFragmentManager(), list, getResources().getStringArray(R.array.apply_interest_tabs));
        binding.viewPager.setAdapter(adapter);
        binding.tabs.setViewPager(binding.viewPager);
        binding.viewPager.setOffscreenPageLimit(list.size());
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                list.get(position).onStarShow();
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector()
    {
        return fragmentInjector;
    }
}
