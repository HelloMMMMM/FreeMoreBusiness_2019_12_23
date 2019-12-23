package com.fx.feemore.business.ui.order.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:订单管理ViewModel
 * author: frj
 * modify date: 2019/1/17
 */
public class VMOrderManager extends ViewModel
{
    private DataSource dataSource;

    @Inject
    public VMOrderManager(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
