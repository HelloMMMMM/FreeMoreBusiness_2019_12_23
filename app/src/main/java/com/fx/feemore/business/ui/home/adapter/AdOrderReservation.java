package com.fx.feemore.business.ui.home.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseBindingViewHolder;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.AdOrderReservationBinding;

/**
 * function:预约订单列表适配器
 * author: frj
 * modify date: 2018/12/29
 */
public class AdOrderReservation extends BaseDataBindingAdapter<OrderBean, AdOrderReservationBinding>
{
    public AdOrderReservation()
    {
        super(R.layout.ad_order_reservation);
    }

    @Override
    protected void convert(AdOrderReservationBinding binding, OrderBean item)
    {

    }

    @Override
    protected void convert(BaseBindingViewHolder<AdOrderReservationBinding> helper, AdOrderReservationBinding binding, OrderBean item)
    {
        binding.setItem(item);
        helper.addOnClickListener(R.id.btn_pass);
        helper.addOnClickListener(R.id.btn_turn_down);
    }
}
