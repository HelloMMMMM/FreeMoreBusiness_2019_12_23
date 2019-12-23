package com.fx.feemore.business.ui.merchant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.databinding.FApplyInterestBinding;
import com.fx.feemore.business.ui.merchant.adapter.AdApplyInterestItem;
import com.fx.feemore.business.ui.merchant.vm.VMApplyActiveItem;
import com.fx.feemore.business.ui.merchant.vm.VMApplyInterest;
import com.fx.feemore.business.ui.merchant.vm.VMApplyInterestItem;

import java.util.ArrayList;
import java.util.List;

/**
 * function:我的权益包申请进度Fragment
 * author: frj
 * modify date: 2019/1/2
 */
public class FApplyInterest extends FragmentViewPagerBase<FApplyInterestBinding, VMApplyInterestItem> implements SwipeRefreshLayout.OnRefreshListener
{

    public static FApplyInterest newInstance(int status)
    {
        FApplyInterest fragment = new FApplyInterest();
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
        return R.layout.f_apply_interest;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);
        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        AdApplyInterestItem adapter = new AdApplyInterestItem();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            jump(AcApplyInterestDetails.class);
        });
        adapter.setOnLoadMoreListener(() -> {
        }, binding.recyclerView);
        adapter.setNewData(getList());

    }

    private List<InterestBean> getList()
    {
        List<InterestBean> list = new ArrayList<>();
        list.add(new InterestBean());
        list.add(new InterestBean());
        list.add(new InterestBean());
        list.add(new InterestBean());
        return list;
    }

    @Override
    public void onRefresh()
    {

    }
}
