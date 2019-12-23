package com.fx.feemore.business.ui.merchant.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.ActiveBean;
import com.fx.feemore.business.databinding.AdApplyActiveItemBinding;

/**
 * function:活动权益包申请列表适配器
 * author: frj
 * modify date: 2019/1/2
 */
public class AdApplyActiveItem extends BaseDataBindingAdapter<ActiveBean, AdApplyActiveItemBinding>
{
    public AdApplyActiveItem()
    {
        super(R.layout.ad_apply_active_item);
    }

    @Override
    protected void convert(AdApplyActiveItemBinding binding, ActiveBean item)
    {
        binding.setItem(item);
    }
}
