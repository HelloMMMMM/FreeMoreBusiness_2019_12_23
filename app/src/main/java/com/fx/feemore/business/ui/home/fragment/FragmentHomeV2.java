package com.fx.feemore.business.ui.home.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.StoreStatisticInfoBean;
import com.fx.feemore.business.bean.StoryUserBean;
import com.fx.feemore.business.databinding.FHomeV2Binding;
import com.fx.feemore.business.ui.apply.AcApplyManager;
import com.fx.feemore.business.ui.apply.AcPayDeposit;
import com.fx.feemore.business.ui.commodity.AcCommodityManager;
import com.fx.feemore.business.ui.home.vm.VMHome;
import com.fx.feemore.business.ui.interest.AcPublishInterestV2;
import com.fx.feemore.business.ui.notify.AcNotifyManager;
import com.fx.feemore.business.ui.order.AcOrderManager;
import com.fx.feemore.business.ui.transaction.AcTransactionData;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.BroadCastUtil;
import com.fx.feemore.business.util.GlideLoad;
import com.fx.feemore.business.util.RequestAndResultCode;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindFragment;
import com.hengxian.common.DisplayUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:首页第二版
 * author: frj
 * modify date: 2019/1/18
 */
public class FragmentHomeV2 extends BaseBindFragment<FHomeV2Binding, VMHome> implements SwipeRefreshLayout.OnRefreshListener {

    public static FragmentHomeV2 newInstance() {
        return new FragmentHomeV2();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_home_v2;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.vHead.getLayoutParams();
            if (params != null) {
                params.height += DisplayUtil.getStatusBarHeight(getActivity());
                binding.vHead.setLayoutParams(params);
            }
        }
        initLiveData();
        initData();
        binding.swipeRefreshLayout.setOnRefreshListener(this);

        binding.ibNotify.setOnClickListener(v -> jump(AcNotifyManager.class));

//        binding.vTranArea.setOnClickListener(v -> jump(AcTransactionData.class));
        binding.vTranToday.setOnClickListener(this::tranAreaClick);
        binding.vTranVisitorToday.setOnClickListener(this::tranAreaClick);
        binding.vTranCollNum.setOnClickListener(this::tranAreaClick);
        binding.vTranTranNum.setOnClickListener(this::tranAreaClick);

//        binding.vOrderArea.setOnClickListener(v -> jump(AcOrderManager.class));

        binding.vOrderEvaluation.setOnClickListener(this::orderAreaClick);
        binding.vOrderGroup.setOnClickListener(this::orderAreaClick);
        binding.vOrderProtection.setOnClickListener(this::orderAreaClick);
        binding.vOrderComplete.setOnClickListener(this::orderAreaClick);

//        binding.vCommodityArea.setOnClickListener(v -> jump(AcCommodityManager.class));
        binding.vCommodityPublished.setOnClickListener(this::commodityAreaClick);
        binding.vCommodityRemoved.setOnClickListener(this::commodityAreaClick);
        binding.vCommodityConsumed.setOnClickListener(this::commodityAreaClick);

