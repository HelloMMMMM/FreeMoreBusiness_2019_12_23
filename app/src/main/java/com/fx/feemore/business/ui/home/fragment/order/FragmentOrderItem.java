package com.fx.feemore.business.ui.home.fragment.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.FOrderItemBinding;
import com.fx.feemore.business.ui.home.vm.VMOrderItem;
import com.fx.feemore.business.util.SpaceDecorationUtil;

/**
 * function:订单项
 * author: frj
 * modify date: 2018/12/24
 */
public abstract class FragmentOrderItem<T extends FOrderItemBinding, K extends VMOrderItem> extends FragmentViewPagerBase<T, K> implements SwipeRefreshLayout.OnRefreshListener
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

        if (getArguments() != null)
        {
            viewModel.setOrderType(getArguments().getInt(KEY));
        }
        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }


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
        return R.layout.f_order_item;
    }

    @Override
    public void onRefresh()
    {
        viewModel.getData(PAGE_START);
    }
}
