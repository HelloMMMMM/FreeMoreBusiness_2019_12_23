package com.fx.feemore.business.ui.home.fragment.order;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.FOrderItemBinding;
import com.fx.feemore.business.ui.home.adapter.AdOrderWaitComsume;
import com.fx.feemore.business.ui.home.vm.VMOrderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * function:待消费订单
 * author: frj
 * modify date: 2018/12/30
 */
public class FOrderWaitConsume extends FragmentOrderItem<FOrderItemBinding, VMOrderItem>
{

    public static FOrderWaitConsume newInstance()
    {
        FOrderWaitConsume fragment = new FOrderWaitConsume();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, VMOrderItem.TYPE_WAIT_CONSUME);
        return fragment;
    }

    @NonNull
    @Override
    protected BaseQuickAdapter getAdapter()
    {
        AdOrderWaitComsume adapter = new AdOrderWaitComsume();
        adapter.setNewData(getList());
        return adapter;
    }

    private List<OrderBean> getList()
    {
        List<OrderBean> list = new ArrayList<>();
        list.add(new OrderBean());
        list.add(new OrderBean());
        list.add(new OrderBean());
        list.add(new OrderBean());
        list.add(new OrderBean());
        return list;
    }
}
