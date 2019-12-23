package com.fx.feemore.business.ui.merchant;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.AcApplyInterestBinding;
import com.fx.feemore.business.ui.home.adapter.AdHomeItem;
import com.fx.feemore.business.ui.merchant.vm.VMApplyInterest;
import com.fx.feemore.business.ui.merchant.vm.VMApplyInterestItem;
import com.hengxian.baselib.base.BaseBindActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * function:权益包申请进度
 * author: frj
 * modify date: 2019/1/2
 */
public class AcAppyInterest extends BaseBindActivity<AcApplyInterestBinding, VMApplyInterest> implements HasSupportFragmentInjector
{

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_apply_interest;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.apply_interest_title);
        initTabs();
    }

    private void initTabs()
    {
        final List<FragmentViewPagerBase> list = new ArrayList<>();
        list.add(FApplyInterest.newInstance(VMApplyInterestItem.TYPE_PASS));
        list.add(FApplyInterest.newInstance(VMApplyInterestItem.TYPE_APPLING));
        list.add(FApplyInterest.newInstance(VMApplyInterestItem.TYPE_REJECT));

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
