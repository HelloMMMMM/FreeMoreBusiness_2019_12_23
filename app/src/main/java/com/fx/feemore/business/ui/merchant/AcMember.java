package com.fx.feemore.business.ui.merchant;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcMemberBinding;
import com.fx.feemore.business.ui.merchant.adapter.AdMemberItem;
import com.fx.feemore.business.ui.merchant.vm.VMMember;
import com.hengxian.baselib.base.BaseBindActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * function:会员活跃度列表
 * author: frj
 * modify date: 2018/12/31
 */
public class AcMember extends BaseBindActivity<AcMemberBinding, VMMember> implements SwipeRefreshLayout.OnRefreshListener
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_member;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.member_title);
        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdMemberItem adapter = new AdMemberItem();
        adapter.setNewData(getList());
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnLoadMoreListener(() -> {
        }, binding.recyclerView);
    }

    private List<String> getList()
    {
        List<String> list = new ArrayList<>();
        list.add("");
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
