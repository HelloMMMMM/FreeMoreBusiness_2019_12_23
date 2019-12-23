package com.fx.feemore.business.ui.merchant;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcMyActiveBinding;
import com.fx.feemore.business.ui.merchant.adapter.AdMyActive;
import com.fx.feemore.business.ui.merchant.vm.VMMyActive;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * function:我的活动
 * author: frj
 * modify date: 2018/12/31
 */
public class AcMyActive extends BaseBindActivity<AcMyActiveBinding, VMMyActive> implements SwipeRefreshLayout.OnRefreshListener
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_my_active;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.my_active_title);

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(this, 6), false, false, false));
        AdMyActive adapter = new AdMyActive();
        adapter.setNewData(getList());
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnLoadMoreListener(() -> {
        }, binding.recyclerView);
        adapter.setOnItemClickListener((adapter1, view, position) -> jump(AcActiveDetail.class));
        binding.tvInitate.setOnClickListener(v -> jump(AcInitateActive.class));

    }

    private List<String> getList()
    {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        return list;
    }

    @Override
    public void onRefresh()
    {

    }
}
