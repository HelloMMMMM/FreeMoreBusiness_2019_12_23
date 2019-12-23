package com.fx.feemore.business.ui.commodity.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:商品管理ViewModel
 * author: frj
 * modify date: 2019/1/18
 */
public class VMCommodityManager extends ViewModel
{

    private DataSource dataSource;


    @Inject
    public VMCommodityManager(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }


}
