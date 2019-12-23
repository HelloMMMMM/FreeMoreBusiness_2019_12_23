package com.fx.feemore.business.ui.luckdraw;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fx.feemore.business.R;


public class AdLuckDrawType extends BaseQuickAdapter<LuckDrawTypeBean, BaseViewHolder> {
    public AdLuckDrawType(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LuckDrawTypeBean item) {
        helper.setText(R.id.tv_1, item.getS1());
        helper.setText(R.id.tv_2, item.getS2());
        View view = helper.getView(R.id.item);
        view.setSelected(item.isSelected());
    }
}
