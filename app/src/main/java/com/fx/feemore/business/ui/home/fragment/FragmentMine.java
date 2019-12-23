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
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.StoryUserBean;
import com.fx.feemore.business.databinding.FMineBinding;
import com.fx.feemore.business.ui.finance.AcFinance;
import com.fx.feemore.business.ui.home.vm.VMHome;
import com.fx.feemore.business.ui.home.vm.VMMain;
import com.fx.feemore.business.ui.merchant.account.AcAccountList;
import com.fx.feemore.business.ui.merchant.review.AcReview;
import com.fx.feemore.business.ui.merchant.set.AcMerchantSet;
import com.fx.feemore.business.ui.notify.AcNotifyManager;
import com.fx.feemore.business.ui.order.AcOrderWriteOff;
import com.fx.feemore.business.util.BroadCastUtil;
import com.fx.feemore.business.util.GlideLoad;
import com.fx.feemore.business.util.RequestAndResultCode;
import com.hengxian.baselib.base.BaseBindFragment;
import com.hengxian.common.DisplayUtil;
import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;

public class FragmentMine extends BaseBindFragment<FMineBinding, VMMain> implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @Override
    protected int getLayoutId() {
        return R.layout.f_mine;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            refreshStoreData();
        }
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.header.getLayoutParams();
            if (params != null) {
                params.topMargin += DisplayUtil.getStatusBarHeight(getActivity());
                binding.header.setLayoutParams(params);
            }
        }

        refreshStoreData();

        binding.refresh.setOnRefreshListener(this);
        binding.message.setOnClickListener(this);
        binding.intro.ivScan.setOnClickListener(this);

        binding.accountDetail.setOnClickListener(this);
        binding.childAccountManager.setOnClickListener(this);
        binding.operateCheck.setOnClickListener(this);
        binding.mineSetting.setOnClickListener(this);

        registerBroadcast();
    }

    private void refreshStoreData() {
        StoryUserBean bean = AppContext.getInstanceBase().getStoryUserBean();
        if (bean != null) {
            binding.intro.tvStoreName.setText(bean.getNAME());
            binding.intro.tvStoreAddress.setText(bean.getADDRESS());
            GlideLoad.loadAvartar(binding.intro.ivStoreAvatar, bean.getIMG1());
            RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.65f);
            SpannableString ss1 = new SpannableString(getString(R.string.v3_data_format_rmb, bean.getBALANCE() == null ? "0" : bean.getBALANCE()));
            SpannableString ss2 = new SpannableString(getString(R.string.v3_data_format_rmb, bean.getAMOUNT() == null ? "0" : bean.getAMOUNT()));
            ss1.setSpan(relativeSizeSpan, ss1.length() - 1, ss1.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            ss2.setSpan(relativeSizeSpan, ss2.length() - 1, ss2.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
            binding.tvBalance.setText(ss1);
            binding.tvRealAmount.setText(ss2);
        }
        binding.refresh.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message:
                jump(AcNotifyManager.class);
                break;
            case R.id.iv_scan:
                jump(CaptureActivity.class, RequestAndResultCode.REQUEST_SCAN_CODE);
                break;
            case R.id.account_detail:
                jump(AcFinance.class);
                break;
            case R.id.mine_setting:
                jump(AcMerchantSet.class);
                break;
            case R.id.child_account_manager:
                jump(AcAccountList.class);
                break;
            case R.id.operate_check:
                jump(AcReview.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && Activity.RESULT_OK == resultCode) {
            if (RequestAndResultCode.REQUEST_SCAN_CODE == requestCode) {
                String result = data.getStringExtra(Intents.Scan.RESULT);
                jump(AcOrderWriteOff.class, result);
            }
        }
    }

    @Override
    public void onRefresh() {
        viewModel.refreshStoryInfo(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), AppContext.getInstanceBase().getStoryUserBean().getZIACCOUNT());
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
                refreshStoreData();
            } else if (BroadCastUtil.ACTION_REFRESH_SHOW_STOREINFO_FAIL.equals(intent.getAction())) {
                binding.refresh.setRefreshing(false);
            }
        }
    };

    @Override
    public void onDestroy() {
        unregisterBroadcast();
        super.onDestroy();
    }
}
