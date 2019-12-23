package com.fx.feemore.business.ui.order.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.AdOrderCompleteV2Binding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:订单管理-已完成
 * author: frj
 * modify date: 2019/1/18
 */
public class AdOrderComplete extends BaseDataBindingAdapter<OrderBean, AdOrderCompleteV2Binding>
{
    public AdOrderComplete()
    {
        super(R.layout.ad_order_complete_v2);
    }

    @Override
    protected void convert(AdOrderCompleteV2Binding binding, OrderBean item)
    {
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getIMG());
        GlideLoad.loadAvartar(binding.imgAvartar, item.getMEMBERIMG());
        //订单状态，1已付款，2已完成
        if (item.getSTATUS() == 1)
        {
            binding.tvStatus.setText("已付款");
        } else
        {
            binding.tvStatus.setText("已完成");
        }
    }
}
