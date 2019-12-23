package com.fx.feemore.business.ui.commodity.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseBindingViewHolder;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.databinding.AdCommodityUsingBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:商品管理-发布中
 * author: frj
 * modify date: 2019/1/18
 */
public class AdCommodityUsing extends BaseDataBindingAdapter<InterestBean, AdCommodityUsingBinding>
{

    public AdCommodityUsing()
    {
        super(R.layout.ad_commodity_using);
    }

    @Override
    protected void convert(AdCommodityUsingBinding binding, InterestBean item)
    {
    }

    @Override
    protected void convert(BaseBindingViewHolder<AdCommodityUsingBinding> helper, AdCommodityUsingBinding binding, InterestBean item)
    {
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getIMG());
        helper.addOnClickListener(R.id.btn_removed);
    }
}
