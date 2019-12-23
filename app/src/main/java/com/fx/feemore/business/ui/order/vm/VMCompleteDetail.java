package com.fx.feemore.business.ui.order.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:订单完成详情ViewModel
 * author: frj
 * modify date: 2019/1/18
 */
public class VMCompleteDetail extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMCompleteDetail(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
