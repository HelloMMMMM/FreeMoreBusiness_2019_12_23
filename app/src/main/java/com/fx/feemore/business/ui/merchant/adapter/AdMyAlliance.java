package com.fx.feemore.business.ui.merchant.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.AllianceBean;
import com.fx.feemore.business.databinding.AdMyAllianceBinding;

/**
 * function:我发起的联盟列表适配器
 * author: frj
 * modify date: 2019/1/2
 */
public class AdMyAlliance extends BaseDataBindingAdapter<AllianceBean, AdMyAllianceBinding>
{
    public AdMyAlliance()
    {
        super(R.layout.ad_my_alliance);
    }

    @Override
    protected void convert(AdMyAllianceBinding binding, AllianceBean item)
    {
        binding.setItem(item);
    }
}
