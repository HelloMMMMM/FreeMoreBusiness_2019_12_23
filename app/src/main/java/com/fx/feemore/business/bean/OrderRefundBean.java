package com.fx.feemore.business.bean;

/**
 * function:订单退款申请接口
 * author: frj
 * modify date: 2019/2/14
 */
public class OrderRefundBean
{
    private String STOREID; //店铺id
    private String NAME;    //权益包名称
    private String IMG;     //图片地址
    private String PRICE;   //价格
    private String AMOUNT;  //总数
    private Integer STATUS;  //状态，5待退款，6退款成功，7被驳回
    private String ORDERTIME;   //下单时间
    private String APPLYTIME;   //申请时间
    private String REVIEWTIME;
    private String REMARK;      //申请退款原因
    private String MESSAGE;     //驳回退款原因
    private String MEMBERIMG;   //用户头像
    private String NICKNAME;    //用户昵称
    private String ORDER_ID;    //订单id

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

    public Integer getSTATUS()
    {
        return STATUS == null ? 0 : STATUS;
    }

    public void setSTATUS(Integer STATUS)
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

    public String getAPPLYTIME()
    {
        return APPLYTIME;
    }

    public void setAPPLYTIME(String APPLYTIME)
    {
        this.APPLYTIME = APPLYTIME;
    }

    public String getREVIEWTIME()
    {
        return REVIEWTIME;
    }

    public void setREVIEWTIME(String REVIEWTIME)
    {
        this.REVIEWTIME = REVIEWTIME;
    }

    public String getREMARK()
    {
        return REMARK;
    }

    public void setREMARK(String REMARK)
    {
        this.REMARK = REMARK;
    }

    public String getMESSAGE()
    {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE)
    {
        this.MESSAGE = MESSAGE;
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

    public String getORDER_ID()
    {
        return ORDER_ID;
    }

    public void setORDER_ID(String ORDER_ID)
    {
        this.ORDER_ID = ORDER_ID;
    }
}
