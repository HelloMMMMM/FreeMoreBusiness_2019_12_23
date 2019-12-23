package com.fx.feemore.business.ui.merchant.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:权益包申请进度项ViewModel
 * author: frj
 * modify date: 2019/1/2
 */
public class VMApplyInterestItem extends ViewModel
{
    /**
     * 审核通过
     */
    public static final int TYPE_PASS = 1;
    /**
     * 审核中
     */
    public static final int TYPE_APPLING = 2;
    /**
     * 审核未通过
     */
    public static final int TYPE_REJECT = 3;

    private DataSource dataSource;

    @Inject
    public VMApplyInterestItem(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
}
