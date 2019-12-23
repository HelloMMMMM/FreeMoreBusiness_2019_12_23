package com.fx.feemore.business.bean;

/**
 * function:权益包实体信息
 * author: frj
 * modify date: 2018/12/30
 */
public class InterestBean
{
    private String STOREID;     //店铺id
    private String NAME;        //权益包名称
    private String QUANTITY;    //总数
    private String SELLNUM;     //已售出数量
    private Integer STATUS;  //状态；0：申请中；1：审核通过；-1：下线锁定
    private Integer TYPE;    //类型；1，普通权益包；2：拼团权益包
    private String CARD_ID; //权益包id
    private String ENDTIME; //结束时间
    private String STARTTIME;   //开始时间
    private String IMG;   //权益包图片地址
    private String APPLYTIME;   //权益包申请时间
    private String PRICE;   //价格
    private String OLDPRICE;    //价格

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

    public String getQUANTITY()
    {
        return QUANTITY;
    }

    public void setQUANTITY(String QUANTITY)
    {
        this.QUANTITY = QUANTITY;
    }

    public Integer getSTATUS()
    {
        return STATUS == null ? 0 : STATUS;
    }

    public void setSTATUS(Integer STATUS)
    {
        this.STATUS = STATUS;
    }

    public Integer getTYPE()
    {
        return TYPE == null ? 0 : TYPE;
    }

    public void setTYPE(Integer TYPE)
    {
        this.TYPE = TYPE;
    }

    public String getCARD_ID()
    {
        return CARD_ID;
    }

    public void setCARD_ID(String CARD_ID)
    {
        this.CARD_ID = CARD_ID;
    }

    public String getSELLNUM()
    {
        return SELLNUM;
    }

    public void setSELLNUM(String SELLNUM)
    {
        this.SELLNUM = SELLNUM;
    }

    public String getENDTIME()
    {
        return ENDTIME;
    }

    public void setENDTIME(String ENDTIME)
    {
        this.ENDTIME = ENDTIME;
    }

    public String getSTARTTIME()
    {
        return STARTTIME;
    }

    public void setSTARTTIME(String STARTTIME)
    {
        this.STARTTIME = STARTTIME;
    }

    public String getIMG()
    {
        return IMG;
    }

    public void setIMG(String IMG)
    {
        this.IMG = IMG;
    }

    public String getAPPLYTIME()
    {
        return APPLYTIME;
    }

    public void setAPPLYTIME(String APPLYTIME)
    {
        this.APPLYTIME = APPLYTIME;
    }

    public String getPRICE()
    {
        return PRICE;
    }

    public void setPRICE(String PRICE)
    {
        this.PRICE = PRICE;
    }

    public String getOLDPRICE()
    {
        return OLDPRICE;
    }

    public void setOLDPRICE(String OLDPRICE)
    {
        this.OLDPRICE = OLDPRICE;
    }
}
