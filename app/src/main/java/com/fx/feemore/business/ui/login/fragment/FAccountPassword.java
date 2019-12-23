package com.fx.feemore.business.ui.login.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.FAccountPasswordBinding;
import com.fx.feemore.business.ui.forgot.AcForgotPwd;
import com.fx.feemore.business.ui.home.MainActivity;
import com.fx.feemore.business.ui.login.vm.VMAccountPassword;

/**
 * function:账号密码登录
 * author: frj
 * modify date: 2018/12/23
 */
public class FAccountPassword extends FragmentViewPagerBase<FAccountPasswordBinding, VMAccountPassword>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.f_account_password;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        binding.setVm(viewModel);

        binding.tvForgotPwd.setOnClickListener(v -> jump(AcForgotPwd.class));
        binding.btnLogin.setOnClickListener(v -> jump(MainActivity.class));
    }

    @Override
    public void onStarShow()
    {

    }
}
