package com.fx.feemore.business.ui.finance.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.WithdrawRecordBean;
import com.fx.feemore.business.databinding.AdWithdrawRecordBinding;
import com.fx.feemore.business.util.BankCardNoUtil;

/**
 * function:提现记录列表适配器
 * author: frj
 * modify date: 2019/1/20
 */
public class AdWithdrawRecord extends BaseDataBindingAdapter<WithdrawRecordBean, AdWithdrawRecordBinding>
{
    public AdWithdrawRecord()
    {
        super(R.layout.ad_withdraw_record);
    }

    @Override
    protected void convert(AdWithdrawRecordBinding binding, WithdrawRecordBean item)
    {
        binding.setItem(item);
        binding.tvBankCard.setText(BankCardNoUtil.cardAnonymization(item.getBankno()));
    }
}
