package com.fx.feemore.business.ui.commodity.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.ConsumeRecordBean;
import com.fx.feemore.business.databinding.AdCommodityConsumeRecordBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:消费记录列表适配器
 * author: frj
 * modify date: 2019/3/1
 */
public class AdCommodityConsumeRecord extends BaseDataBindingAdapter<ConsumeRecordBean, AdCommodityConsumeRecordBinding> {
    public AdCommodityConsumeRecord() {
        super(R.layout.ad_commodity_consume_record);
    }

    @Override
    protected void convert(AdCommodityConsumeRecordBinding binding, ConsumeRecordBean item) {
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getCARDIMG());
        GlideLoad.load(binding.imgAvartar, item.getMEMBERIMG());
    }
}
