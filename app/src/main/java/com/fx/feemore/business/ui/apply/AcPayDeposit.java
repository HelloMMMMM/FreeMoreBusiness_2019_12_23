package com.fx.feemore.business.ui.apply;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.databinding.AcPayDepositBinding;
import com.fx.feemore.business.util.BroadCastUtil;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.ToastUtil;

/**
 * function:缴纳保证金界面
 * author: frj
 * modify date: 2018/12/27
 */
public class AcPayDeposit extends BaseBindActivity<AcPayDepositBinding, VMPayDeposit> {

    private ProgressDialog dialog;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_pay_deposit;
    }

    @Override
    protected void init() {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.pay_deposit_title);

        initLiveData();

        registerBroadCastReceiver();

        binding.tvAlipay.setOnClickListener(this::setSelected);
        binding.tvWechat.setOnClickListener(this::setSelected);
        binding.btnConfirm.setOnClickListener(v -> {
            if (binding.tvAlipay.isSelected() || binding.tvWechat.isSelected()) {
                if (dialog == null) {
                    dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
                }
                dialog.show();
                if (binding.tvWechat.isSelected()) {
                    viewModel.payBond(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID());
                } else {
                    viewModel.payBondAlipay(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID());
                }
            } else {
                ToastUtil.show("请先选择支付方式");
            }
        });
    }

    private void initLiveData() {
        viewModel.resCheckResult.observe(this, responseBean -> {
            ToastUtil.show("您已缴纳保证金");
            finish();
        });
        viewModel.resHttpFail.observe(this, httpFailBean -> {
            if (dialog != null) {
                dialog.dismiss();
            }
            if (httpFailBean != null) {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.resPayBean.observe(this, wChatPayBean -> {
            if (dialog != null) {
                dialog.dismiss();
            }
            if (wChatPayBean != null) {
                viewModel.wechatPay(wChatPayBean);
            }
        });
        viewModel.resAlipay.observe(this, orderStr -> {
            if (dialog != null) {
                dialog.dismiss();
            }
            viewModel.alipay(this, orderStr);
        });
        viewModel.resAlipayResult.observe(this, payResult -> {
            /**
             对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                ToastUtil.show("支付成功");
                BroadCastUtil.sendRefreshStoreInfo();
                setResult(RESULT_OK);
                finish();
//                jump(AcAssetDetail.class, true);
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                ToastUtil.show("支付失败");
            }
        });
    }

    /**
     * 注册广播接收者
     */
    private void registerBroadCastReceiver() {
        IntentFilter filter = new IntentFilter(BroadCastUtil.ACTION_REFRESH_STOREINFO);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, filter);
    }

    /**
     * 注销广播接收者
     */
    private void unRegisterBroadCastReceiver() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (BroadCastUtil.ACTION_WCHAT_PAY_SUCC.equals(intent.getAction())) {
                AcPayDeposit.this.setResult(RESULT_OK);
                finish();
            }
        }
    };

    /**
     * 设置选中
     *
     * @param textView 要选中的项
     */
    private void setSelected(View textView) {
        binding.tvWechat.setSelected(binding.tvWechat == textView);
        binding.tvAlipay.setSelected(binding.tvAlipay == textView);
    }

    @Override
    protected void onDestroy() {
        unRegisterBroadCastReceiver();
        super.onDestroy();
    }
}
