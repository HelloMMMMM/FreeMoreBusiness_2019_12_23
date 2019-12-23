package com.fx.feemore.business.ui.home.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.AdOrderCompleteBinding;

/**
 * function:已完成订单列表适配器
 * author: frj
 * modify date: 2018/12/30
 */
public class AdOrderComplete extends BaseDataBindingAdapter<OrderBean, AdOrderCompleteBinding>
{
    public AdOrderComplete()
    {
        super(R.layout.ad_order_complete);
    }

    @Override
    protected void convert(AdOrderCompleteBinding binding, OrderBean item)
    {
        binding.setItem(item);
    }
}
