package com.fx.feemore.business.ui.home.vm;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.fx.feemore.business.ui.home.adapter.AdUtil;

public class VMUtilOption implements MultiItemEntity {

    private Object image;
    private String option;
    private int optionId;

    public VMUtilOption(Object image, String option, int optionId) {
        this.image = image;
        this.option = option;
        this.optionId = optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getOptionId() {
        return optionId;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public int getItemType() {
        return AdUtil.TYPE_OPTION;
    }
}
