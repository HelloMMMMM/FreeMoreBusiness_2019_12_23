package com.fx.feemore.business.ui.interest.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:权益包详情ViewModel
 * author: frj
 * modify date: 2018/12/30
 */
public class VMInterestDetail extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMInterestDetail(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
