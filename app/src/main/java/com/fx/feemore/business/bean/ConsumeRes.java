package com.fx.feemore.business.bean;

/**
 * function:核销订单信息返回结果
 * author: frj
 * modify date: 2019/2/27
 */
public class ConsumeRes
{
    /**
     * 核销订单信息
     */
    private ConsumeBean BaseCardOrder;
    /**
     * 核销确认链接
     */
    private String URL;
    /**
     * 会员名称
     */
    private String MEMBERNAME;
    /**
     * 会员头像
     */
    private String MEMBERIMG;
    /**
     * 定制卡价格
     */
    private String CARDPRICE;
    /**
     * 定制卡图片
     */
    private String CARDIMG;
    /**
     * 定制卡名称
     */
    private String CARDNAME;
    /**
     * 使用次数
     */
    private String USEDNUM;
    /**
     * 类型
     */
    private String TYPE;

    public ConsumeBean getBaseCardOrder()
    {
        return BaseCardOrder;
    }

    public void setBaseCardOrder(ConsumeBean baseCardOrder)
    {
        BaseCardOrder = baseCardOrder;
    }

    public String getURL()
    {
        return URL;
    }

    public void setURL(String URL)
    {
        this.URL = URL;
    }

    public String getMEMBERNAME()
    {
        return MEMBERNAME;
    }

    public void setMEMBERNAME(String MEMBERNAME)
    {
        this.MEMBERNAME = MEMBERNAME;
    }

    public String getMEMBERIMG()
    {
        return MEMBERIMG;
    }

    public void setMEMBERIMG(String MEMBERIMG)
    {
        this.MEMBERIMG = MEMBERIMG;
    }

    public String getCARDPRICE()
    {
        return CARDPRICE;
    }

    public void setCARDPRICE(String CARDPRICE)
    {
        this.CARDPRICE = CARDPRICE;
    }

    public String getCARDIMG()
    {
        return CARDIMG;
    }

    public void setCARDIMG(String CARDIMG)
    {
        this.CARDIMG = CARDIMG;
    }

    public String getCARDNAME()
    {
        return CARDNAME;
    }

    public void setCARDNAME(String CARDNAME)
    {
        this.CARDNAME = CARDNAME;
    }

    public String getUSEDNUM()
    {
        return USEDNUM;
    }

    public void setUSEDNUM(String USEDNUM)
    {
        this.USEDNUM = USEDNUM;
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
