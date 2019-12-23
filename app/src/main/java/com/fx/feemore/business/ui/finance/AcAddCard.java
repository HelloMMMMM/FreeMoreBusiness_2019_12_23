package com.fx.feemore.business.ui.finance;

import android.app.ProgressDialog;
import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcAddCardBinding;
import com.fx.feemore.business.ui.finance.vm.VMAddCard;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.TextViewUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.ToastUtil;

/**
 * function:添加银行卡页面
 * author: frj
 * modify date: 2018/12/31
 */
public class AcAddCard extends BaseBindActivity<AcAddCardBinding, VMAddCard>
{

    private ProgressDialog dialog;


    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_add_card;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.add_card_title);

        initLiveData();


        binding.btnBind.setOnClickListener(v -> addBankCard());
        binding.tvGetCode.setOnClickListener(v -> {
            if (VerificationUtil.validator(binding.edPhone, "请输入银行预留手机号"))
            {
                if (dialog == null)
                {
                    dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
                }
                if (!dialog.isShowing())
                {
                    dialog.show();
                }
                viewModel.getCode(TextViewUtil.getText(binding.edPhone));
            }
        });
        viewModel.initCountDown(binding.tvGetCode);
    }

    private void initLiveData()
    {
        viewModel.resFail.observe(this, httpFailBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });

        viewModel.resSucc.observe(this, responseBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            ToastUtil.show("添加成功");
            setResult(RESULT_OK);
            finish();
        });
        viewModel.resSendSucc.observe(this, responseBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
        });
    }

    /**
     * 添加银行卡
     */
    private void addBankCard()
    {
        if (VerificationUtil.requiredFieldValidator(new View[]{binding.edName, binding.edCard, binding.edPhone, binding.edCode}, new String[]{"请输入真实姓名，并和银行卡保持一致", "请输入银行卡号", "请输入银行预留手机号", "请输入验证码"}))
        {
            if (dialog == null)
            {
                dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
            }
            dialog.show();
            viewModel.addBankCard(TextViewUtil.getText(binding.edCard), TextViewUtil.getText(binding.edName), TextViewUtil.getText(binding.edPhone), TextViewUtil.getText(binding.edCode));
        }
    }

    @Override
    protected void onDestroy()
    {
        viewModel.destroy();
        super.onDestroy();
    }
}
