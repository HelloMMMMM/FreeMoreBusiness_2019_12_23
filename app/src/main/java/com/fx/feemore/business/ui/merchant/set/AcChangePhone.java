package com.fx.feemore.business.ui.merchant.set;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.databinding.AcChangePhoneBinding;
import com.fx.feemore.business.ui.merchant.set.vm.VMChangePhone;
import com.fx.feemore.business.util.BroadCastUtil;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.TextViewUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.ToastUtil;

/**
 * function:更改手机号
 * author: frj
 * modify date: 2019/1/22
 */
public class AcChangePhone extends BaseBindActivity<AcChangePhoneBinding, VMChangePhone>
{

    private ProgressDialog dialog;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_change_phone;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("更改手机号");

        initLiveData();

        viewModel.initOldCountDown(binding.tvFirstGetCode);
        viewModel.initNewCountDown(binding.tvSecondGetCode);

        binding.btnNext.setOnClickListener(v -> {
            if (VerificationUtil.requiredFieldValidator(new View[]{binding.edOldPhone, binding.edVerify}, new String[]{"请输入原手机号", "请输入验证码"}))
            {
                viewModel.checkVerificationCode(TextViewUtil.getText(binding.edOldPhone), TextViewUtil.getText(binding.edVerify));
            }
        });
        binding.tvFirstGetCode.setOnClickListener(v -> {
            if (VerificationUtil.validator(binding.edOldPhone, "请输入原手机号"))
            {
                if (TextViewUtil.getText(binding.edOldPhone).equals(AppContext.getInstanceBase().getStoryUserBean().getACCOUNT()))
                {
                    showDialog();
                    viewModel.sendVerifyCode(TextViewUtil.getText(binding.edOldPhone));
                } else
                {
                    ToastUtil.show("请与当前登录手机号一致");
                }
            }
        });
        binding.tvSecondGetCode.setOnClickListener(v -> {
            if (VerificationUtil.validator(binding.edNewPhone, "请输入新手机号"))
            {
                showDialog();
                viewModel.sendVerifyCode(TextViewUtil.getText(binding.edNewPhone));
            }
        });

        binding.btnConfirm.setOnClickListener(v -> {
            if (VerificationUtil.requiredFieldValidator(new View[]{binding.edNewPhone, binding.edNewVerify}, new String[]{"请输入新手机号", "请输入验证码"}))
            {
                showDialog();
                viewModel.changePhone(TextViewUtil.getText(binding.edNewPhone), TextViewUtil.getText(binding.edNewVerify));
            }
        });
    }


    private void initLiveData()
    {
        viewModel.resHttpFail.observe(this, httpFailBean -> {
            dismissDialog();
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });

        viewModel.resCheckCodeSucc.observe(this, responseBean -> {
            dismissDialog();
            viewModel.firstStep.set(false);
        });
        viewModel.resSendCodeSucc.observe(this, resposne -> dismissDialog());
        viewModel.resChangePhone.observe(this, responseBean -> {
            dismissDialog();
            BroadCastUtil.sendRefreshStoreInfo();
            Intent intent = new Intent();
            intent.putExtra(KEY, TextViewUtil.getText(binding.edNewPhone));
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    /**
     * 显示对话框
     */
    private void showDialog()
    {
        if (dialog == null)
        {
            dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
        }
        if (!dialog.isShowing())
        {
            dialog.show();
        }
    }

    /**
     * 关闭对话框显示
     */
    private void dismissDialog()
    {
        if (dialog != null)
        {
            dialog.dismiss();
        }
    }

    @Override
    protected void onDestroy()
    {
        viewModel.destroy();
        super.onDestroy();
    }
}
