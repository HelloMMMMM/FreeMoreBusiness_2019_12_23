package com.fx.feemore.business.ui.merchant.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:权益包申请详情ViweModel
 * author: frj
 * modify date: 2019/1/2
 */
public class VMApplyInterestDetails extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMApplyInterestDetails(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
