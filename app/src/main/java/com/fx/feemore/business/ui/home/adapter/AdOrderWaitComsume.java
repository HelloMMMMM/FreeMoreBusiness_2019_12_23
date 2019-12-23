package com.fx.feemore.business.ui.home.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.AdOrderWaitConsumeBinding;

/**
 * function:待消费订单列表适配器
 * author: frj
 * modify date: 2018/12/30
 */
public class AdOrderWaitComsume extends BaseDataBindingAdapter<OrderBean, AdOrderWaitConsumeBinding>
{

    public AdOrderWaitComsume()
    {
        super(R.layout.ad_order_wait_consume);
    }

    @Override
    protected void convert(AdOrderWaitConsumeBinding binding, OrderBean item)
    {
        binding.setItem(item);
    }
}
