package com.fx.feemore.business.ui.apply;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.AcApplyManagerBinding;
import com.fx.feemore.business.ui.apply.item.FApplyItem;
import com.fx.feemore.business.ui.apply.vm.VMApplyItem;
import com.fx.feemore.business.ui.apply.vm.VMApplyManager;
import com.fx.feemore.business.ui.home.adapter.AdHomeItem;
import com.hengxian.baselib.base.BaseBindActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * function:权益包申请管理
 * author: frj
 * modify date: 2019/1/18
 */
public class AcApplyManager extends BaseBindActivity<AcApplyManagerBinding, VMApplyManager> implements HasSupportFragmentInjector {


    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_apply_manager;
    }

    @Override
    protected void init() {
        binding.setVm(viewModel);
        setToolbarTitle("申请管理");

        String[] tabTitles = getResources().getStringArray(R.array.apply_manager_tabs);
        final List<FragmentViewPagerBase> fragmengs = new ArrayList<>(tabTitles.length);
        fragmengs.add(FApplyItem.newInstance(VMApplyItem.STATUS_APPLICATION));
        fragmengs.add(FApplyItem.newInstance(VMApplyItem.STATUS_REFUSED));
        fragmengs.add(FApplyItem.newInstance(VMApplyItem.STATUS_PASS));
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
        binding.viewPager.setCurrentItem(getIntent().getIntExtra(KEY, 0));
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
