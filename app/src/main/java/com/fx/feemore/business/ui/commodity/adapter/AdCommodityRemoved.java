package com.fx.feemore.business.ui.commodity.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseBindingViewHolder;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.databinding.AdCommodityRemovedBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:商品管理-已下架
 * author: frj
 * modify date: 2019/1/18
 */
public class AdCommodityRemoved extends BaseDataBindingAdapter<InterestBean, AdCommodityRemovedBinding>
{

    public AdCommodityRemoved()
    {
        super(R.layout.ad_commodity_removed);
    }

    @Override
    protected void convert(AdCommodityRemovedBinding binding, InterestBean item)
    {
    }

    @Override
    protected void convert(BaseBindingViewHolder<AdCommodityRemovedBinding> helper, AdCommodityRemovedBinding binding, InterestBean item)
    {
        binding.setItem(item);
        GlideLoad.load(binding.img,item.getIMG());
        helper.addOnClickListener(R.id.btn_publish);
        helper.addOnClickListener(R.id.btn_delete);
    }
}
