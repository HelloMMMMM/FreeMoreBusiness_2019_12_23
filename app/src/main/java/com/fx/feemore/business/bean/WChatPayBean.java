package com.fx.feemore.business.bean;

import com.google.gson.annotations.SerializedName;

/**
 * function:微信支付实体信息
 * author: frj
 * modify date: 2019/2/17
 */
public class WChatPayBean
{
    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageStr;
    private String partnerid;
    private String prepayid;
    private String sign;
    private String timestamp;

    public String getAppid()
    {
        return appid;
    }

    public void setAppid(String appid)
    {
        this.appid = appid;
    }

    public String getNoncestr()
    {
        return noncestr;
    }

    public void setNoncestr(String noncestr)
    {
        this.noncestr = noncestr;
    }

    public String getPackageStr()
    {
        return packageStr;
    }

    public void setPackageStr(String packageStr)
    {
        this.packageStr = packageStr;
    }

    public String getPartnerid()
    {
        return partnerid;
    }

    public void setPartnerid(String partnerid)
    {
        this.partnerid = partnerid;
    }

    public String getPrepayid()
    {
        return prepayid;
    }

    public void setPrepayid(String prepayid)
    {
        this.prepayid = prepayid;
    }

    public String getSign()
    {
        return sign;
    }

    public void setSign(String sign)
    {
        this.sign = sign;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }
}
