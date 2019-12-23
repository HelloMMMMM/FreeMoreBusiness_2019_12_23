package com.fx.feemore.business.ui.home.fragment.interest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.FInterestItemBinding;
import com.fx.feemore.business.ui.home.vm.VMInterestItem;
import com.fx.feemore.business.util.SpaceDecorationUtil;

/**
 * function:权益包列表基类
 * author: frj
 * modify date: 2018/12/30
 */
public abstract class FInterestItem<T extends FInterestItemBinding, K extends VMInterestItem> extends FragmentViewPagerBase<T, K> implements SwipeRefreshLayout.OnRefreshListener
{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);
        initData();
    }

    private void initData()
    {
        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(getResources().getDimensionPixelSize(R.dimen.order_item_btn_mtb), false, false, false));
        BaseQuickAdapter adapter = getAdapter();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnLoadMoreListener(() -> {
            viewModel.getData(viewModel.currPage + 1);
        }, binding.recyclerView);

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    /**
     * 绑定当前页面数据类型
     */
    protected abstract void bindType();


    /**
     * 获取列表适配器
     *
     * @return 订单列表适配器对象
     */
    @NonNull
    protected abstract BaseQuickAdapter getAdapter();

    @Override
    public void onStarShow()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.f_interest_item;
    }

    @Override
    public void onRefresh()
    {
        viewModel.getData(PAGE_START);
    }
}
