package com.fx.feemore.business.ui.commodity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.AcCommodityManagerBinding;
import com.fx.feemore.business.ui.commodity.item.FCommodityConsumeRecord;
import com.fx.feemore.business.ui.commodity.item.FCommodityRemoved;
import com.fx.feemore.business.ui.commodity.item.FCommodityUsing;
import com.fx.feemore.business.ui.commodity.vm.VMCommodityManager;
import com.fx.feemore.business.ui.home.adapter.AdHomeItem;
import com.hengxian.baselib.base.BaseBindActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * function:商品管理
 * author: frj
 * modify date: 2019/1/18
 */
public class AcCommodityManager extends BaseBindActivity<AcCommodityManagerBinding, VMCommodityManager> implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_commodity_manager;
    }

    @Override
    protected void init() {
        int from = getIntent().getIntExtra("from", 1);
        if (from == 1) {
            setToolbarTitle(getString(R.string.util_gm_equity));
        } else if (from == 2) {
            setToolbarTitle(getString(R.string.util_gm_card));
        }

        binding.setVm(viewModel);

        String[] tabTitles = getResources().getStringArray(R.array.commodity_manager_tabs);
        final List<FragmentViewPagerBase> fragmengs = new ArrayList<>(tabTitles.length);
        fragmengs.add(FCommodityUsing.newInstance());
        fragmengs.add(FCommodityRemoved.newInstance());
        fragmengs.add(FCommodityConsumeRecord.newInstance());
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
