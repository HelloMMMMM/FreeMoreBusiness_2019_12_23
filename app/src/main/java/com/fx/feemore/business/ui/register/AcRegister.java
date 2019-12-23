package com.fx.feemore.business.ui.register;

import android.Manifest;
import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcRegisterBinding;
import com.fx.feemore.business.util.TextViewUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.ToastUtil;

/**
 * function:申请开店界面
 * author: frj
 * modify date: 2018/12/23
 */
public class AcRegister extends BaseBindActivity<AcRegisterBinding, VMRegister>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_register;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);

        setToolbarTitle(R.string.register_title);
        initLiveData();

        showStepFirst();
        viewModel.initCountDownHelper(binding.tvGetCode);

        binding.tvGotoLogin.setOnClickListener(v -> finish());
    }

    private void initLiveData()
    {
        viewModel.httpFailRes.observe(this, httpFailBean -> {
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.getCodeSuccRes.observe(this, responseBean -> {
            showStepConfirm();
            ToastUtil.show("已将验证码发送至您的手机");
        });
        viewModel.checkCodeSuccRes.observe(this, responseBean -> jump(AcInputData.class, TextViewUtil.getText(binding.edPhone)));
    }

    /**
     * 显示第一步
     */
    private void showStepFirst()
    {
        binding.tvStepTips.setText(R.string.register_step_first);
        binding.edPhone.setVisibility(View.VISIBLE);
        binding.vLine.setVisibility(View.VISIBLE);
        binding.btnNext.setVisibility(View.VISIBLE);
        binding.tvGotoLogin.setVisibility(View.VISIBLE);
        binding.tvSendPhoneTips.setVisibility(View.GONE);
        binding.edCode.setVisibility(View.GONE);
        binding.tvGetCode.setVisibility(View.GONE);
        binding.vCodeLine.setVisibility(View.GONE);
        binding.btnConfirm.setVisibility(View.GONE);


        binding.btnNext.setOnClickListener(v -> {

//            jump(AcInputData.class, "15926212638");
            if (VerificationUtil.validator(binding.edPhone, "请输入手机号"))
            {
                binding.tvSendPhoneTips.setText(String.format(getString(R.string.register_send_phone_tips), binding.edPhone.getText().toString().trim()));
                viewModel.sendVerificationCode(TextViewUtil.getText(binding.edPhone));
                binding.tvGetCode.setEnabled(false);

            }
        });
    }

    /**
     * 显示第二步
     */
    private void showStepConfirm()
    {
        binding.tvStepTips.setText(R.string.register_step_second);
        binding.edPhone.setVisibility(View.GONE);
        binding.vLine.setVisibility(View.GONE);
        binding.btnNext.setVisibility(View.GONE);
        binding.tvGotoLogin.setVisibility(View.GONE);
        binding.tvSendPhoneTips.setVisibility(View.VISIBLE);
        binding.edCode.setVisibility(View.VISIBLE);
        binding.tvGetCode.setVisibility(View.VISIBLE);
        binding.vCodeLine.setVisibility(View.VISIBLE);
        binding.btnConfirm.setVisibility(View.VISIBLE);

        binding.btnConfirm.setOnClickListener(v -> {
            if (VerificationUtil.validator(binding.edCode, "请输入验证码"))
            {
                viewModel.checkVerificationCode(TextViewUtil.getText(binding.edPhone), TextViewUtil.getText(binding.edCode));
            }
        });
    }

    /**
     * 发送验证码
     *
     * @param view 点击的控件
     */
    public void sendverifyCode(View view)
    {
        if (VerificationUtil.validator(binding.edPhone, "请输入手机号"))
        {
            viewModel.sendVerificationCode(TextViewUtil.getText(binding.edPhone));
            view.setEnabled(false);
        }
    }

    @Override
    protected void onDestroy()
    {
        viewModel.destroy();
        super.onDestroy();
    }

    @Override
    protected String[] getPermissionArrays()
    {
        return new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA};
    }

    @Override
    protected int[] getPermissionInfoTips()
    {
        return new int[]{R.string.permission_fine_location_tips, R.string.permission_camera_tips};
    }
}
