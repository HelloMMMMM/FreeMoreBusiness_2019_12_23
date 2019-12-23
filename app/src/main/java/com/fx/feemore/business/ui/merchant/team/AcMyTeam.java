package com.fx.feemore.business.ui.merchant.team;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.AcMyTeamBinding;
import com.fx.feemore.business.ui.home.adapter.AdHomeItem;
import com.fx.feemore.business.ui.merchant.team.item.FTeamLevel;
import com.fx.feemore.business.ui.merchant.team.vm.VMMyTeam;
import com.fx.feemore.business.ui.merchant.team.vm.VMTeamLevel;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * function:我的团队
 * author: frj
 * modify date: 2019/1/22
 */
public class AcMyTeam extends BaseBindActivity<AcMyTeamBinding, VMMyTeam> implements HasSupportFragmentInjector
{

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_my_team;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("我的团队");

        binding.tvTabOne.setOnClickListener(v -> {
            binding.tvTabOne.setSelected(true);
            binding.tvTabTwo.setSelected(false);
            binding.viewPager.setCurrentItem(0);
        });

        binding.tvTabTwo.setOnClickListener(v -> {
            binding.tvTabOne.setSelected(false);
            binding.tvTabTwo.setSelected(true);
            binding.viewPager.setCurrentItem(1);
        });

        final List<FragmentViewPagerBase> fragmengs = new ArrayList<>(2);
        fragmengs.add(FTeamLevel.newInstance(VMTeamLevel.LEVEL_ONE));
        fragmengs.add(FTeamLevel.newInstance(VMTeamLevel.LEVEL_TWO));
        AdHomeItem adHomeItem = new AdHomeItem(getSupportFragmentManager(), fragmengs, null);
        binding.viewPager.setAdapter(adHomeItem);
        binding.tvTabOne.performClick();

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int i, float v, int i1)
            {

            }

            @Override
            public void onPageSelected(int i)
            {
                if (i == 0)
                {
                    binding.tvTabOne.setSelected(true);
                    binding.tvTabTwo.setSelected(false);
                } else
                {
                    binding.tvTabOne.setSelected(false);
                    binding.tvTabTwo.setSelected(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i)
            {

            }
        });
    }

    /**
     * 设置值
     *
     * @param totalIncome 总收益
     * @param m1Num       一级交易数
     * @param m2Num       二级交易数
     */
    public void setValue(String totalIncome, String m1Num, String m2Num)
    {
        VerificationUtil.setViewValue(binding.tvIncome, totalIncome);
        VerificationUtil.setViewValue(binding.tvTabOne, String.format(getString(R.string.team_level_tab_one), m1Num));
        VerificationUtil.setViewValue(binding.tvTabTwo, String.format(getString(R.string.team_level_tab_two), m2Num));
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector()
    {
        return fragmentInjector;
    }
}
