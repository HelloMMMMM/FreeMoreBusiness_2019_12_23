package com.fx.feemore.business.ui.merchant.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:活动详情ViewModel
 * author: frj
 * modify date: 2019/1/1
 */
public class VMActiveDetail extends ViewModel
{
    private DataSource dataSource;

    @Inject
    public VMActiveDetail(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
