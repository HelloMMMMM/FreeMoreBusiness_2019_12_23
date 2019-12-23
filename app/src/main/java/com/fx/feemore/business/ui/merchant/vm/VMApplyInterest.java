package com.fx.feemore.business.ui.merchant.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:权益包申请进度ViewModel
 * author: frj
 * modify date: 2019/1/2
 */
public class VMApplyInterest extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMApplyInterest(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
