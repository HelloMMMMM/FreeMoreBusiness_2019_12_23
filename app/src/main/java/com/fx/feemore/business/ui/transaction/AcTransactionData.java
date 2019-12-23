package com.fx.feemore.business.ui.transaction;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.AcTransactionDataBinding;
import com.fx.feemore.business.ui.home.adapter.AdHomeItem;
import com.fx.feemore.business.ui.transaction.vm.VMTransactionData;
import com.hengxian.baselib.base.BaseBindActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * function:近日交易数据
 * author: frj
 * modify date: 2019/1/17
 */
public class AcTransactionData extends BaseBindActivity<AcTransactionDataBinding, VMTransactionData> implements HasSupportFragmentInjector
{

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_transaction_data;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("近日交易数据");

        String[] tabTitles = getResources().getStringArray(R.array.transaction_data_tabs);
        final List<FragmentViewPagerBase> fragmengs = new ArrayList<>(tabTitles.length);
        fragmengs.add(FTransactionAmount.newInstance());
        fragmengs.add(FTransactionVisitor.newInstance());
        fragmengs.add(FTransactionCollege.newInstance());
        fragmengs.add(FTransactionTransaction.newInstance());
        AdHomeItem adapter = new AdHomeItem(getSupportFragmentManager(), fragmengs, tabTitles);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(tabTitles.length);
        binding.tabs.setViewPager(binding.viewPager);

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                fragmengs.get(position).onStarShow();
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
        binding.viewPager.setCurrentItem(getIntent().getIntExtra(KEY, 0));
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector()
    {
        return fragmentInjector;
    }
}
