package com.fx.feemore.business.ui.transaction.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:近日交易数据
 * author: frj
 * modify date: 2019/1/17
 */
public class VMTransactionData extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMTransactionData(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
