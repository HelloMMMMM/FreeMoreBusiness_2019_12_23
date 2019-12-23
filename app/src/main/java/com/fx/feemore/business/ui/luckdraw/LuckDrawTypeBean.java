package com.fx.feemore.business.ui.luckdraw;

public class LuckDrawTypeBean {

    private String s1;
    private String s2;
    private boolean isSelected;

    public LuckDrawTypeBean(String s1, String s2, boolean isSelected) {
        this.s1 = s1;
        this.s2 = s2;
        this.isSelected = isSelected;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
