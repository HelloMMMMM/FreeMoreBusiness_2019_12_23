package com.fx.feemore.business.ui.order.item;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.bean.OrderRefundBean;
import com.fx.feemore.business.databinding.FOrderRightsProtectionBinding;
import com.fx.feemore.business.ui.order.AcRightsProtection;
import com.fx.feemore.business.ui.order.adapter.AdOrderRightsProtection;
import com.fx.feemore.business.ui.order.vm.VMOrderRightsProtection;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.InputDialogUtil;
import com.fx.feemore.business.util.LoadManageUtil;
import com.fx.feemore.business.util.PermissionUtil;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:订单管理-维权
 * author: frj
 * modify date: 2019/1/18
 */
public class FOrderRightsProtection extends FragmentViewPagerBase<FOrderRightsProtectionBinding, VMOrderRightsProtection> implements SwipeRefreshLayout.OnRefreshListener {

    private ProgressDialog dialog;

    public static FOrderRightsProtection newInstance() {
        return new FOrderRightsProtection();
    }

    @Override
    public void onStarShow() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_order_rights_protection;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);

        initLiveData();

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(getActivity(), 12), true, false, true));

        AdOrderRightsProtection adapter = new AdOrderRightsProtection();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnLoadMoreListener(() -> {
            viewModel.getOrders(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), viewModel.currPage + 1, PAGE_NUM);
        }, binding.recyclerView);

        adapter.setOnItemClickListener((adapter1, view, position) -> {
            OrderRefundBean bean = (OrderRefundBean) adapter1.getItem(position);
            if (bean != null) {
                jump(AcRightsProtection.class, bean.getORDER_ID(), false);
            }
        });

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            OrderRefundBean bean = (OrderRefundBean) adapter1.getItem(position);
            if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_OPERATOR)) {
                AlertDialogUtil.createPermissionTips(getActivity()).show();
                return;
            }
            if (bean != null) {
                if (R.id.btn_agree == view.getId()) {
                    AlertDialogUtil.create(getContext(), "您确定要同意退款吗？", (dialog1, which) -> agreeRefund(bean.getORDER_ID()), null).show();
                } else if (R.id.btn_refuse == view.getId()) {
                    AlertDialogUtil.create(getContext(), "您确定要驳回退款吗？", (dialog1, which) -> refusedRefundInputReason(bean.getORDER_ID()), null).show();
                }
            }
        });

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initLiveData() {
        viewModel.resFail.observe(this, httpFailBean -> {
            if (binding.swipeRefreshLayout != null) {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (dialog != null) {
                dialog.dismiss();
            }
            if (httpFailBean != null) {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.resOrderRefundBean.observe(this, list -> {
            LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdOrderRightsProtection) binding.recyclerView.getAdapter(), viewModel.currPage, list);
        });
        viewModel.resResponseBean.observe(this, bean -> {
            if (dialog != null) {
                dialog.dismiss();
            }
            if (bean != null) {
                ToastUtil.show(bean.getMsg());
            }
            binding.swipeRefreshLayout.setRefreshing(true);
            onRefresh();
        });
    }

    /**
     * 同意维权订单退款
     *
     * @param orderId 订单id
     */
    private void agreeRefund(String orderId) {
        if (dialog == null) {
            dialog = ProgressDialogUtil.getProgressDialog(getActivity(), getString(R.string.progress_tips), true);
        }
        dialog.show();
        viewModel.agreeRefund(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), orderId);
    }

    /**
     * 驳回维权订单退款输入原因
     *
     * @param orderId 订单id
     */
    private void refusedRefundInputReason(String orderId) {
        InputDialogUtil.createInputDialog(getActivity(), "驳回原因", result ->
                refusedRefund(orderId, result)).show();
    }

    /**
     * 驳回维权订单退款
     *
     * @param orderId 订单id
     */
    private void refusedRefund(String orderId, String reason) {
        if (dialog == null) {
            dialog = ProgressDialogUtil.getProgressDialog(getActivity(), getString(R.string.progress_tips), true);
        }
        dialog.show();
        viewModel.refusedRefund(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), orderId, reason);
    }

    @Override
    public void onRefresh() {
        viewModel.getOrders(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), PAGE_START, PAGE_NUM);
    }
}
