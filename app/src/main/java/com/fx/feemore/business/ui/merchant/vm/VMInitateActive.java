package com.fx.feemore.business.ui.merchant.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:发起活动ViewModel
 * author: frj
 * modify date: 2018/12/31
 */
public class VMInitateActive extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMInitateActive(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
