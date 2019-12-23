package com.fx.feemore.business.bean;

/**
 * function:财政收入记录实体信息
 * author: frj
 * modify date: 2019/3/5
 */
public class RevenueRecordBean
{

    private Integer ISINCOME;//1,                //1收入类型，0支出
    private String MEMBERID;//
    private String STOREID;//店铺id
    private Integer ORDERTYPE;                //订单类型，1普通权益包，2拼团权益包
    private Integer PAYTYPE;                    //支付类型，1人民币
    private String NAME;              //商品名称
    private String ORDERID;//订单id
    private String STATUS;//
    private Integer ISINVITE;                   //0不是邀请提成
    private String AMOUNT;                //收入金额
    private String DESCRIPTION;    //描述
    private String STOREINCOMERECORD_ID;//财政收入记录id
    private String CREATETIME;     //记录时间
    private String DEPTH;      //ISINVITE=1时有效，层级1：M1, 2:M2

    public Integer getISINCOME()
    {
        return ISINCOME == null ? 0 : ISINCOME;
    }

    public void setISINCOME(Integer ISINCOME)
    {
        this.ISINCOME = ISINCOME;
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

    public Integer getORDERTYPE()
    {
        return ORDERTYPE == null ? 0 : ORDERTYPE;
    }

    public void setORDERTYPE(Integer ORDERTYPE)
    {
        this.ORDERTYPE = ORDERTYPE;
    }

    public Integer getPAYTYPE()
    {
        return PAYTYPE == null ? 0 : PAYTYPE;
    }

    public void setPAYTYPE(Integer PAYTYPE)
    {
        this.PAYTYPE = PAYTYPE;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }

    public String getORDERID()
    {
        return ORDERID;
    }

    public void setORDERID(String ORDERID)
    {
        this.ORDERID = ORDERID;
    }

    public String getSTATUS()
    {
        return STATUS;
    }

    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }

    public Integer getISINVITE()
    {
        return ISINVITE == null ? 0 : ISINVITE;
    }

    public void setISINVITE(Integer ISINVITE)
    {
        this.ISINVITE = ISINVITE;
    }

    public String getAMOUNT()
    {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT)
    {
        this.AMOUNT = AMOUNT;
    }

    public String getDESCRIPTION()
    {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION)
    {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getSTOREINCOMERECORD_ID()
    {
        return STOREINCOMERECORD_ID;
    }

    public void setSTOREINCOMERECORD_ID(String STOREINCOMERECORD_ID)
    {
        this.STOREINCOMERECORD_ID = STOREINCOMERECORD_ID;
    }

    public String getCREATETIME()
    {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME)
    {
        this.CREATETIME = CREATETIME;
    }

    public String getDEPTH()
    {
        return DEPTH;
    }

    public void setDEPTH(String DEPTH)
    {
        this.DEPTH = DEPTH;
    }
}
