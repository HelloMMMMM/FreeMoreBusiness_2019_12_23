package com.fx.feemore.business.bean;

/**
 * function:交易数据实体信息
 * author: frj
 * modify date: 2019/2/15
 */
public class TransactionBean
{
    private String STOREID;//店铺id
    private String NAME;//权益包名称
    private String IMG;//图片地址
    private String PRICE;   //单价
    private String AMOUNT;//成交金额
    private String STATUS;//状态
    private String ORDERTIME;//订单时间
    private String ORDER_ID;//订单id
    private String MEMBERIMG;//会员头像
    private String NICKNAME;//会员昵称
    private Integer PAYTYPE;//paytype 支付类型，1人民币，3抽奖，4兑换

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

    public String getIMG()
    {
        return IMG;
    }

    public void setIMG(String IMG)
    {
        this.IMG = IMG;
    }

    public String getPRICE()
    {
        return PRICE;
    }

    public void setPRICE(String PRICE)
    {
        this.PRICE = PRICE;
    }

    public String getAMOUNT()
    {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT)
    {
        this.AMOUNT = AMOUNT;
    }

    public String getSTATUS()
    {
        return STATUS;
    }

    public void setSTATUS(String STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getORDERTIME()
    {
        return ORDERTIME;
    }

    public void setORDERTIME(String ORDERTIME)
    {
        this.ORDERTIME = ORDERTIME;
    }

    public String getORDER_ID()
    {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID)
    {
        this.ORDER_ID = ORDER_ID;
    }

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

    public Integer getPAYTYPE()
    {
        return PAYTYPE == null ? 0 : PAYTYPE;
    }

    public void setPAYTYPE(Integer PAYTYPE)
    {
        this.PAYTYPE = PAYTYPE;
    }
}
