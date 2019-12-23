package com.fx.feemore.business.ui.transaction.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.CollectionBean;
import com.fx.feemore.business.databinding.AdTransactionCollegeBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:近日交易数据-收藏列表适配器
 * author: frj
 * modify date: 2019/1/17
 */
public class AdTransactionCollege extends BaseDataBindingAdapter<CollectionBean, AdTransactionCollegeBinding>
{
    public AdTransactionCollege()
    {
        super(R.layout.ad_transaction_college);
    }

    @Override
    protected void convert(AdTransactionCollegeBinding binding, CollectionBean item)
    {
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getMEMBERIMG());
    }
}
