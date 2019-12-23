package com.fx.feemore.business.ui.notify.adapter;

import android.text.TextUtils;
import android.view.View;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.NotifyBean;
import com.fx.feemore.business.databinding.AdNotifyOrderBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:订单通知列表适配器
 * author: frj
 * modify date: 2019/1/17
 */
public class AdNotifyOrder extends BaseDataBindingAdapter<NotifyBean, AdNotifyOrderBinding>
{
    public AdNotifyOrder()
    {
        super(R.layout.ad_notify_order);
    }

    @Override
    protected void convert(AdNotifyOrderBinding binding, NotifyBean item)
    {
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getORDERIMG());

        if (TextUtils.isEmpty(item.getORDER_ID()))
        {
            binding.img.setVisibility(View.GONE);
            binding.tvCommodityName.setVisibility(View.GONE);
            binding.tvOrderTime.setVisibility(View.GONE);
            binding.tvOrderNum.setVisibility(View.GONE);
        } else
        {
            binding.img.setVisibility(View.VISIBLE);
            binding.tvCommodityName.setVisibility(View.VISIBLE);
            binding.tvOrderTime.setVisibility(View.VISIBLE);
            binding.tvOrderNum.setVisibility(View.VISIBLE);
        }
    }
}
