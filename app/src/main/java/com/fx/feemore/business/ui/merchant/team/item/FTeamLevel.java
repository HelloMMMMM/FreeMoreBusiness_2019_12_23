package com.fx.feemore.business.ui.merchant.team.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.FTeamLevelBinding;
import com.fx.feemore.business.ui.merchant.team.AcMyTeam;
import com.fx.feemore.business.ui.merchant.team.adapter.AdTeamLevelItem;
import com.fx.feemore.business.ui.merchant.team.vm.VMTeamLevel;
import com.fx.feemore.business.util.LoadManageUtil;
import com.hengxian.baselib.utils.Arithmetic;
import com.hengxian.common.ToastUtil;

/**
 * function:我的团队-推荐数据列表
 * author: frj
 * modify date: 2019/1/22
 */
public class FTeamLevel extends FragmentViewPagerBase<FTeamLevelBinding, VMTeamLevel> implements SwipeRefreshLayout.OnRefreshListener
{
    public static FTeamLevel newInstance(int level)
    {
        FTeamLevel fTeamLevel = new FTeamLevel();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, level);
        fTeamLevel.setArguments(bundle);
        return fTeamLevel;
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.f_team_level;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);

        initLiveData();

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdTeamLevelItem adapter = new AdTeamLevelItem();
        adapter.bindToRecyclerView(binding.recyclerView);

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
        viewModel.resTeamIncome.observe(this, bean -> {
            if (bean != null)
            {
                LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdTeamLevelItem) binding.recyclerView.getAdapter(), viewModel.currPage, bean.getListPerformance());
                AcMyTeam acMyTeam = (AcMyTeam) getActivity();
                acMyTeam.setValue(Arithmetic.doubleToStr(bean.getTOTAL(), 2), String.valueOf(bean.getM1NUMS()),String.valueOf(bean.getM2NUMS()));
            }
        });
    }

    @Override
    public void onRefresh()
    {
        if (getArguments() != null)
        {
            viewModel.getData(PAGE_START, PAGE_NUM, getArguments().getInt(KEY));
        }
    }

    @Override
    public void onStarShow()
    {

    }
}
