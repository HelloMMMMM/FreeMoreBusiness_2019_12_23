package com.fx.feemore.business.ui.luckdraw;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fx.feemore.business.R;

public class AdRateSetting extends BaseQuickAdapter<LuckDrawTypeBean, BaseViewHolder> {
    AdRateSetting(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LuckDrawTypeBean item) {
        helper.setText(R.id.tv_type, item.getS1());
    }
}
