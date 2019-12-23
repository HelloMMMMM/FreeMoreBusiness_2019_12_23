package com.fx.feemore.business.ui.apply.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:申请管理ViewModel
 * author: frj
 * modify date: 2019/1/18
 */
public class VMApplyManager extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMApplyManager(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
