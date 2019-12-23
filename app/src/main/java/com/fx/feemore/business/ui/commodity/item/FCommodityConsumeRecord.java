package com.fx.feemore.business.ui.commodity.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.bean.ConsumeRecordBean;
import com.fx.feemore.business.databinding.FCommodityConsumeRecordBinding;
import com.fx.feemore.business.ui.commodity.AcConsumeDetail;
import com.fx.feemore.business.ui.commodity.adapter.AdCommodityConsumeRecord;
import com.fx.feemore.business.ui.commodity.vm.VMCommodityConsumeRecord;
import com.fx.feemore.business.util.LoadManageUtil;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:消费记录
 * author: frj
 * modify date: 2019/3/1
 */
public class FCommodityConsumeRecord extends FragmentViewPagerBase<FCommodityConsumeRecordBinding, VMCommodityConsumeRecord> implements SwipeRefreshLayout.OnRefreshListener {

    public static FCommodityConsumeRecord newInstance() {
        return new FCommodityConsumeRecord();
    }

    @Override
    public void onStarShow() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_commodity_consume_record;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);

        initLiveData();

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(getActivity(), 12), true, false, true));
        AdCommodityConsumeRecord adCommodityConsumeRecord = new AdCommodityConsumeRecord();
        adCommodityConsumeRecord.bindToRecyclerView(binding.recyclerView);
        adCommodityConsumeRecord.setOnItemClickListener((adapter, view, position) -> {
            ConsumeRecordBean bean = (ConsumeRecordBean) adapter.getItem(position);
            if (bean != null) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(KEY, bean);
                jump(AcConsumeDetail.class, bundle, false);
            }
        });

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initLiveData() {
        viewModel.resConsumeRecord.observe(this, consumeRecordRes -> {
            if (consumeRecordRes != null) {
                LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdCommodityConsumeRecord) binding.recyclerView.getAdapter(), viewModel.currPage, consumeRecordRes.getListUseCards());
            }
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
        viewModel.getData(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), PAGE_START, PAGE_NUM);
    }
}
