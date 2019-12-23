package com.fx.feemore.business.bean;

/**
 * function:联盟实体信息
 * author: frj
 * modify date: 2019/1/2
 */
public class AllianceBean
{
    private String IMG;//图片地址
    private String STATUS;//状态
    private String MERCHANTALLIANCE_ID;//联盟id
    private String DESCRIPTION;//描述
    private String STOREID;//店铺id
    private String CREATETIME;//发起时间
    private String TYPE;//类型
    private String NAME;//商家联盟名称

    public String getIMG()
    {
        return IMG;
    }

    public void setIMG(String IMG)
    {
        this.IMG = IMG;
    }

    public String getSTATUS()
    {
        return STATUS;
    }

    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getMERCHANTALLIANCE_ID()
    {
        return MERCHANTALLIANCE_ID;
    }

    public void setMERCHANTALLIANCE_ID(String MERCHANTALLIANCE_ID)
    {
        this.MERCHANTALLIANCE_ID = MERCHANTALLIANCE_ID;
    }

    public String getDESCRIPTION()
    {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION)
    {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getSTOREID()
    {
        return STOREID;
    }

    public void setSTOREID(String STOREID)
    {
        this.STOREID = STOREID;
    }

    public String getCREATETIME()
    {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME)
    {
        this.CREATETIME = CREATETIME;
    }

    public String getTYPE()
    {
        return TYPE;
    }

    public void setTYPE(String TYPE)
    {
        this.TYPE = TYPE;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }
}
