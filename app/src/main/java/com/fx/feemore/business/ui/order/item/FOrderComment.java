package com.fx.feemore.business.ui.order.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.FOrderCommentBinding;
import com.fx.feemore.business.ui.order.AcCommentDetail;
import com.fx.feemore.business.ui.order.adapter.AdOrderComment;
import com.fx.feemore.business.ui.order.bean.CommentBean;
import com.fx.feemore.business.ui.order.vm.VMOrderComment;
import com.fx.feemore.business.util.LoadManageUtil;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:订单评价
 * author: frj
 * modify date: 2019/1/17
 */
public class FOrderComment extends FragmentViewPagerBase<FOrderCommentBinding, VMOrderComment> implements SwipeRefreshLayout.OnRefreshListener {

    public static FOrderComment newInstance() {
        return new FOrderComment();
    }

    @Override
    public void onStarShow() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_order_comment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);

        initLiveData();

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(getActivity(), 12), true, false, true));

        AdOrderComment adapter = new AdOrderComment();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnLoadMoreListener(() -> viewModel.getComments(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), viewModel.currPage + 1, PAGE_NUM), binding.recyclerView);

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            CommentBean bean = (CommentBean) adapter1.getItem(position);
            if (bean != null) {
                jump(AcCommentDetail.class, bean.getCOMMENT_ID(), false);
            }
        });

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initLiveData() {
        viewModel.resComments.observe(this, list -> {
            LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdOrderComment) binding.recyclerView.getAdapter(), viewModel.currPage, list);
        });
        viewModel.resFail.observe(this, httpFailBean -> {
            if (binding.swipeRefreshLayout != null) {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (httpFailBean != null) {
                ToastUtil.show(httpFailBean.getText());
            }
        });
    }

    @Override
    public void onRefresh() {
        viewModel.getComments(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), PAGE_START, PAGE_NUM);
    }
}
