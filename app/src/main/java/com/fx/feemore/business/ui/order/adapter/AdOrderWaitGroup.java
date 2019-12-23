package com.fx.feemore.business.ui.order.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseBindingViewHolder;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.databinding.AdOrderWaitGroupBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:订单管理-待成团订单
 * author: frj
 * modify date: 2019/1/18
 */
public class AdOrderWaitGroup extends BaseDataBindingAdapter<OrderBean, AdOrderWaitGroupBinding>
{
    public AdOrderWaitGroup()
    {
        super(R.layout.ad_order_wait_group);
    }

    @Override
    protected void convert(AdOrderWaitGroupBinding binding, OrderBean item)
    {
    }

    @Override
    protected void convert(BaseBindingViewHolder<AdOrderWaitGroupBinding> helper, AdOrderWaitGroupBinding binding, OrderBean item)
    {
        super.convert(helper, binding, item);
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getIMG());
        GlideLoad.loadAvartar(binding.imgAvartar, item.getMEMBERIMG());
        //订单状态，1已付款，2已完成
        if (item.getSTATUS() == 1)
        {
            binding.tvStatus.setText("已付款");
        } else
        {
            binding.tvStatus.setText("已完成");
        }
        helper.addOnClickListener(R.id.btn_help);
    }
}
