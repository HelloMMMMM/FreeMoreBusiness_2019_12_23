package com.fx.feemore.business.ui.order.adapter;

import android.support.v7.widget.GridLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.databinding.AdOrderCommentBinding;
import com.fx.feemore.business.ui.apply.adapter.AdImgItem;
import com.fx.feemore.business.ui.order.bean.CommentBean;
import com.fx.feemore.business.util.GlideLoad;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.DisplayUtil;

/**
 * function:订单管理-评价订单列表适配器
 * author: frj
 * modify date: 2019/1/18
 */
public class AdOrderComment extends BaseDataBindingAdapter<CommentBean, AdOrderCommentBinding> {
    public AdOrderComment() {
        super(R.layout.ad_order_comment);
    }

    @Override
    protected void convert(AdOrderCommentBinding binding, CommentBean item) {
        binding.setItem(item);
        GlideLoad.load(binding.img, item.getORDERIMG());
        binding.starView.setStarRating(item.getTYPE());

        if (binding.recyclerView.getItemDecorationCount() == 0) {
            binding.recyclerView.setLayoutManager(new GridLayoutManager(binding.recyclerView.getContext(), 4));
            binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(binding.recyclerView.getContext(), 8), false, false, true));
        }
        binding.recyclerView.setFocusableInTouchMode(false);

        int itemWidth = DisplayUtil.getScreenSize(AppContext.getInstanceBase()).widthPixels
                - DensityUtil.dip2px(AppContext.getInstanceBase(), 15) * 2
                - DensityUtil.dip2px(AppContext.getInstanceBase(), 8) * 3;

        AdImgItem adImgItem = new AdImgItem(itemWidth);
        adImgItem.bindToRecyclerView(binding.recyclerView);
        adImgItem.setNewData(item.getListPicture());
    }
}
