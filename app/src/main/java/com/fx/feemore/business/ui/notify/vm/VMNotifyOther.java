package com.fx.feemore.business.ui.notify.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:其他通知
 * author: frj
 * modify date: 2019/1/17
 */
public class VMNotifyOther extends ViewModel
{
    private DataSource dataSource;

    @Inject
    public VMNotifyOther(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
