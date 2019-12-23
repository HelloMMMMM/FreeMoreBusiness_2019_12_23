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
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.HomeV3MyDataBean;
import com.fx.feemore.business.bean.StoryUserBean;
import com.fx.feemore.business.databinding.FHomeV3Binding;
import com.fx.feemore.business.ui.apply.AcPayDeposit;
import com.fx.feemore.business.ui.home.adapter.AdHomeV3Case;
import com.fx.feemore.business.ui.home.vm.VMHome;
import com.fx.feemore.business.ui.interest.AcPublishInterestV2;
import com.fx.feemore.business.ui.notify.AcNotifyManager;
import com.fx.feemore.business.ui.order.AcOrderManager;
import com.fx.feemore.business.ui.order.AcOrderWriteOff;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.BroadCastUtil;
import com.fx.feemore.business.util.GlideLoad;
import com.fx.feemore.business.util.RequestAndResultCode;
import com.hengxian.baselib.base.BaseBindFragment;
import com.hengxian.common.DisplayUtil;
import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;

import java.util.ArrayList;
import java.util.List;

public class FragmentHomeV3 extends BaseBindFragment<FHomeV3Binding, VMHome> implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    // 学习案例-当前轮播案例索引
    private int currentCaseIndex;

    public static FragmentHomeV3 newInstance() {
        return new FragmentHomeV3();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.f_home_v3;
    }

    @Override
    public void onRefresh() {
        binding.refresh.setRefreshing(true);
        viewModel.getMyData(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), this);
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.header.getLayoutParams();
            if (params != null) {
                params.topMargin += DisplayUtil.getStatusBarHeight(getActivity());
                binding.header.setLayoutParams(params);
            }
        }

        binding.refresh.setOnRefreshListener(this);
        binding.message.setOnClickListener(this);
        binding.tab1.intro.ivScan.setOnClickListener(this);

        onRefresh();

        refreshBaseInfoModule(null);
        initCaseModule();

        registerBroadcast();
    }

    public void refreshFinish() {
        binding.refresh.setRefreshing(false);
    }

    public void refreshBaseInfoModule(HomeV3MyDataBean dataBean) {
        refreshStoreData();
        if (AppContext.getInstanceBase().getStoryUserBean().getMARGIN() == 0) {
            binding.tab1.noPublish.root.setVisibility(View.VISIBLE);
            binding.tab1.noPublish.tvPublish.setOnClickListener(v -> {
                if (AppContext.getInstanceBase().getStoryUserBean().getMARGIN() == 0) {
                    AlertDialogUtil.create(getActivity(), getString(R.string.service_money), (dialog, which) -> jump(AcPayDeposit.class), null).show();
                    return;
                }
                jump(AcPublishInterestV2.class, RequestAndResultCode.REQUEST_NEED_REFRESH_CODE);
            });
        } else {
            binding.tab1.hasPublish.root.setVisibility(View.VISIBLE);
            binding.tab1.hasPublish.tvData4.setOnClickListener(this);
            binding.tab1.hasPublish.tvData5.setOnClickListener(this);
            binding.tab1.hasPublish.tvData6.setOnClickListener(this);
            if (dataBean != null) {
                refreshData(dataBean);
            }
        }
    }

    public void refreshStoreData() {
        StoryUserBean bean = AppContext.getInstanceBase().getStoryUserBean();
        if (bean != null) {
            binding.tab1.intro.tvStoreName.setText(bean.getNAME());
            binding.tab1.intro.tvStoreAddress.setText(bean.getADDRESS());
            GlideLoad.loadAvartar(binding.tab1.intro.ivStoreAvatar, bean.getIMG1());
        }
    }

    public void refreshData(HomeV3MyDataBean dataBean) {
        refreshMyData(dataBean);
        refreshPlatformData(dataBean);
    }

    private void refreshMyData(HomeV3MyDataBean dataBean) {
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.65f);
        SpannableString ss1 = new SpannableString("建设中...");
        SpannableString ss2 = new SpannableString(getString(R.string.v3_data_format_second, String.valueOf(dataBean.getLUCKTOTAL())));
        SpannableString ss3 = new SpannableString(getString(R.string.v3_data_format_second, String.valueOf(dataBean.getRECORDTOTAL())));
        SpannableString ss4 = new SpannableString(getString(R.string.v3_data_format_rmb, String.valueOf(dataBean.getCUSTOMSELLTOTEL())));
        SpannableString ss5 = new SpannableString(getString(R.string.v3_data_format_rmb, String.valueOf(dataBean.getCARDSELLTOTEL())));
        SpannableString ss6 = new SpannableString(getString(R.string.v3_data_format_rmb, String.valueOf(dataBean.getTICKETSELLTOTEL())));

        //ss1.setSpan(relativeSizeSpan, ss1.length() - 1, ss1.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss2.setSpan(relativeSizeSpan, ss2.length() - 1, ss2.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss3.setSpan(relativeSizeSpan, ss3.length() - 1, ss3.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss4.setSpan(relativeSizeSpan, ss4.length() - 1, ss4.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss5.setSpan(relativeSizeSpan, ss5.length() - 1, ss5.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss6.setSpan(relativeSizeSpan, ss6.length() - 1, ss6.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        binding.tab1.hasPublish.tvData1.setText(ss1);
        binding.tab1.hasPublish.tvData2.setText(ss2);
        binding.tab1.hasPublish.tvData3.setText(ss3);
        binding.tab1.hasPublish.tvData4.setText(ss4);
        binding.tab1.hasPublish.tvData5.setText(ss5);
        binding.tab1.hasPublish.tvData6.setText(ss6);

        refreshFinish();
    }

    private void refreshPlatformData(HomeV3MyDataBean dataBean) {
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.65f);

        SpannableString ss1 = new SpannableString(getString(R.string.v3_data_format_people, String.valueOf(dataBean.getMEMBERTOTAL_SYS())));
        SpannableString ss2 = new SpannableString(getString(R.string.v3_data_format_second, String.valueOf(dataBean.getLUCKTOTAL_SYS())));
        SpannableString ss3 = new SpannableString(getString(R.string.v3_data_format_second, String.valueOf(dataBean.getRECORDTOTAL_SYS())));
        SpannableString ss4 = new SpannableString(getString(R.string.v3_data_format_rmb, String.valueOf(dataBean.getCUSTOMSELLTOTEL_SYS())));
        SpannableString ss5 = new SpannableString(getString(R.string.v3_data_format_rmb, String.valueOf(dataBean.getCARDSELLTOTEL_SYS())));
        SpannableString ss6 = new SpannableString(getString(R.string.v3_data_format_rmb, String.valueOf(dataBean.getTICKETSELLTOTEL_SYS())));

        ss1.setSpan(relativeSizeSpan, ss1.length() - 1, ss1.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss2.setSpan(relativeSizeSpan, ss2.length() - 1, ss2.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss3.setSpan(relativeSizeSpan, ss3.length() - 1, ss3.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss4.setSpan(relativeSizeSpan, ss4.length() - 1, ss4.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss5.setSpan(relativeSizeSpan, ss5.length() - 1, ss5.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        ss6.setSpan(relativeSizeSpan, ss6.length() - 1, ss6.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        binding.tab2.tvData1.setText(ss1);
        binding.tab2.tvData2.setText(ss2);
        binding.tab2.tvData3.setText(ss3);
        binding.tab2.tvData4.setText(ss4);
        binding.tab2.tvData5.setText(ss5);
        binding.tab2.tvData6.setText(ss6);
    }

    private void initCaseModule() {
        List<Object> images = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            images.add(R.mipmap.test_3);
        }
        AdHomeV3Case adHomeV3Case = new AdHomeV3Case(getActivity(), images);
        ViewGroup.LayoutParams layoutParams = binding.tab4.banner.getLayoutParams();
        layoutParams.width = ScreenUtils.getScreenWidth() - SizeUtils.dp2px(24);
        binding.tab4.banner.setLayoutParams(layoutParams);
        binding.tab4.banner.setPageMargin(SizeUtils.dp2px(16));
        binding.tab4.banner.setOffscreenPageLimit(2);
        binding.tab4.banner.setAdapter(adHomeV3Case);
        binding.tab4.banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                binding.tab4.indicator.getChildAt(currentCaseIndex).setSelected(false);
                currentCaseIndex = i;
                binding.tab4.indicator.getChildAt(currentCaseIndex).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        for (int i = 0; i < images.size(); i++) {
            int size = SizeUtils.dp2px(6);
            int margin = SizeUtils.dp2px(6);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(size, size);
            llp.setMargins(margin, 0, margin, 0);
            View indicator = new View(getActivity());
            indicator.setBackgroundResource(R.drawable.bg_home_v3_indicator);
            binding.tab4.indicator.addView(indicator, llp);
        }
        binding.tab4.indicator.getChildAt(currentCaseIndex).setSelected(true);
    }

    private void initActivityModule() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && Activity.RESULT_OK == resultCode) {
            if (RequestAndResultCode.REQUEST_SCAN_CODE == requestCode) {
                String result = data.getStringExtra(Intents.Scan.RESULT);
                jump(AcOrderWriteOff.class, result);
            } else if (RequestAndResultCode.REQUEST_NEED_REFRESH_CODE == requestCode) {
                onRefresh();
            }
        }
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
            case R.id.tv_data_4:
                jump(AcOrderManager.class);
                break;
            case R.id.tv_data_5:
                jump(AcOrderManager.class);
                break;
            case R.id.tv_data_6:
                jump(AcOrderManager.class);
                break;
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
                refreshStoreData();
            }
        }
    };

    @Override
    public void onDestroy() {
        unregisterBroadcast();
        super.onDestroy();
    }
}
