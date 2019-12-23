package com.fx.feemore.business.ui.order.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:预约驳回ViewModel
 * author: frj
 * modify date: 2018/12/27
 */
public class VMReservationReject extends ViewModel
{
    private DataSource dataSource;

    @Inject
    public VMReservationReject(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
