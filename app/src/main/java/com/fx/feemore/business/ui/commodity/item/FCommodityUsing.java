package com.fx.feemore.business.ui.commodity.item;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.databinding.FCommodityUsingBinding;
import com.fx.feemore.business.ui.commodity.adapter.AdCommodityUsing;
import com.fx.feemore.business.ui.commodity.vm.VMCommodityUsing;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.LoadManageUtil;
import com.fx.feemore.business.util.PermissionUtil;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:商品管理-发布中
 * author: frj
 * modify date: 2019/1/18
 */
public class FCommodityUsing extends FragmentViewPagerBase<FCommodityUsingBinding, VMCommodityUsing> implements SwipeRefreshLayout.OnRefreshListener
{

    private ProgressDialog dialog;

    public static FCommodityUsing newInstance()
    {
        return new FCommodityUsing();
    }

    @Override
    public void onStarShow()
    {

    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.f_commodity_using;
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

        AdCommodityUsing adapter = new AdCommodityUsing();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnLoadMoreListener(() -> {
            viewModel.getInterestList(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), viewModel.currPage + 1, PAGE_NUM);
        }, binding.recyclerView);
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            InterestBean bean = (InterestBean) adapter1.getItem(position);
            if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_OPERATOR))
            {
                AlertDialogUtil.createPermissionTips(getActivity()).show();
                return;
            }
            if (bean != null)
            {
                if (R.id.btn_removed == view.getId())
                {
                    AlertDialogUtil.create(getActivity(), "您确定下架该权益包吗？", (dialog1, which) -> removeInterest(bean.getCARD_ID()), null).show();
                }
            }
        });

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
            if (dialog != null)
            {
                dialog.dismiss();
            }
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });

        viewModel.resInterestList.observe(this, interestListBean -> {
            if (interestListBean != null)
            {
                LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdCommodityUsing) binding.recyclerView.getAdapter(), viewModel.currPage, interestListBean.getListStoreGoodsCard());
            }
        });

        viewModel.resResponseBean.observe(this, responseBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            binding.swipeRefreshLayout.setRefreshing(true);
            onRefresh();
        });
    }


    /**
     * 下架权益包
     *
     * @param cardId 权益包id
     */
    private void removeInterest(String cardId)
    {
        if (dialog == null)
        {
            dialog = ProgressDialogUtil.getProgressDialog(getActivity(), getString(R.string.progress_tips), true);
        }
        if (!dialog.isShowing())
        {
            dialog.show();
        }
        viewModel.removeInterest(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), cardId);
    }

    @Override
    public void onRefresh()
    {
        viewModel.getInterestList(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), PAGE_START, PAGE_NUM);
    }
}
