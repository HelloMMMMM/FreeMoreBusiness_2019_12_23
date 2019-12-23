package com.fx.feemore.business.ui.order.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.FOrderWaitGroupBinding;
import com.fx.feemore.business.ui.order.AcWaitGroupDetail;
import com.fx.feemore.business.ui.order.adapter.AdOrderWaitGroup;
import com.fx.feemore.business.ui.order.vm.VMOrderWaitGroup;
import com.fx.feemore.business.util.LoadManageUtil;
import com.fx.feemore.business.util.ShareUtils;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:订单管理-待成团界面
 * author: frj
 * modify date: 2019/1/18
 */
public class FOrderWaitGroup extends FragmentViewPagerBase<FOrderWaitGroupBinding, VMOrderWaitGroup> implements SwipeRefreshLayout.OnRefreshListener
{

    public static FOrderWaitGroup newInstance()
    {
        return new FOrderWaitGroup();
    }

    @Override
    public void onStarShow()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.f_order_wait_group;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);

        initLiveData();

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(getActivity(), 12), true, false, true));

        AdOrderWaitGroup adapter = new AdOrderWaitGroup();
        adapter.bindToRecyclerView(binding.recyclerView);

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            OrderBean orderBean = (OrderBean) adapter1.getItem(position);
            if (orderBean != null)
            {
                Bundle bundle = new Bundle();
                bundle.putParcelable(KEY, orderBean);
                jump(AcWaitGroupDetail.class, bundle, false);
            }
        });
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
//            ShareUtils.callWxMiniProgramRegister(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID());
            OrderBean bean = (OrderBean) adapter1.getItem(position);
            if (bean != null)
            {
                ShareUtils.callWxMiniProgramGroup(bean.getORDER_ID());
            }
        });
        adapter.setOnLoadMoreListener(() -> viewModel.getData(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), viewModel.currPage + 1, PAGE_NUM), binding.recyclerView);

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
        viewModel.resOrderBean.observe(this, list -> {
            LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdOrderWaitGroup) binding.recyclerView.getAdapter(), viewModel.currPage, list);
        });
    }

    @Override
    public void onRefresh()
    {
        viewModel.getData(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), PAGE_START, PAGE_NUM);
    }
}
