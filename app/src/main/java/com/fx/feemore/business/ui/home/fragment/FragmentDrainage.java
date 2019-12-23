package com.fx.feemore.business.ui.home.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.FDrainageBinding;
import com.fx.feemore.business.ui.home.adapter.AdVPNews;
import com.fx.feemore.business.ui.home.vm.VMHome;
import com.hengxian.baselib.base.BaseBindFragment;
import com.hengxian.common.DisplayUtil;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class FragmentDrainage extends BaseBindFragment<FDrainageBinding, VMHome> {
    @Override
    protected int getLayoutId() {
        return R.layout.f_drainage;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        BarUtils.setStatusBarLightMode(getActivity(), hidden);
        BarUtils.setStatusBarColor(getActivity(), hidden ? 0xffffffff : 0xffF72424);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            binding.header.setPadding(0, DisplayUtil.getStatusBarHeight(getActivity()), 0, 0);
        }

        binding.tvStep1.setSelected(true);
        binding.tvStep.setText(R.string.drainage_card_step_1);

        String[] tabTitles = new String[]{"热门", "技巧", "服务", "美食", "零售"};
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return tabTitles.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(0xff333333);
                colorTransitionPagerTitleView.setSelectedColor(0xffF72424);
                colorTransitionPagerTitleView.setTextSize(18);
                colorTransitionPagerTitleView.setText(tabTitles[index]);
                colorTransitionPagerTitleView.setPadding(SizeUtils.dp2px(16), 0, SizeUtils.dp2px(16), 0);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        binding.vp.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setColors(0xffF72424);
                indicator.setLineWidth(SizeUtils.dp2px(25));
                indicator.setLineHeight(SizeUtils.dp2px(2.5f));
                indicator.setRoundRadius(SizeUtils.dp2px(1.25f));
                return indicator;
            }
        });
        binding.tab.setNavigator(commonNavigator);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentNews());
        fragments.add(new FragmentNews());
        fragments.add(new FragmentNews());
        fragments.add(new FragmentNews());
        fragments.add(new FragmentNews());
        binding.vp.setAdapter(new AdVPNews(getChildFragmentManager(), fragments));

        ViewPagerHelper.bind(binding.tab, binding.vp);
    }
}
