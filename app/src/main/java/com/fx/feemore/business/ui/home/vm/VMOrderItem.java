package com.fx.feemore.business.ui.home.vm;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.repository.DataSource;

import javax.inject.Inject;

/**
 * function:订单列表ViewModel
 * author: frj
 * modify date: 2018/12/24
 */
public class VMOrderItem extends ViewModel
{

    /**
     * 预约
     */
    public static final int TYPE_RESERVATION = 0;
    /**
     * 待消费
     */
    public static final int TYPE_WAIT_CONSUME = 1;
    /**
     * 维权
     */
    public static final int TYPE_RIGHTS_PROTECTION = 2;
    /**
     * 已完成
     */
    public static final int TYPE_COMPLETE = 3;

    private DataSource dataSource;
    //订单类型值
    private int orderType;
    //当前页码
    public int currPage = -1;

    @Inject
    public VMOrderItem(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }


    /**
     * 设置订单类型
     *
     * @param type 类型值，值为{@link VMOrderItem#TYPE_RESERVATION},{@link VMOrderItem#TYPE_WAIT_CONSUME},{@link VMOrderItem#TYPE_RIGHTS_PROTECTION},{@link VMOrderItem#TYPE_COMPLETE}
     */
    public void setOrderType(int type)
    {
        orderType = type;
    }

    /**
     * 获取数据
     *
     * @param page 页码
     */
    public void getData(int page)
    {
        if (TYPE_RESERVATION == orderType)
        {

        } else if (TYPE_WAIT_CONSUME == orderType)
        {

        } else if (TYPE_RIGHTS_PROTECTION == orderType)
        {

        }

    }
}
