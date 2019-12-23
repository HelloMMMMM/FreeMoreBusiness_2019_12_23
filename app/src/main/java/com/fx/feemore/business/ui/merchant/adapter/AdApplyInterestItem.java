package com.fx.feemore.business.ui.merchant.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.databinding.AdApplyInterestItemBinding;

/**
 * function:权益包申请进度项
 * author: frj
 * modify date: 2019/1/2
 */
public class AdApplyInterestItem extends BaseDataBindingAdapter<InterestBean, AdApplyInterestItemBinding>
{
    public AdApplyInterestItem()
    {
        super(R.layout.ad_apply_interest_item);
    }

    @Override
    protected void convert(AdApplyInterestItemBinding binding, InterestBean item)
    {
        binding.setItem(item);
    }
}
