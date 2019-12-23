package com.fx.feemore.business.ui.order.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:待成团订单ViewModel
 * author: frj
 * modify date: 2019/1/18
 */
public class VMWaitGroupDetail extends ViewModel
{
    private DataSource dataSource;

    @Inject
    public VMWaitGroupDetail(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
