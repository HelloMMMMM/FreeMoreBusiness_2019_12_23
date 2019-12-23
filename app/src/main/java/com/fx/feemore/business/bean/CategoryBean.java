package com.fx.feemore.business.bean;

/**
 * function:栏目信息实体
 * author: frj
 * modify date: 2019/1/21
 */
public class CategoryBean
{
    //栏目id
    private String CATEGORY_ID;
    //栏目名称
    private String NAME;

    public String getCATEGORY_ID()
    {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(String CATEGORY_ID)
    {
        this.CATEGORY_ID = CATEGORY_ID;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }

    @Override
    public String toString()
    {
        return NAME;
    }
}
