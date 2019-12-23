package com.fx.feemore.business.ui.home.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.StoryUserBean;
import com.fx.feemore.business.databinding.FPersonBinding;
import com.fx.feemore.business.ui.home.vm.VMPerson;
import com.fx.feemore.business.ui.merchant.AcAllianceList;
import com.fx.feemore.business.ui.merchant.DialogMerchantQRCode;
import com.fx.feemore.business.ui.merchant.account.AcAccountList;
import com.fx.feemore.business.ui.merchant.margin.AcMyMargin;
import com.fx.feemore.business.ui.merchant.review.AcReview;
import com.fx.feemore.business.ui.merchant.set.AcMerchantSet;
import com.fx.feemore.business.ui.merchant.team.AcMyTeam;
import com.fx.feemore.business.ui.order.AcOrderWriteOff;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.BroadCastUtil;
import com.fx.feemore.business.util.GlideLoad;
import com.fx.feemore.business.util.PermissionUtil;
import com.fx.feemore.business.util.RequestAndResultCode;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindFragment;
import com.king.zxing.CaptureActivity;
import com.king.zxing.Intents;

/**
 * function:商家
 * author: frj
 * modify date: 2018/12/24
 */
public class FragmentPerson extends BaseBindFragment<FPersonBinding, VMPerson>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.f_person;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);
        binding.toolbar.setNavigationIcon(null);
        setToolbarTitle(R.string.person_title);

        binding.vHeadContent.setOnClickListener(v -> {
            DialogMerchantQRCode dialog = new DialogMerchantQRCode();
            dialog.show(getChildFragmentManager(), "DialogMerchantQRCode");
        });
        binding.tvMember.setOnClickListener(v -> jump(AcMyTeam.class));
//        binding.tvActive.setOnClickListener(v -> jump(AcMyActive.class));
        binding.tvAlliance.setOnClickListener(v -> jump(AcAllianceList.class));
//        binding.tvActiveInterest.setOnClickListener(v -> jump(AcApplayActive.class));
//        binding.tvInterest.setOnClickListener(v -> jump(AcAppyInterest.class));

        binding.tvMyMargin.setOnClickListener(v -> {
            if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_OPERATOR))
            {
                AlertDialogUtil.createPermissionTips(getActivity()).show();
                return;
            }
            jump(AcMyMargin.class);
        });
        binding.tvMySet.setOnClickListener(v -> jump(AcMerchantSet.class));
        binding.ibScan.setOnClickListener(v -> jump(CaptureActivity.class, RequestAndResultCode.REQUEST_SCAN_CODE));

        binding.tvAddAccount.setOnClickListener(v -> {
            if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_OPERATOR))
            {
                AlertDialogUtil.createPermissionTips(getActivity()).show();
                return;
            }
            jump(AcAccountList.class);
        });
        binding.tvReview.setOnClickListener(v -> {
            if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_OPERATOR))
            {
                AlertDialogUtil.createPermissionTips(getActivity()).show();
                return;
            }
            jump(AcReview.class);
        });

        bindValue();
        registerBroadcast();
    }


    /**
     * 显示数据
     */
    private void bindValue()
    {
        StoryUserBean bean = AppContext.getInstanceBase().getStoryUserBean();
        if (bean != null)
        {
            GlideLoad.load(binding.img, bean.getIMG1());
            VerificationUtil.setViewValue(binding.tvName, bean.getNAME());
            VerificationUtil.setViewValue(binding.tvAddr, bean.getADDRESS());
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (isVisible())
        {
            bindValue();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);
        if (!isHidden())
        {
            bindValue();
        }
    }

    /**
     * 注册广播通知
     */
    private void registerBroadcast()
    {
        IntentFilter filter = new IntentFilter(BroadCastUtil.ACTION_REFRESH_SHOW_STOREINFO);
        LocalBroadcastManager.getInstance(AppContext.getInstanceBase()).registerReceiver(broadcastReceiver, filter);
    }

    /**
     * 注销广播通知
     */
    private void unregisterBroadcast()
    {
        LocalBroadcastManager.getInstance(AppContext.getInstanceBase()).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            if (BroadCastUtil.ACTION_REFRESH_SHOW_STOREINFO.equals(intent.getAction()))
            {   //刷新店铺信息显示
                bindValue();
            }
        }
    };

    @Override
    public void onDestroy()
    {
        unregisterBroadcast();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && Activity.RESULT_OK == resultCode)
        {
            if (RequestAndResultCode.REQUEST_SCAN_CODE == requestCode)
            {
                String result = data.getStringExtra(Intents.Scan.RESULT);
                jump(AcOrderWriteOff.class, result);
            }
        }
    }
}
