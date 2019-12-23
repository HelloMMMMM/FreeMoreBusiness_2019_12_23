package com.fx.feemore.business.bean;

/**
 * function:消费信息实体（核销订单确认）
 * author: frj
 * modify date: 2019/2/26
 */
public class ConsumeBean
{

    private String MEMBERID;//会员id
    private String NAME;//名称
    private String CARDID;
    private String STATUS;                //状态，1可用，2已失效
    private String TYPE;//类型
    private String REMAINTIMES;        //剩余使用次数
    private String REMARK;//备注
    private String CREATETIME;   //获取卡包时间
    private String ENDTIME;      //卡包到期时间
    private String STOREID;     //店铺id
    private String MYCARD_ID;//我的卡包id
    private String VERIFICATIONCODE;    //核销码
    private String CARDLEVEL_ID;    //权益包id
    private String ORDERDATE;       //订单日期
    private String AREACODE;        //区域码
    private String PRICE;           //价格
    private String GROUPS;          //2定制卡，3活动，1基础卡类型
    private String VERIFICATIONCARDID;  //验证卡id
    private String BASECARDORDER_ID;    //基础卡id

    public String getMEMBERID()
    {
        return MEMBERID;
    }

    public void setMEMBERID(String MEMBERID)
    {
        this.MEMBERID = MEMBERID;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }

    public String getCARDID()
    {
        return CARDID;
    }

    public void setCARDID(String CARDID)
    {
        this.CARDID = CARDID;
    }

    public String getSTATUS()
    {
        return STATUS;
    }

    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getTYPE()
    {
        return TYPE;
    }

    public void setTYPE(String TYPE)
    {
        this.TYPE = TYPE;
    }

    public String getREMAINTIMES()
    {
        return REMAINTIMES;
    }

    public void setREMAINTIMES(String REMAINTIMES)
    {
        this.REMAINTIMES = REMAINTIMES;
    }

    public String getREMARK()
    {
        return REMARK;
    }

    public void setREMARK(String REMARK)
    {
        this.REMARK = REMARK;
    }

    public String getCREATETIME()
    {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME)
    {
        this.CREATETIME = CREATETIME;
    }

    public String getENDTIME()
    {
        return ENDTIME;
    }

    public void setENDTIME(String ENDTIME)
    {
        this.ENDTIME = ENDTIME;
    }

    public String getSTOREID()
    {
        return STOREID;
    }

    public void setSTOREID(String STOREID)
    {
        this.STOREID = STOREID;
    }

    public String getMYCARD_ID()
    {
        return MYCARD_ID;
    }

    public void setMYCARD_ID(String MYCARD_ID)
    {
        this.MYCARD_ID = MYCARD_ID;
    }

    public String getVERIFICATIONCODE()
    {
        return VERIFICATIONCODE;
    }

    public void setVERIFICATIONCODE(String VERIFICATIONCODE)
    {
        this.VERIFICATIONCODE = VERIFICATIONCODE;
    }

    public String getCARDLEVEL_ID()
    {
        return CARDLEVEL_ID;
    }

    public void setCARDLEVEL_ID(String CARDLEVEL_ID)
    {
        this.CARDLEVEL_ID = CARDLEVEL_ID;
    }

    public String getORDERDATE()
    {
        return ORDERDATE;
    }

    public void setORDERDATE(String ORDERDATE)
    {
        this.ORDERDATE = ORDERDATE;
    }

    public String getAREACODE()
    {
        return AREACODE;
    }

    public void setAREACODE(String AREACODE)
    {
        this.AREACODE = AREACODE;
    }

    public String getPRICE()
    {
        return PRICE;
    }

    public void setPRICE(String PRICE)
    {
        this.PRICE = PRICE;
    }

    public String getGROUPS()
    {
        return GROUPS;
    }

    public void setGROUPS(String GROUPS)
    {
        this.GROUPS = GROUPS;
    }

    public String getVERIFICATIONCARDID()
    {
        return VERIFICATIONCARDID;
    }

    public void setVERIFICATIONCARDID(String VERIFICATIONCARDID)
    {
        this.VERIFICATIONCARDID = VERIFICATIONCARDID;
    }

    public String getBASECARDORDER_ID()
    {
        return BASECARDORDER_ID;
    }

    public void setBASECARDORDER_ID(String BASECARDORDER_ID)
    {
        this.BASECARDORDER_ID = BASECARDORDER_ID;
    }
}
