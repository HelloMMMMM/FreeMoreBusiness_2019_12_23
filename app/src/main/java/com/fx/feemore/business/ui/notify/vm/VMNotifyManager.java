package com.fx.feemore.business.ui.notify.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:系统通知管理ViewModel
 * author: frj
 * modify date: 2019/1/17
 */
public class VMNotifyManager extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMNotifyManager(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
