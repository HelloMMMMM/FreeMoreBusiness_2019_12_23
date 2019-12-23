package com.fx.feemore.business.ui.merchant.review;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.databinding.AcReviewBinding;
import com.fx.feemore.business.ui.merchant.review.adapter.AdReviewList;
import com.fx.feemore.business.ui.merchant.review.vm.VMReview;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.InputDialogUtil;
import com.fx.feemore.business.util.LoadManageUtil;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:运营审核界面
 * author: frj
 * modify date: 2019/5/14
 */
public class AcReview extends BaseBindActivity<AcReviewBinding, VMReview> implements SwipeRefreshLayout.OnRefreshListener
{

    private ProgressDialog dialog;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_review;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("运营审核");

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(this, 12), true, false, true, true));

        AdReviewList adReviewList = new AdReviewList();
        adReviewList.bindToRecyclerView(binding.recyclerView);
        adReviewList.setOnItemChildClickListener((adapter, view, position) -> {
            InterestBean bean = (InterestBean) adapter.getItem(position);
            if (bean != null)
            {
                if (R.id.btn_pass == view.getId())
                {
                    AlertDialogUtil.create(this, "您确定发布该权益包吗？", (dialog1, which) -> {
                        if (dialog == null)
                        {
                            dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
                        }
                        dialog.show();
                        viewModel.interestPass(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), AppContext.getInstanceBase().getStoryUserBean().getZIACCOUNT(), bean.getCARD_ID());
                    }, null).show();
                } else if (R.id.btn_refuse == view.getId())
                {
                    refused(bean.getCARD_ID());
                }
            }
        });
        adReviewList.setOnLoadMoreListener(() ->
                        viewModel.getData(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), viewModel.currPage + 1)
                , binding.recyclerView);

        initLiveData();

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initLiveData()
    {
        viewModel.failRes.observe(this, httpFailBean -> {
            if (binding.swipeRefreshLayout != null)
            {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (dialog != null)
            {
                dialog.dismiss();
            }
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.interestsRes.observe(this, list ->
                LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdReviewList) binding.recyclerView.getAdapter(), viewModel.currPage, list)
        );

        viewModel.operationRes.observe(this, responseBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            binding.swipeRefreshLayout.setRefreshing(true);
            onRefresh();
        });
    }


    /**
     * 审核不通过
     */
    private void refused(String interestId)
    {
        InputDialogUtil.createInputDialog(this, "驳回原因", input -> {
            if (dialog == null)
            {
                dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
            }
            if (!dialog.isShowing())
            {
                dialog.show();
            }
            viewModel.interestRefuse(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), AppContext.getInstanceBase().getStoryUserBean().getACCOUNT(), interestId, input);
        }).show();
    }

    @Override
    public void onRefresh()
    {
        viewModel.getData(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), PAGE_START);
    }
}
