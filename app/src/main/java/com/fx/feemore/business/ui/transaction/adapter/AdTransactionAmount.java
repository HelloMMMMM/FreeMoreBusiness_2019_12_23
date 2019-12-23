package com.fx.feemore.business.ui.transaction.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.TransactionBean;
import com.fx.feemore.business.databinding.AdTransactionAmountBinding;
import com.fx.feemore.business.util.VerificationUtil;

/**
 * function:近日交易数据-交易额列表适配器
 * author: frj
 * modify date: 2019/1/17
 */
public class AdTransactionAmount extends BaseDataBindingAdapter<TransactionBean, AdTransactionAmountBinding>
{
    public AdTransactionAmount()
    {
        super(R.layout.ad_transaction_amount);
    }

    @Override
    protected void convert(AdTransactionAmountBinding binding, TransactionBean item)
    {
        binding.setItem(item);
        if (item.getPAYTYPE() == 3)
        {
            binding.tvMoney.setText("抽奖");
        } else if (item.getPAYTYPE() == 4)
        {
            binding.tvMoney.setText("兑换");
        } else
        {
            binding.tvMoney.setText(String.format(binding.tvMoney.getContext().getString(R.string.commodity_using_interest_price), VerificationUtil.verifyDefault(item.getAMOUNT())));
        }
    }
}
