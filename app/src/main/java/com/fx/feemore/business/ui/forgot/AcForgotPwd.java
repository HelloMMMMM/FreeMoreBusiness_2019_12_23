package com.fx.feemore.business.ui.forgot;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcForgotPwdBinding;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:忘记密码页
 * author: frj
 * modify date: 2018/12/23
 */
public class AcForgotPwd extends BaseBindActivity<AcForgotPwdBinding, VMForgotPwd>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_forgot_pwd;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        initData();
    }

    private void initData()
    {
        setToolbarTitle(R.string.forgot_pwd_title);
    }
}
