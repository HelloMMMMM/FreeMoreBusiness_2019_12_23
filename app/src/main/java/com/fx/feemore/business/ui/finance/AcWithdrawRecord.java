package com.fx.feemore.business.ui.finance;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.bean.WithdrawRecordBean;
import com.fx.feemore.business.databinding.AcWithdrawRecordBinding;
import com.fx.feemore.business.ui.finance.adapter.AdWithdrawRecord;
import com.fx.feemore.business.ui.finance.vm.VMWithdrawRecord;
import com.fx.feemore.business.util.LoadManageUtil;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:提现记录
 * author: frj
 * modify date: 2019/1/20
 */
public class AcWithdrawRecord extends BaseBindActivity<AcWithdrawRecordBinding, VMWithdrawRecord> implements SwipeRefreshLayout.OnRefreshListener
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_withdraw_record;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("提现记录");

        binding.swipeRefreshLayout.setOnRefreshListener(this);

        initLiveData();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(this, 18), true, false, true));

        AdWithdrawRecord adapter = new AdWithdrawRecord();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnLoadMoreListener(() -> viewModel.getData(viewModel.currPage + 1, PAGE_NUM), binding.recyclerView);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            WithdrawRecordBean bean = (WithdrawRecordBean) adapter1.getItem(position);
            if (bean != null)
            {
                jump(AcWithdrawDetail.class, bean.getWithdrawrecord_ID());
            }
        });

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initLiveData()
    {
        viewModel.resHttpFail.observe(this, httpFailBean -> {
            if (binding.swipeRefreshLayout != null)
            {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });

        viewModel.resRecords.observe(this, list ->
                LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdWithdrawRecord) binding.recyclerView.getAdapter(), viewModel.currPage, list)
        );
    }

    @Override
    public void onRefresh()
    {
        viewModel.getData(PAGE_START, PAGE_NUM);
    }
}
