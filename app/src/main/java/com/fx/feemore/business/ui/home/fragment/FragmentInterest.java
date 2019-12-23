package com.fx.feemore.business.ui.home.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.FInterestBinding;
import com.fx.feemore.business.ui.home.adapter.AdHomeItem;
import com.fx.feemore.business.ui.home.fragment.interest.FConsumeRecord;
import com.fx.feemore.business.ui.home.fragment.interest.FMyInterest;
import com.fx.feemore.business.ui.home.vm.VMInterest;
import com.fx.feemore.business.ui.interest.AcPublishInterest;
import com.hengxian.baselib.base.BaseBindFragment;
import com.hengxian.common.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * function:权益包
 * author: frj
 * modify date: 2018/12/24
 */
public class FragmentInterest extends BaseBindFragment<FInterestBinding, VMInterest> implements View.OnClickListener
{

    /**
     * 我的权益包索引
     */
    private static final int INDEX_MY_INTEREST = 0;

    /**
     * 消费记录索引
     */
    public static final int INDEX_CONSUME_RECORD = 1;

    @Override
    protected int getLayoutId()
    {
        return R.layout.f_interest;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);
        binding.tvConsumeRecord.setOnClickListener(this);
        binding.tvMyInterest.setOnClickListener(this);
        binding.tvApply.setOnClickListener(v -> jump(AcPublishInterest.class));
        initData();
    }

    private void initData()
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
        final List<FragmentViewPagerBase> fragmengs = new ArrayList<>(2);
        fragmengs.add(FMyInterest.newInstance());
        fragmengs.add(FConsumeRecord.newInstance());
        AdHomeItem adapter = new AdHomeItem(getChildFragmentManager(), fragmengs, null);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(fragmengs.size());
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
                if (position == INDEX_CONSUME_RECORD)
                {
                    switchItem(binding.tvConsumeRecord);
                } else if (position == INDEX_MY_INTEREST)
                {
                    switchItem(binding.tvMyInterest);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
    }

    /**
     * 切换选中项
     *
     * @param textView 选中的项
     */
    private void switchItem(View textView)
    {
        if (binding.tvMyInterest == textView)
        {
            selectTabs(binding.tvMyInterest);
            unselectTabs(binding.tvConsumeRecord);
        } else if (binding.tvConsumeRecord == textView)
        {
            selectTabs(binding.tvConsumeRecord);
            unselectTabs(binding.tvMyInterest);
        }
    }

    /**
     * 设置选中的Tab项的样式
     *
     * @param textView tab项
     */
    private void selectTabs(TextView textView)
    {
        if (textView == null)
        {
            return;
        }
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.interest_tabs_text_sel));
        textView.setTextColor(Color.WHITE);
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.mipmap.ic_tabs_mark);
    }

    /**
     * 设置选中的Tab项的样式
     *
     * @param textView tab项
     */
    private void unselectTabs(TextView textView)
    {
        if (textView == null)
        {
            return;
        }
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.interest_tabs_text_unsel));
        textView.setTextColor(getResources().getColor(R.color.interest_tabs_text_unsel));
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    @Override
    public void onClick(View v)
    {
        if (isFastDoubleClick(v))
        {
            return;
        }
        switch (v.getId())
        {
            case R.id.tv_my_interest:
                switchItem(v);
                binding.viewPager.setCurrentItem(INDEX_MY_INTEREST);
                break;
            case R.id.tv_consume_record:
                switchItem(v);
                binding.viewPager.setCurrentItem(INDEX_CONSUME_RECORD);
                break;
        }
    }
}
