package com.fx.feemore.business.ui.order.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseBindingViewHolder;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.OrderRefundBean;
import com.fx.feemore.business.databinding.AdOrderRightsProtectionV2Binding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:订单管理-维权
 * author: frj
 * modify date: 2019/1/18
 */
public class AdOrderRightsProtection extends BaseDataBindingAdapter<OrderRefundBean, AdOrderRightsProtectionV2Binding> {
    public AdOrderRightsProtection() {
        super(R.layout.ad_order_rights_protection_v2);
    }

    @Override
    protected void convert(AdOrderRightsProtectionV2Binding binding, OrderRefundBean item) {

    }

    @Override
    protected void convert(BaseBindingViewHolder<AdOrderRightsProtectionV2Binding> helper, AdOrderRightsProtectionV2Binding binding, OrderRefundBean item) {
        super.convert(helper, binding, item);
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getIMG());
        if (5 == item.getSTATUS()) {
            binding.tvStatus.setText("待退款");
        } else if (6 == item.getSTATUS()) {
            binding.tvStatus.setText("已退款");
        } else if (7 == item.getSTATUS()) {
            binding.tvStatus.setText("已驳回");
        }
        helper.addOnClickListener(R.id.btn_agree);
        helper.addOnClickListener(R.id.btn_refuse);
    }
}
