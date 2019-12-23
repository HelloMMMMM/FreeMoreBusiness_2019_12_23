package com.fx.feemore.business.ui.finance.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.IncomeRecordBean;
import com.fx.feemore.business.bean.RevenueRecordBean;
import com.fx.feemore.business.databinding.AdIncomeRecordBinding;

/**
 * function:收益记录列表适配器
 * author: frj
 * modify date: 2018/12/31
 */
public class AdIncomeRecord extends BaseDataBindingAdapter<RevenueRecordBean, AdIncomeRecordBinding>
{
    public AdIncomeRecord()
    {
        super(R.layout.ad_income_record);
    }

    @Override
    protected void convert(AdIncomeRecordBinding binding, RevenueRecordBean item)
    {
        binding.setItem(item);
    }
}
