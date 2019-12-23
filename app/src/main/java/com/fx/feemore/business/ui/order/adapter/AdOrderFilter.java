package com.fx.feemore.business.ui.order.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fx.feemore.business.R;
import com.fx.feemore.business.ui.order.bean.OrderFilterBean;

import java.util.List;

public class AdOrderFilter extends BaseQuickAdapter<OrderFilterBean, BaseViewHolder> {

    public AdOrderFilter(int layoutResId, @Nullable List<OrderFilterBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderFilterBean item) {
        helper.setText(R.id.tv_filter, item.getFilter());
    }
}
