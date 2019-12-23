package com.fx.feemore.business.ui.home.fragment.order;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.FOrderItemBinding;
import com.fx.feemore.business.ui.home.adapter.AdOrderRightsProtection;
import com.fx.feemore.business.ui.home.vm.VMOrderItem;
import com.fx.feemore.business.ui.order.AcOrderRightsProtection;

import java.util.ArrayList;
import java.util.List;

/**
 * function:维权订单列表
 * author: frj
 * modify date: 2018/12/30
 */
public class FOrderRightsProtection extends FragmentOrderItem<FOrderItemBinding, VMOrderItem>
{
    public static FOrderRightsProtection newInstance()
    {
        FOrderRightsProtection fragment = new FOrderRightsProtection();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, VMOrderItem.TYPE_RIGHTS_PROTECTION);
        return fragment;
    }

    @NonNull
    @Override
    protected BaseQuickAdapter getAdapter()
    {
        AdOrderRightsProtection adapter = new AdOrderRightsProtection();
        adapter.setNewData(getList());
        adapter.setOnItemClickListener((adapter1, view, position) -> jump(AcOrderRightsProtection.class));
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
