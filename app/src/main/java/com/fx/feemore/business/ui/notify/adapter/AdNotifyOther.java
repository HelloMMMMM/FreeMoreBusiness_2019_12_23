package com.fx.feemore.business.ui.notify.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.databinding.AdNotifyOtherBinding;

/**
 * function:其他通知列表适配器
 * author: frj
 * modify date: 2019/1/17
 */
public class AdNotifyOther extends BaseDataBindingAdapter<String, AdNotifyOtherBinding>
{
    public AdNotifyOther()
    {
        super(R.layout.ad_notify_other);
    }

    @Override
    protected void convert(AdNotifyOtherBinding binding, String item)
    {
        binding.setItem(item);
    }
}
