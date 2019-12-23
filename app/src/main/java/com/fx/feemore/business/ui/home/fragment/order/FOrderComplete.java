package com.fx.feemore.business.ui.home.fragment.order;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.FOrderItemBinding;
import com.fx.feemore.business.ui.home.adapter.AdOrderComplete;
import com.fx.feemore.business.ui.home.vm.VMOrderItem;
import com.fx.feemore.business.ui.order.AcOrderComplete;

import java.util.ArrayList;
import java.util.List;

/**
 * function:已完成订单界面
 * author: frj
 * modify date: 2018/12/30
 */
public class FOrderComplete extends FragmentOrderItem<FOrderItemBinding, VMOrderItem>
{

    public static FOrderComplete newInstance()
    {
        FOrderComplete fragment = new FOrderComplete();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, VMOrderItem.TYPE_COMPLETE);
        return fragment;
    }

    @NonNull
    @Override
    protected BaseQuickAdapter getAdapter()
    {
        AdOrderComplete adapter = new AdOrderComplete();
        adapter.setNewData(getList());
        adapter.setOnItemClickListener((adapter1, view, position) -> jump(AcOrderComplete.class));
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
