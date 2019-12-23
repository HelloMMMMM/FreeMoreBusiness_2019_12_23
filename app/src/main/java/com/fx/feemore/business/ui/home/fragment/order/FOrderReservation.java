package com.fx.feemore.business.ui.home.fragment.order;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fx.feemore.business.R;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.ui.home.adapter.AdOrderReservation;
import com.fx.feemore.business.ui.home.vm.VMOrderItem;
import com.fx.feemore.business.ui.order.AcReservationDetail;
import com.fx.feemore.business.databinding.FOrderItemBinding;
import com.fx.feemore.business.ui.order.AcReserveationReject;

import java.util.ArrayList;
import java.util.List;

/**
 * function:预约订单界面
 * author: frj
 * modify date: 2018/12/29
 */
public class FOrderReservation extends FragmentOrderItem<FOrderItemBinding, VMOrderItem>
{
    public static FOrderReservation newInstance()
    {
        FOrderReservation fragment = new FOrderReservation();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, VMOrderItem.TYPE_RESERVATION);
        return fragment;
    }

    @NonNull
    @Override
    protected BaseQuickAdapter getAdapter()
    {
        AdOrderReservation adapter = new AdOrderReservation();
        adapter.setNewData(getList());
        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            if (R.id.btn_turn_down == view.getId())
            {
                jump(AcReserveationReject.class);
            }
        });
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            jump(AcReservationDetail.class);
        });
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
