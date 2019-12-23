package com.fx.feemore.business.ui.home.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:商家中心ViewModel
 * author: frj
 * modify date: 2018/12/31
 */
public class VMPerson extends ViewModel
{
    private DataSource dataSource;

    @Inject
    public VMPerson(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
