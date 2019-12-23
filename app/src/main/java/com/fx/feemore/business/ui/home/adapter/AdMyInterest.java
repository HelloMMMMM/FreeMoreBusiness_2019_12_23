package com.fx.feemore.business.ui.home.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.databinding.AdMyInterestBinding;

/**
 * function:我的权益包列表适配器
 * author: frj
 * modify date: 2018/12/30
 */
public class AdMyInterest extends BaseDataBindingAdapter<InterestBean, AdMyInterestBinding>
{
    public AdMyInterest()
    {
        super(R.layout.ad_my_interest);
    }

    @Override
    protected void convert(AdMyInterestBinding binding, InterestBean item)
    {
        binding.setItem(item);
    }
}
