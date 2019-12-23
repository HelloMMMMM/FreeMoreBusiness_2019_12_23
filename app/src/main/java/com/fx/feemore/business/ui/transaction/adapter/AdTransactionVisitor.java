package com.fx.feemore.business.ui.transaction.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.VisitorBean;
import com.fx.feemore.business.databinding.AdTransactionVisitorBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:近日交易数据-访问者列表适配器
 * author: frj
 * modify date: 2019/1/17
 */
public class AdTransactionVisitor extends BaseDataBindingAdapter<VisitorBean, AdTransactionVisitorBinding>
{
    public AdTransactionVisitor()
    {
        super(R.layout.ad_transaction_visitor);
    }

    @Override
    protected void convert(AdTransactionVisitorBinding binding, VisitorBean item)
    {
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getMEMBERIMG());
    }
}
