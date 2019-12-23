package com.fx.feemore.business.ui.apply.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.databinding.FApplyItemBinding;
import com.fx.feemore.business.ui.apply.AcApplyDetail;
import com.fx.feemore.business.ui.apply.adapter.AdApplyItem;
import com.fx.feemore.business.ui.apply.vm.VMApplyItem;
import com.fx.feemore.business.util.DataLoadUtil;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.common.DensityUtil;

/**
 * function:权益包申请记录
 * author: frj
 * modify date: 2019/1/18
 */
public class FApplyItem extends FragmentViewPagerBase<FApplyItemBinding, VMApplyItem> implements SwipeRefreshLayout.OnRefreshListener
{

    public static FApplyItem newInstance(int status)
    {
        FApplyItem fragment = new FApplyItem();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, status);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onStarShow()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.f_apply_item;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(getContext(), 12), true, false, true, true));

        AdApplyItem adapter = new AdApplyItem();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            InterestBean bean = (InterestBean) adapter1.getItem(position);
            if (bean != null)
            {
                Bundle bundle = new Bundle();
                bundle.putString(KEY, bean.getCARD_ID());
                bundle.putString(KEY_STR, bean.getNAME());
                jump(AcApplyDetail.class, bundle, false);
            }
        });
        adapter.setOnLoadMoreListener(() ->
                getData(viewModel.currPage + 1), binding.recyclerView);

        initLiveData();

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initLiveData()
    {
        viewModel.failRes.observe(this, httpFailBean -> DataLoadUtil.loadFail(binding.swipeRefreshLayout, httpFailBean));
        viewModel.interestsRes.observe(this, list -> DataLoadUtil.loadData(binding.swipeRefreshLayout, (AdApplyItem) binding.recyclerView.getAdapter(), list, viewModel.currPage));
    }

    /**
     * 获取数据
     *
     * @param page 页码
     */
    private void getData(int page)
    {
        int status = VMApplyItem.STATUS_APPLICATION;
        if (getArguments() != null)
        {
            status = getArguments().getInt(KEY, VMApplyItem.STATUS_APPLICATION);
        }
        viewModel.getData(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), status, page);
    }

    @Override
    public void onRefresh()
    {
        getData(PAGE_START);
    }
}
