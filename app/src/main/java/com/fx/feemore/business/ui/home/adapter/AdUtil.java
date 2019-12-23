package com.fx.feemore.business.ui.home.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.fx.feemore.business.R;
import com.fx.feemore.business.ui.home.vm.VMUtilLabel;
import com.fx.feemore.business.ui.home.vm.VMUtilOption;

import java.util.List;

public class AdUtil extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LABEL = 1;
    public static final int TYPE_OPTION = 2;

    public AdUtil(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LABEL, R.layout.layout_util_label_item);
        addItemType(TYPE_OPTION, R.layout.layout_util_option_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case TYPE_LABEL:
                VMUtilLabel vmUtilLabel = (VMUtilLabel) item;
                helper.setText(R.id.tv_label, vmUtilLabel.getLabel());
                break;
            case TYPE_OPTION:
                VMUtilOption vmUtilOption = (VMUtilOption) item;
                Glide.with(mContext).load(vmUtilOption.getImage()).into((ImageView) helper.getView(R.id.iv_util));
                helper.setText(R.id.tv_util, vmUtilOption.getOption());
                break;
        }
    }
}
