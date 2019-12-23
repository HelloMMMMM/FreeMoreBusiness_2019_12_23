package com.fx.feemore.business.ui.home.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.ConsumeRecordBean;
import com.fx.feemore.business.databinding.AdConsumeRecordBinding;

/**
 * function:消费记录列表适配器
 * author: frj
 * modify date: 2018/12/30
 */
public class AdConsumeRecord extends BaseDataBindingAdapter<ConsumeRecordBean, AdConsumeRecordBinding>
{
    public AdConsumeRecord()
    {
        super(R.layout.ad_consume_record);
    }

    @Override
    protected void convert(AdConsumeRecordBinding binding, ConsumeRecordBean item)
    {
        binding.setItem(item);
    }
}
