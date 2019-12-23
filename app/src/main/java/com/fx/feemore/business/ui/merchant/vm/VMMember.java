package com.fx.feemore.business.ui.merchant.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:会员活跃度记录
 * author: frj
 * modify date: 2018/12/31
 */
public class VMMember extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMMember(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
