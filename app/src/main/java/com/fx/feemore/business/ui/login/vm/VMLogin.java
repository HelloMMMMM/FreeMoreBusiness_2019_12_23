package com.fx.feemore.business.ui.login.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:登录ViewModel
 * author: frj
 * modify date: 2018/12/23
 */
public class VMLogin extends ViewModel
{
    private DataSource dataSource;

    @Inject
    public VMLogin(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