        binding.vCommodityAdd.setOnClickListener(v -> {
            if (AppContext.getInstanceBase().getStoryUserBean().getMARGIN() == 0) {
                AlertDialogUtil.create(getActivity(), "您还未缴纳服务保证金！缴纳服务保证金后才可发布权益包~", (dialog, which) -> jump(AcPayDeposit.class), null).show();
                return;
            }
            jump(AcPublishInterestV2.class, RequestAndResultCode.REQUEST_NEED_REFRESH_CODE);
        });

//        binding.vApplyArea.setOnClickListener(v -> jump(AcApplyManager.class));
        binding.vApplyIng.setOnClickListener(this::applyAreaClick);
        binding.vApplyReject.setOnClickListener(this::applyAreaClick);
        binding.vApplyPassed.setOnClickListener(this::applyAreaClick);
    }

    private void initData() {
        bindValue();
        refreshData();

        registerBroadcast();
    }

    private void initLiveData() {
        viewModel.resFail.observe(this, httpFailBean -> {
            if (binding.swipeRefreshLayout != null) {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (httpFailBean != null) {
                ToastUtil.show(httpFailBean.getText());
            }
        });

        viewModel.resStoreInfo.observe(this, this::bindData);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isVisible()) {
            bindValue();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isHidden()) {
            bindValue();
        }
    }

    /**
     * 绑定数据
     */
    private void bindData(StoreStatisticInfoBean bean) {
        if (binding.swipeRefreshLayout != null) {
            binding.swipeRefreshLayout.setRefreshing(false);
        }
        if (bean != null) {
            binding.setItem(bean);
        }
    }

    /**
     * 刷新数据
     */
    public void refreshData() {
        viewModel.getStoreStatisticInfo(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID());
    }

    /**
     * 绑定店铺信息显示
     */
    private void bindValue() {
        StoryUserBean bean = AppContext.getInstanceBase().getStoryUserBean();
        if (bean != null) {
            GlideLoad.load(binding.imgHead, bean.getIMG1());
            VerificationUtil.setViewValue(binding.tvName, bean.getNAME());
            VerificationUtil.setViewValue(binding.tvAddr, bean.getADDRESS());
        }
    }

    /**
     * 注册广播通知
     */
    private void registerBroadcast() {
        IntentFilter filter = new IntentFilter(BroadCastUtil.ACTION_REFRESH_SHOW_STOREINFO);
        LocalBroadcastManager.getInstance(AppContext.getInstanceBase()).registerReceiver(broadcastReceiver, filter);
    }

    /**
     * 注销广播通知
     */
    private void unregisterBroadcast() {
        LocalBroadcastManager.getInstance(AppContext.getInstanceBase()).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (BroadCastUtil.ACTION_REFRESH_SHOW_STOREINFO.equals(intent.getAction())) {   //刷新店铺信息显示
                bindValue();
            }
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RequestAndResultCode.REQUEST_NEED_REFRESH_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            refreshData();
        }
    }

    @Override
    public void onRefresh() {
        refreshData();
    }

    /**
     * 订单区域点击事件
     *
     * @param view 点击控件
     */
    private void orderAreaClick(View view) {
        int index = 0;
        switch (view.getId()) {
            case R.id.v_order_evaluation:
                index = 0;
                break;
            case R.id.v_order_group:
                index = 1;
                break;
            case R.id.v_order_protection:
                index = 2;
                break;
            case R.id.v_order_complete:
                index = 3;
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, index);
        jump(AcOrderManager.class, bundle, false);
    }

    /**
     * 交易区域点击事件
     *
     * @param view
     */
    private void tranAreaClick(View view) {
        int index = 0;
        switch (view.getId()) {
            case R.id.v_tran_today:
                index = 0;
                break;
            case R.id.v_tran_visitor_today:
                index = 1;
                break;
            case R.id.v_tran_coll_num:
                index = 2;
                break;
            case R.id.v_tran_tran_num:
                index = 3;
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, index);
        jump(AcTransactionData.class, bundle, false);
    }

    /**
     * 商品区域点击事件
     *
     * @param view
     */
    private void commodityAreaClick(View view) {
        int index = 0;
        switch (view.getId()) {
            case R.id.v_commodity_published:
                index = 0;
                break;
            case R.id.v_commodity_removed:
                index = 1;
                break;
            case R.id.v_commodity_consumed:
                index = 2;
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, index);
        jump(AcCommodityManager.class, bundle, false);
    }

    /**
     * 权益包申请区域点击事件
     *
     * @param view
     */
    private void applyAreaClick(View view) {
        int index = 0;
        switch (view.getId()) {
            case R.id.v_apply_ing:
                index = 0;
                break;
            case R.id.v_apply_reject:
                index = 1;
                break;
            case R.id.v_apply_passed:
                index = 2;
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, index);
        jump(AcApplyManager.class, bundle, false);
    }

    @Override
    public void onDestroy() {
        unregisterBroadcast();
        super.onDestroy();
    }
}
