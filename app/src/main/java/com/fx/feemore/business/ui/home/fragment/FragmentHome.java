package com.fx.feemore.business.ui.home.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.FHomeBinding;
import com.fx.feemore.business.ui.home.adapter.AdHomeItem;
import com.fx.feemore.business.ui.home.fragment.order.FOrderComplete;
import com.fx.feemore.business.ui.home.fragment.order.FOrderReservation;
import com.fx.feemore.business.ui.home.fragment.order.FOrderRightsProtection;
import com.fx.feemore.business.ui.home.fragment.order.FOrderWaitConsume;
import com.fx.feemore.business.ui.home.vm.VMHome;
import com.hengxian.baselib.base.BaseBindFragment;
import com.hengxian.common.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * function:首页
 * author: frj
 * modify date: 2018/12/24
 */
public class FragmentHome extends BaseBindFragment<FHomeBinding, VMHome>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.f_home;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);
        init();
    }

    private void init()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.vHead.getLayoutParams();
            if (params != null)
            {
                params.height += DisplayUtil.getStatusBarHeight(getActivity());
                binding.vHead.setLayoutParams(params);
            }
        }

        String[] tabTitles = getResources().getStringArray(R.array.home_tabs);
        final List<FragmentViewPagerBase> fragmengs = new ArrayList<>(tabTitles.length);
        fragmengs.add(FOrderReservation.newInstance());
        fragmengs.add(FOrderWaitConsume.newInstance());
        fragmengs.add(FOrderRightsProtection.newInstance());
        fragmengs.add(FOrderComplete.newInstance());
        AdHomeItem adapter = new AdHomeItem(getChildFragmentManager(), fragmengs, tabTitles);
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
        binding.viewPager.setCurrentItem(0);
    }
}
