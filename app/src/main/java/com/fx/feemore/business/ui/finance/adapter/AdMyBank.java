package com.fx.feemore.business.ui.finance.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseBindingViewHolder;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.BankCardBean;
import com.fx.feemore.business.databinding.AdMyBankBinding;
import com.fx.feemore.business.util.BankCardNoUtil;
import com.fx.feemore.business.util.VerificationUtil;

/**
 * function:我的银行卡列表适配器
 * author: frj
 * modify date: 2019/1/20
 */
public class AdMyBank extends BaseDataBindingAdapter<BankCardBean, AdMyBankBinding>
{
    public AdMyBank()
    {
        super(R.layout.ad_my_bank);
    }

    @Override
    protected void convert(AdMyBankBinding binding, BankCardBean item)
    {
    }

    @Override
    protected void convert(BaseBindingViewHolder<AdMyBankBinding> helper, AdMyBankBinding binding, BankCardBean item)
    {
        binding.setItem(item);
        VerificationUtil.setViewValue(binding.tvCard, BankCardNoUtil.cardAnonymization(item.getBankno()));
        helper.addOnClickListener(R.id.tv_delete);
    }

}
