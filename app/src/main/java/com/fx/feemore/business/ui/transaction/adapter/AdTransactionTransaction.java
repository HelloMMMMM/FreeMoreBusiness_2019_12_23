package com.fx.feemore.business.ui.transaction.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.TransactionBean;
import com.fx.feemore.business.databinding.AdTransactionTransactionBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:近日交易数据-成交数量列表适配器
 * author: frj
 * modify date: 2019/1/17
 */
public class AdTransactionTransaction extends BaseDataBindingAdapter<TransactionBean, AdTransactionTransactionBinding>
{
    public AdTransactionTransaction()
    {
        super(R.layout.ad_transaction_transaction);
    }

    @Override
    protected void convert(AdTransactionTransactionBinding binding, TransactionBean item)
    {
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getMEMBERIMG());
    }
}
