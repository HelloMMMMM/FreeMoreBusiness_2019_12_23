package com.fx.feemore.business.ui.forgot;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:忘记密码
 * author: frj
 * modify date: 2018/12/23
 */
public class VMForgotPwd extends ViewModel
{
    private DataSource dataSource;

    @Inject
    public VMForgotPwd(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
