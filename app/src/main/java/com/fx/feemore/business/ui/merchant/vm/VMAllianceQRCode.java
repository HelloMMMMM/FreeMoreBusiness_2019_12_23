package com.fx.feemore.business.ui.merchant.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:联盟二维码ViewModel
 * author: frj
 * modify date: 2019/1/2
 */
public class VMAllianceQRCode extends ViewModel
{

    private DataSource dataSource;

    @Inject
    public VMAllianceQRCode(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
