package com.fx.feemore.business.bean;

/**
 * function:收藏实体信息
 * author: frj
 * modify date: 2019/2/15
 */
public class CollectionBean
{
    private String MEMBERIMG;//会员头像地址
    private String NICKNAME;//会员昵称
    private String COLLECTION_ID;//收藏id
    private String STOREID;//店铺id
    private String NAME;//名称
    private String MEMBERID;//会员id
    private String ITEMID;//
    private String TYPE;//
    private String CREATETIME;//收藏时间

    public String getMEMBERIMG()
    {
        return MEMBERIMG;
    }

    public void setMEMBERIMG(String MEMBERIMG)
    {
        this.MEMBERIMG = MEMBERIMG;
    }

    public String getNICKNAME()
    {
        return NICKNAME;
    }

    public void setNICKNAME(String NICKNAME)
    {
        this.NICKNAME = NICKNAME;
    }

    public String getCOLLECTION_ID()
    {
        return COLLECTION_ID;
    }

    public void setCOLLECTION_ID(String COLLECTION_ID)
    {
        this.COLLECTION_ID = COLLECTION_ID;
    }

    public String getSTOREID()
    {
        return STOREID;
    }

    public void setSTOREID(String STOREID)
    {
        this.STOREID = STOREID;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }

    public String getMEMBERID()
    {
        return MEMBERID;
    }

    public void setMEMBERID(String MEMBERID)
    {
        this.MEMBERID = MEMBERID;
    }

    public String getITEMID()
    {
        return ITEMID;
    }

    public void setITEMID(String ITEMID)
    {
        this.ITEMID = ITEMID;
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
