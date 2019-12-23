package com.fx.feemore.business.ui.merchant.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.databinding.AdMemberItemBinding;

/**
 * function:会员活跃度排行榜列表适配器
 * author: frj
 * modify date: 2018/12/31
 */
public class AdMemberItem extends BaseDataBindingAdapter<String, AdMemberItemBinding>
{
    public AdMemberItem()
    {
        super(R.layout.ad_member_item);
    }

    @Override
    protected void convert(AdMemberItemBinding binding, String item)
    {
        binding.setItem(item);
    }
}
