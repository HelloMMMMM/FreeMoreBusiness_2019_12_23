package com.fx.feemore.business.ui.login.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.FPhoneVerifyBinding;
import com.fx.feemore.business.ui.apply.AcApplyRecord;
import com.fx.feemore.business.ui.home.MainActivity;
import com.fx.feemore.business.ui.login.vm.VMPhoneVerify;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.TextViewUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:手机验证码登录
 * author: frj
 * modify date: 2018/12/23
 */
public class FPhoneVerify extends FragmentViewPagerBase<FPhoneVerifyBinding, VMPhoneVerify>
{

    private ProgressDialog dialog;

    @Override
    protected int getLayoutId()
    {
        return R.layout.f_phone_verify;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);

        dialog = ProgressDialogUtil.getProgressDialog(getActivity(), getString(R.string.progress_tips), true);

        viewModel.initCountDownHelper(binding.tvGetCode);

        initLiveData();

        binding.btnLogin.setOnClickListener(v -> {
            if (VerificationUtil.requiredFieldValidator(new View[]{binding.edPhone, binding.edCode}, new String[]{"请输入手机号", "请输入验证码"}))
            {
                if (dialog != null)
                {
                    dialog.show();
                }
                viewModel.login(getTextView(binding.edPhone), getTextView(binding.edCode));
            }
        });
        binding.tvGetCode.setOnClickListener(v -> {
            if (VerificationUtil.validator(binding.edPhone, "请输入手机号"))
            {
                viewModel.sendVerificationCode(TextViewUtil.getText(binding.edPhone));
            }
        });
    }


    private void initLiveData()
    {
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

        viewModel.storyUserSucc.observe(this, storyUserBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            if (storyUserBean != null && !TextUtils.isEmpty(storyUserBean.getSTORE_ID()))
            {

                AppContext.getInstanceBase().setStoryUserBean(storyUserBean);
                //状态，0申请中，1通过，-1不通过
                if (1 == storyUserBean.getSTATUS())
                {
                    jump(MainActivity.class, true);
                } else
                {
                    jump(AcApplyRecord.class, true);
                }
            }
        });
        viewModel.getCodeSuccRes.observe(this, responseBean ->
                ToastUtil.show("已将验证码发送至您的手机")
        );
    }

    @Override
    public void onStarShow()
    {

    }


    @Override
    public void onDestroy()
    {
        viewModel.destroy();
        super.onDestroy();
    }
}
