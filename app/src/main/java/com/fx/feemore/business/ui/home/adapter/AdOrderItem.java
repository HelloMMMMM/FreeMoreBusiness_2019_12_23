package com.fx.feemore.business.ui.home.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseBindingViewHolder;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.AdOrderItemBinding;

/**
 * function:订单列表适配器
 * author: frj
 * modify date: 2018/12/24
 */
public class AdOrderItem extends BaseDataBindingAdapter<OrderBean, AdOrderItemBinding>
{

    private int type;

    public AdOrderItem(int type)
    {
        super(R.layout.ad_order_item);
        this.type = type;
    }

    @Override
    protected void convert(AdOrderItemBinding binding, OrderBean item)
    {

    }

    @Override
    protected void convert(BaseBindingViewHolder<AdOrderItemBinding> helper, AdOrderItemBinding binding, OrderBean item)
    {
        binding.setItem(item);
    }
}
