package com.fx.feemore.business.ui.merchant.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.databinding.AdMyActiveBinding;

/**
 * function:我的活动列表适配器
 * author: frj
 * modify date: 2018/12/31
 */
public class AdMyActive extends BaseDataBindingAdapter<String, AdMyActiveBinding>
{
    public AdMyActive()
    {
        super(R.layout.ad_my_active);
    }

    @Override
    protected void convert(AdMyActiveBinding binding, String item)
    {
        binding.setItem(item);
    }
}
