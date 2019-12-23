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
import com.fx.feemore.business.bean.NotifyBean;
import com.fx.feemore.business.databinding.FNotifyOrderBinding;
import com.fx.feemore.business.ui.notify.adapter.AdNotifyOrder;
import com.fx.feemore.business.ui.notify.vm.VMNotifyOrder;
import com.fx.feemore.business.ui.order.AcOrderManager;
import com.fx.feemore.business.util.LoadManageUtil;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:订单通知
 * author: frj
 * modify date: 2019/1/17
 */
public class FNotifyOrder extends FragmentViewPagerBase<FNotifyOrderBinding, VMNotifyOrder> implements SwipeRefreshLayout.OnRefreshListener
{

    public static FNotifyOrder newInstance()
    {
        return new FNotifyOrder();
    }

    @Override
    public void onStarShow()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.f_notify_order;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdNotifyOrder adapter = new AdNotifyOrder();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnLoadMoreListener(() -> {
            viewModel.getData(viewModel.currPage + 1, PAGE_NUM);
        }, binding.recyclerView);
        adapter.setEmptyView(createEmptyView());
//        adapter.setOnItemClickListener((adapter1, view, position) -> {
//            NotifyBean bean = (NotifyBean) adapter1.getItem(position);
//            if (bean != null)
//            {
//                jump(AcOrderManager.class);
//            }
//        });

        initLiveData();

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initLiveData()
    {
        viewModel.failData.observe(this, httpFailBean -> {
            if (binding.swipeRefreshLayout != null)
            {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });

        viewModel.notifyBeansData.observe(this, list -> {
            LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdNotifyOrder) binding.recyclerView.getAdapter(), viewModel.currPage, list);
        });
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
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setCompoundDrawablePadding(DensityUtil.dip2px(getContext(), 16));
        textView.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_notify_empty, 0, 0);
        view.addView(textView);
        return view;
    }

    @Override
    public void onRefresh()
    {
        viewModel.getData(PAGE_START, PAGE_NUM);
    }
}
