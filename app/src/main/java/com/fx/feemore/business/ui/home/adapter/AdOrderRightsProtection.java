package com.fx.feemore.business.ui.home.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.AdOrderRightsProtectionBinding;

/**
 * function:维权订单列表适配器
 * author: frj
 * modify date: 2018/12/30
 */
public class AdOrderRightsProtection extends BaseDataBindingAdapter<OrderBean, AdOrderRightsProtectionBinding>
{
    public AdOrderRightsProtection()
    {
        super(R.layout.ad_order_rights_protection);
    }

    @Override
    protected void convert(AdOrderRightsProtectionBinding binding, OrderBean item)
    {
        binding.setItem(item);
    }
}
