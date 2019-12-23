package com.fx.feemore.business.ui.merchant.team.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:我的团队ViewModel
 * author: frj
 * modify date: 2019/1/22
 */
public class VMMyTeam extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMMyTeam(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
