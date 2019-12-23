package com.fx.feemore.business.ui.merchant.about.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:关于
 * author: frj
 * modify date: 2019/2/27
 */
public class VMAbout extends ViewModel
{
    private DataSource dataSource;

    @Inject
    public VMAbout(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
