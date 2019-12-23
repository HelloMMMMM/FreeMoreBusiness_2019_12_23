package com.fx.feemore.business.ui.apply.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.databinding.AdApplyItemBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:申请记录列表适配器
 * author: frj
 * modify date: 2019/1/18
 */
public class AdApplyItem extends BaseDataBindingAdapter<InterestBean, AdApplyItemBinding>
{
    /**
     * 状态：平台待审核
     */
    public static final int STATUS_PLATFORM_APPLICATION = 0;
    /**
     * 状态：平台审核通过
     */
    public static final int STATUS_PLATFORM_PASS = 1;
    /**
     * 状态：平台内审核不通过
     */
    public static final int STATUS_PLATFORM_REFUSED = -99;
    /**
     * 状态：商家内审核不通过
     */
    public static final int STATUS_MERCHANT_REFUSED = -1;
    /**
     * 状态：商家内待审核
     */
    public static final int STATUS_MERCHANT_APPLICATION = -2;

    public AdApplyItem()
    {
        super(R.layout.ad_apply_item);
    }

    @Override
    protected void convert(AdApplyItemBinding binding, InterestBean item)
    {
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getIMG());
        if (STATUS_PLATFORM_APPLICATION == item.getSTATUS())
        {   //申请中
            binding.tvStatus.setText("平台审核中");
        } else if (STATUS_PLATFORM_PASS == item.getSTATUS())
        {   //已通过
            binding.tvStatus.setText("已通过");
        } else if (STATUS_PLATFORM_REFUSED == item.getSTATUS())
        {   //未通过
            binding.tvStatus.setText("平台审核未通过");
        } else if (STATUS_MERCHANT_REFUSED == item.getSTATUS())
        {   //未通过
            binding.tvStatus.setText("店铺审核未通过");
        } else
        {
            binding.tvStatus.setText("店铺审核中");
        }

        binding.tvType.setText(String.format(binding.tvType.getContext().getString(R.string.apply_item_type), item.getTYPE() == 1 ? "普通权益包" : "拼团权益包"));
    }
}
