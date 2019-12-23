package com.fx.feemore.business.ui.notify;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.AcNotifyManagerBinding;
import com.fx.feemore.business.ui.home.adapter.AdHomeItem;
import com.fx.feemore.business.ui.notify.vm.VMNotifyManager;
import com.hengxian.baselib.base.BaseBindActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * function:系统通知管理
 * author: frj
 * modify date: 2019/1/17
 */
public class AcNotifyManager extends BaseBindActivity<AcNotifyManagerBinding, VMNotifyManager> implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_notify_manager;
    }

    @Override
    protected void init() {
        binding.setVm(viewModel);
        setToolbarTitle("消息通知");

        String[] tabTitles = getResources().getStringArray(R.array.notify_manager_tabs);
        final List<FragmentViewPagerBase> fragmengs = new ArrayList<>(tabTitles.length);
        fragmengs.add(FNotifyOrder.newInstance());
//        fragmengs.add(FNotifyOther.newInstance());
        AdHomeItem adapter = new AdHomeItem(getSupportFragmentManager(), fragmengs, tabTitles);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(tabTitles.length);
        binding.tabs.setViewPager(binding.viewPager);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fragmengs.get(position).onStarShow();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.viewPager.setCurrentItem(0);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
