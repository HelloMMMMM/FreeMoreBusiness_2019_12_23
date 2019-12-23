package com.fx.feemore.business.ui.merchant.margin.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:我的保证金ViewModel
 * author: frj
 * modify date: 2019/1/22
 */
public class VMMyMargin extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMMyMargin(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
