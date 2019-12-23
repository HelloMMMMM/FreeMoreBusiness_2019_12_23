package com.fx.feemore.business.bean;

/**
 * function:收益实体信息
 * author: frj
 * modify date: 2019/3/4
 */
public class IncomeBean
{
    private String INVITETYPE;//邀请级别
    private String TO_IMG;//会员头像
    private String PRICE; //商品价格
    private String TO_NICKNAME; //会员名称
    private String ORDERTYPE;//订单类型
    private String RATIO;  //提成百分比
    private String PAYTYPE;  //支付类型，1人民币支付
    private String OBJID;
    private String INVITERID;
    private String REWARD;             //奖励金额
    private String NAME;          //商品名称
    private String TO_ACCOUNT;
    private String STATUS;//
    private String ARRIVALTIME;//到账时间
    private String PERFORMANCE_ID;
    private String TOMEMBERID;//会员id
    private String CREATETIME;      //购买时间
    private String TYPE;

    public String getINVITETYPE()
    {
        return INVITETYPE;
    }

    public void setINVITETYPE(String INVITETYPE)
    {
        this.INVITETYPE = INVITETYPE;
    }

    public String getTO_IMG()
    {
        return TO_IMG;
    }

    public void setTO_IMG(String TO_IMG)
    {
        this.TO_IMG = TO_IMG;
    }

    public String getPRICE()
    {
        return PRICE;
    }

    public void setPRICE(String PRICE)
    {
        this.PRICE = PRICE;
    }

    public String getTO_NICKNAME()
    {
        return TO_NICKNAME;
    }

    public void setTO_NICKNAME(String TO_NICKNAME)
    {
        this.TO_NICKNAME = TO_NICKNAME;
    }

    public String getORDERTYPE()
    {
        return ORDERTYPE;
    }

    public void setORDERTYPE(String ORDERTYPE)
    {
        this.ORDERTYPE = ORDERTYPE;
    }

    public String getRATIO()
    {
        return RATIO;
    }

    public void setRATIO(String RATIO)
    {
        this.RATIO = RATIO;
    }

    public String getPAYTYPE()
    {
        return PAYTYPE;
    }

    public void setPAYTYPE(String PAYTYPE)
    {
        this.PAYTYPE = PAYTYPE;
    }

    public String getOBJID()
    {
        return OBJID;
    }

    public void setOBJID(String OBJID)
    {
        this.OBJID = OBJID;
    }

    public String getINVITERID()
    {
        return INVITERID;
    }

    public void setINVITERID(String INVITERID)
    {
        this.INVITERID = INVITERID;
    }

    public String getREWARD()
    {
        return REWARD;
    }

    public void setREWARD(String REWARD)
    {
        this.REWARD = REWARD;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }

    public String getTO_ACCOUNT()
    {
        return TO_ACCOUNT;
    }

    public void setTO_ACCOUNT(String TO_ACCOUNT)
    {
        this.TO_ACCOUNT = TO_ACCOUNT;
    }

    public String getSTATUS()
    {
        return STATUS;
    }

    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getARRIVALTIME()
    {
        return ARRIVALTIME;
    }

    public void setARRIVALTIME(String ARRIVALTIME)
    {
        this.ARRIVALTIME = ARRIVALTIME;
    }

    public String getPERFORMANCE_ID()
    {
        return PERFORMANCE_ID;
    }

    public void setPERFORMANCE_ID(String PERFORMANCE_ID)
    {
        this.PERFORMANCE_ID = PERFORMANCE_ID;
    }

    public String getTOMEMBERID()
    {
        return TOMEMBERID;
    }

    public void setTOMEMBERID(String TOMEMBERID)
    {
        this.TOMEMBERID = TOMEMBERID;
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
}
