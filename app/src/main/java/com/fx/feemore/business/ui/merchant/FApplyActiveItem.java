package com.fx.feemore.business.ui.merchant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.bean.ActiveBean;
import com.fx.feemore.business.databinding.FApplyActiveItemBinding;
import com.fx.feemore.business.ui.merchant.adapter.AdApplyActiveItem;
import com.fx.feemore.business.ui.merchant.vm.VMApplyActiveItem;

import java.util.ArrayList;
import java.util.List;

/**
 * function:活动申请进度项
 * author: frj
 * modify date: 2019/1/2
 */
public class FApplyActiveItem extends FragmentViewPagerBase<FApplyActiveItemBinding, VMApplyActiveItem> implements SwipeRefreshLayout.OnRefreshListener
{

    public static FApplyActiveItem newInstance(int status)
    {
        FApplyActiveItem fragment = new FApplyActiveItem();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, status);
        return fragment;
    }

    @Override
    public void onStarShow()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.f_apply_active_item;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);
        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdApplyActiveItem adapter = new AdApplyActiveItem();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            jump(AcApplyActiveDetail.class);
        });
        adapter.setOnLoadMoreListener(() -> {
        }, binding.recyclerView);
        adapter.setNewData(getList());

    }

    private List<ActiveBean> getList()
    {
        List<ActiveBean> list = new ArrayList<>();
        list.add(new ActiveBean());
        list.add(new ActiveBean());
        list.add(new ActiveBean());
        list.add(new ActiveBean());
        return list;
    }

    @Override
    public void onRefresh()
    {

    }
}
