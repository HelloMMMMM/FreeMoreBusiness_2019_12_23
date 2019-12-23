package com.fx.feemore.business.ui.home.vm;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.fx.feemore.business.ui.home.adapter.AdUtil;

public class VMUtilLabel implements MultiItemEntity {

    private String label;

    public VMUtilLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int getItemType() {
        return AdUtil.TYPE_LABEL;
    }
}
