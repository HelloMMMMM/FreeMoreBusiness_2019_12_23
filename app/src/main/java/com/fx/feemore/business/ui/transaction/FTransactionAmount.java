package com.fx.feemore.business.ui.transaction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.FTransactionAmountBinding;
import com.fx.feemore.business.ui.transaction.adapter.AdTransactionAmount;
import com.fx.feemore.business.ui.transaction.vm.VMTransactionAmount;
import com.fx.feemore.business.util.LoadManageUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:交易额
 * author: frj
 * modify date: 2019/1/17
 */
public class FTransactionAmount extends FragmentViewPagerBase<FTransactionAmountBinding, VMTransactionAmount> implements SwipeRefreshLayout.OnRefreshListener
{

    public static FTransactionAmount newInstance()
    {
        return new FTransactionAmount();
    }

    @Override
    public void onStarShow()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.f_transaction_amount;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);
        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initLiveData();

        AdTransactionAmount adapter = new AdTransactionAmount();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnLoadMoreListener(() ->
                viewModel.getData(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), viewModel.currPage + 1, PAGE_NUM), binding.recyclerView);

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initLiveData()
    {
        viewModel.resFail.observe(this, httpFailBean -> {
            if (binding.swipeRefreshLayout != null)
            {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });

        viewModel.resList.observe(this, list -> LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdTransactionAmount) binding.recyclerView.getAdapter(), viewModel.currPage, list));
    }

    @Override
    public void onRefresh()
    {
        viewModel.getData(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), PAGE_START, PAGE_NUM);
    }
}
