package com.fx.feemore.business.ui.merchant.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:活动权益包申请详情
 * author: frj
 * modify date: 2019/1/3
 */
public class VMApplyActiveDetail extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMApplyActiveDetail(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
