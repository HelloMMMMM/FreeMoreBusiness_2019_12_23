package com.fx.feemore.business.ui.commodity.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:消费详情ViewModel
 * author: frj
 * modify date: 2019/3/2
 */
public class VMConsumeDetail extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMConsumeDetail(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

}
