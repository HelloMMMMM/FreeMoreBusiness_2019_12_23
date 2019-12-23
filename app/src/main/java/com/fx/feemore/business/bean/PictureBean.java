package com.fx.feemore.business.bean;

/**
 * function:图片实体信息
 * author: frj
 * modify date: 2019/2/17
 */
public class PictureBean
{
    private String PICTURE_ID;
    private String PATH;
    private String ITEMID;
    private String TYPE;
    private String CREATETIME;

    public String getPICTURE_ID()
    {
        return PICTURE_ID;
    }

    public void setPICTURE_ID(String PICTURE_ID)
    {
        this.PICTURE_ID = PICTURE_ID;
    }

    public String getPATH()
    {
        return PATH;
    }

    public void setPATH(String PATH)
    {
        this.PATH = PATH;
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
