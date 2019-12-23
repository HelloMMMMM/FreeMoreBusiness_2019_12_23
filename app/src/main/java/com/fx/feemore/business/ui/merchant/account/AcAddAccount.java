package com.fx.feemore.business.ui.merchant.account;

import android.app.ProgressDialog;
import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.databinding.AcAddAccountBinding;
import com.fx.feemore.business.ui.merchant.account.vm.VMAddAccount;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.TextViewUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.ToastUtil;

/**
 * function:新增子账号页面
 * author: frj
 * modify date: 2019/5/14
 */
public class AcAddAccount extends BaseBindActivity<AcAddAccountBinding, VMAddAccount>
{

    private ProgressDialog dialog;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_add_account;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("新增子账号");


        binding.tvManipulator.setOnClickListener(v -> {
            binding.tvManipulator.setSelected(true);
            binding.tvOperator.setSelected(false);
        });
        binding.tvOperator.setOnClickListener(v -> {
            binding.tvManipulator.setSelected(false);
            binding.tvOperator.setSelected(true);
        });
        binding.btnConfirm.setOnClickListener(v -> addConfim());

        initLiveData();
    }

    private void initLiveData()
    {
        viewModel.addRes.observe(this, responseBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            ToastUtil.show("添加成功");
            setResult(RESULT_OK);
            finish();
        });
        viewModel.failRes.observe(this, httpFailBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });
    }

    /**
     * 添加子账号确认
     */
    private void addConfim()
    {
        if (!binding.tvOperator.isSelected() && !binding.tvManipulator.isSelected())
        {
            ToastUtil.show("请选择新增的子帐号类型");
            return;
        }
        if (VerificationUtil.requiredFieldValidator(new View[]{binding.edPhone, binding.edName}, new String[]{"请输入手机号", "请输入帐号使用人名称"}))
        {
            if (dialog == null)
            {
                dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
            }
            dialog.show();
            viewModel.addAccount(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), TextViewUtil.getText(binding.edPhone), binding.tvOperator.isSelected() ? 1 : 2, TextViewUtil.getText(binding.edName));
        }
    }
}
