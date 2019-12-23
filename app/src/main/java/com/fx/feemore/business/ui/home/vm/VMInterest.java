package com.fx.feemore.business.ui.home.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:权益包ViewModel
 * author: frj
 * modify date: 2018/12/30
 */
public class VMInterest extends ViewModel
{
    private DataSource dataSource;

    @Inject
    public VMInterest(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
