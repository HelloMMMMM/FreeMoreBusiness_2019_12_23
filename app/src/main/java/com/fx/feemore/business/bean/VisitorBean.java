package com.fx.feemore.business.bean;

/**
 * function:访问信息实体
 * author: frj
 * modify date: 2019/1/17
 */
public class VisitorBean
{
    private String VISITORRECORD_ID;//访客记录id
    private String MEMBERID;//访客id
    private String STOREID;//店铺id
    private String NICKNAME;//访客昵称
    private String MEMBERIMG;//访客会员头像地址
    private String NAME;//名称
    private String TYPE;//
    private String CREATETIME;//访问时间

    public String getVISITORRECORD_ID()
    {
        return VISITORRECORD_ID;
    }

    public void setVISITORRECORD_ID(String VISITORRECORD_ID)
    {
        this.VISITORRECORD_ID = VISITORRECORD_ID;
    }

    public String getMEMBERID()
    {
        return MEMBERID;
    }

    public void setMEMBERID(String MEMBERID)
    {
        this.MEMBERID = MEMBERID;
    }

    public String getSTOREID()
    {
        return STOREID;
    }

    public void setSTOREID(String STOREID)
    {
        this.STOREID = STOREID;
    }

    public String getNICKNAME()
    {
        return NICKNAME;
    }

    public void setNICKNAME(String NICKNAME)
    {
        this.NICKNAME = NICKNAME;
    }

    public String getMEMBERIMG()
    {
        return MEMBERIMG;
    }

    public void setMEMBERIMG(String MEMBERIMG)
    {
        this.MEMBERIMG = MEMBERIMG;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }

    public String getTYPE()
    {
        return TYPE;
    }

    public void setTYPE(String TYPE)
    {
        this.TYPE = TYPE;
    }

    public String getCREATETIME()
    {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME)
    {
        this.CREATETIME = CREATETIME;
    }
}
