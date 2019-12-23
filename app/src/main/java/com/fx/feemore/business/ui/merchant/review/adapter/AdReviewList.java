package com.fx.feemore.business.ui.merchant.review.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseBindingViewHolder;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.databinding.AdReviewListBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:运营审核列表
 * author: frj
 * modify date: 2019/5/14
 */
public class AdReviewList extends BaseDataBindingAdapter<InterestBean, AdReviewListBinding>
{
    public AdReviewList()
    {
        super(R.layout.ad_review_list);
    }

    @Override
    protected void convert(AdReviewListBinding binding, InterestBean item)
    {
    }

    @Override
    protected void convert(BaseBindingViewHolder<AdReviewListBinding> helper, AdReviewListBinding binding, InterestBean item)
    {
        super.convert(helper, binding, item);
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getIMG());
        binding.tvType.setText(String.format(binding.tvType.getContext().getString(R.string.apply_item_type), item.getTYPE() == 1 ? "普通权益包" : "拼团权益包"));
        helper.addOnClickListener(R.id.btn_pass);
        helper.addOnClickListener(R.id.btn_refuse);
    }
}
