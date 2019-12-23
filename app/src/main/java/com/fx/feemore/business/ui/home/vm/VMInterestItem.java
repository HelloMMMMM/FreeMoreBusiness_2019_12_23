package com.fx.feemore.business.ui.home.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:权益包项列表适配器
 * author: frj
 * modify date: 2018/12/30
 */
public class VMInterestItem extends ViewModel
{

    private static final int TYPE_MY_INTEREST = 1;
    private static final int TYPE_CONSUME_RECORD = 2;

    private DataSource dataSource;
    private int type;
    //当前页码
    public int currPage;

    @Inject
    public VMInterestItem(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 设置当前是我的权益包类型
     */
    public void setMyInterest()
    {
        type = TYPE_MY_INTEREST;
    }

    /**
     * 设置当前是消费记录类型
     */
    public void setConsumeRecord()
    {
        type = TYPE_CONSUME_RECORD;
    }

    /**
     * 获取数据
     *
     * @param page
     */
    public void getData(int page)
    {
        if (TYPE_MY_INTEREST == type)
        {
            getMyInterestData(page);
        } else if (TYPE_CONSUME_RECORD == type)
        {
            getConsumeRecord(page);
        }
    }

    /**
     * 获取我的权益包数据
     *
     * @param page 页码
     */
    private void getMyInterestData(int page)
    {

    }

    /**
     * 获取权益包消费记录数据
     *
     * @param page 页码
     */
    private void getConsumeRecord(int page)
    {

    }
}
