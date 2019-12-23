package com.fx.feemore.business.ui.login.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:帐号密码登录
 * author: frj
 * modify date: 2018/12/23
 */
public class VMAccountPassword extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMAccountPassword(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
