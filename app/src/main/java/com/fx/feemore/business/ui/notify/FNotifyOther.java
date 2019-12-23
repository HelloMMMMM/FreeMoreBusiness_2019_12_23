package com.fx.feemore.business.ui.notify;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.FNotifyOtherBinding;
import com.fx.feemore.business.ui.notify.adapter.AdNotifyOther;
import com.fx.feemore.business.ui.notify.vm.VMNotifyOther;
import com.hengxian.common.DensityUtil;

/**
 * function:其他通知
 * author: frj
 * modify date: 2019/1/17
 */
public class FNotifyOther extends FragmentViewPagerBase<FNotifyOtherBinding, VMNotifyOther> implements SwipeRefreshLayout.OnRefreshListener
{

    public static FNotifyOther newInstance()
    {
        return new FNotifyOther();
    }

    @Override
    public void onStarShow()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.f_notify_other;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdNotifyOther adapter = new AdNotifyOther();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnLoadMoreListener(() -> {
        }, binding.recyclerView);
        adapter.setEmptyView(createEmptyView());
    }

    private View createEmptyView()
    {
        FrameLayout view = new FrameLayout(getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setMinimumHeight(DensityUtil.dip2px(getContext(), 200));
        TextView textView = new TextView(getContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        textView.setLayoutParams(params);
        textView.setText("暂无通知");
        textView.setTextColor(Color.parseColor("#A0A0A0"));
        textView.setTextSize(13);
        textView.setCompoundDrawablePadding(DensityUtil.dip2px(getContext(), 16));
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_notify_empty, 0, 0);
        view.addView(textView);
        return view;
    }

    @Override
    public void onRefresh()
    {

    }
}
