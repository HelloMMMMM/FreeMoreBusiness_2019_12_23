package com.fx.feemore.business.bean;

/**
 * function:子账号实体信息
 * author: frj
 * modify date: 2019/5/15
 */
public class AccountBean
{
    private String ACCOUNT;
    private String STATUS;
    private String PASSWORD;
    private String STOREID;
    private String NUM;
    private String TELPHONE;
    private String CREATETIME;
    private Integer TYPE;//账号类型，1运营者，2操作者
    private String STOREUSER_ID;
    private String NAME;

    public String getACCOUNT()
    {
        return ACCOUNT;
    }

    public void setACCOUNT(String ACCOUNT)
    {
        this.ACCOUNT = ACCOUNT;
    }

    public String getSTATUS()
    {
        return STATUS;
    }

    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getPASSWORD()
    {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD)
    {
        this.PASSWORD = PASSWORD;
    }

    public String getSTOREID()
    {
        return STOREID;
    }

    public void setSTOREID(String STOREID)
    {
        this.STOREID = STOREID;
    }

    public String getNUM()
    {
        return NUM;
    }

    public void setNUM(String NUM)
    {
        this.NUM = NUM;
    }

    public String getTELPHONE()
    {
        return TELPHONE;
    }

    public void setTELPHONE(String TELPHONE)
    {
        this.TELPHONE = TELPHONE;
    }

    public String getCREATETIME()
    {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME)
    {
        this.CREATETIME = CREATETIME;
    }

    public Integer getTYPE()
    {
        return TYPE == null ? 1 : TYPE;
    }

    public void setTYPE(Integer TYPE)
    {
        this.TYPE = TYPE;
    }

    public String getSTOREUSER_ID()
    {
        return STOREUSER_ID;
    }

    public void setSTOREUSER_ID(String STOREUSER_ID)
    {
        this.STOREUSER_ID = STOREUSER_ID;
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
