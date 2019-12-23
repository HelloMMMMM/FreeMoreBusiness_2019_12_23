package com.fx.feemore.business.ui.order;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.AcOrderManagerBinding;
import com.fx.feemore.business.ui.home.adapter.AdHomeItem;
import com.fx.feemore.business.ui.order.adapter.AdOrderFilter;
import com.fx.feemore.business.ui.order.bean.OrderFilterBean;
import com.fx.feemore.business.ui.order.item.FOrderComment;
import com.fx.feemore.business.ui.order.item.FOrderComplete;
import com.fx.feemore.business.ui.order.item.FOrderRightsProtection;
import com.fx.feemore.business.ui.order.item.FOrderWaitGroup;
import com.fx.feemore.business.ui.order.vm.VMOrderManager;
import com.hengxian.baselib.base.BaseBindActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * function:订单管理
 * author: frj
 * modify date: 2019/1/17
 */
public class AcOrderManager extends BaseBindActivity<AcOrderManagerBinding, VMOrderManager> implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_order_manager;
    }

    @Override
    protected void init() {
        binding.setVm(viewModel);
        setToolbarTitle("订单管理");

        String[] tabTitles = getResources().getStringArray(R.array.order_manager_tabs);
        final List<FragmentViewPagerBase> fragmengs = new ArrayList<>(tabTitles.length);
        fragmengs.add(FOrderComment.newInstance());
        fragmengs.add(FOrderWaitGroup.newInstance());
        fragmengs.add(FOrderRightsProtection.newInstance());
        fragmengs.add(FOrderComplete.newInstance());
        AdHomeItem adapter = new AdHomeItem(getSupportFragmentManager(), fragmengs, tabTitles);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(tabTitles.length);
        binding.tabs.setViewPager(binding.viewPager);

        List<OrderFilterBean> orderFilterBeans = new ArrayList<>(4);
        orderFilterBeans.add(new OrderFilterBean("全部", 1));
        orderFilterBeans.add(new OrderFilterBean("权益包", 2));
        orderFilterBeans.add(new OrderFilterBean("定制卡", 3));
        orderFilterBeans.add(new OrderFilterBean("活动券", 4));
        AdOrderFilter adOrderFilter = new AdOrderFilter(R.layout.layout_order_list_filter_item, orderFilterBeans);
        binding.filterList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adOrderFilter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        binding.filterList.setAdapter(adOrderFilter);

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
