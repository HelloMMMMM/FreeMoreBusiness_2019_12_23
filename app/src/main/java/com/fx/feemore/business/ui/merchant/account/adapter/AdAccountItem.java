package com.fx.feemore.business.ui.merchant.account.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseBindingViewHolder;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.AccountBean;
import com.fx.feemore.business.databinding.AdAccountItemBinding;

/**
 * function:子账号项
 * author: frj
 * modify date: 2019/5/15
 */
public class AdAccountItem extends BaseDataBindingAdapter<AccountBean, AdAccountItemBinding>
{
    public AdAccountItem()
    {
        super(R.layout.ad_account_item);
    }

    @Override
    protected void convert(AdAccountItemBinding binding, AccountBean item)
    {

    }

    @Override
    protected void convert(BaseBindingViewHolder<AdAccountItemBinding> helper, AdAccountItemBinding binding, AccountBean item)
    {
        super.convert(helper, binding, item);
        binding.setItem(item);
        helper.addOnClickListener(R.id.tv_remove);
    }
}
