package com.fx.feemore.business.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * function:订单实体信息
 * author: frj
 * modify date: 2018/12/24
 */
public class OrderBean implements Parcelable
{
    private String TOTAL;           //拼团总数
    private String IMG;//拼团图片
    private String SELLNUM;           //已拼团数
    private String PRICE;            //价格
    private String MEMBERID;//会员id
    private String QUANTITY;            //购买数量
    private String STOREID;//店铺id
    private String ORDERTYPE;          //类型，2拼团，1普通
    private String PAYTYPE;//1,
    private String AVAILABLENUM;//可用数量
    private String NAME;           //权益包名称
    private Integer STATUS;//订单状态，1已付款，2已完成
    private String ORDERTIME;//下单时间
    private String MEMBERIMG;//会员头像
    private String NICKNAME;   //会员昵称
    private String ORDER_ID;//订单id
    private String AMOUNT;         //总金额

    private String HAVEUSEDNUM;// 3,			//已使用次数
    private String MESSAGE;// 评论内容
    private String APPLYTIME;// 申请时间？
    private String REVIEWTIME;// 回复时间
    private String REMARK;//备注


    public String getTOTAL()
    {
        return TOTAL;
    }

    public void setTOTAL(String TOTAL)
    {
        this.TOTAL = TOTAL;
    }

    public String getIMG()
    {
        return IMG;
    }

    public void setIMG(String IMG)
    {
        this.IMG = IMG;
    }

    public String getSELLNUM()
    {
        return SELLNUM;
    }

    public void setSELLNUM(String SELLNUM)
    {
        this.SELLNUM = SELLNUM;
    }

    public String getPRICE()
    {
        return PRICE;
    }

    public void setPRICE(String PRICE)
    {
        this.PRICE = PRICE;
    }

    public String getMEMBERID()
    {
        return MEMBERID;
    }

    public void setMEMBERID(String MEMBERID)
    {
        this.MEMBERID = MEMBERID;
    }

    public String getQUANTITY()
    {
        return QUANTITY;
    }

    public void setQUANTITY(String QUANTITY)
    {
        this.QUANTITY = QUANTITY;
    }

    public String getSTOREID()
    {
        return STOREID;
    }

    public void setSTOREID(String STOREID)
    {
        this.STOREID = STOREID;
    }

    public String getORDERTYPE()
    {
        return ORDERTYPE;
    }

    public void setORDERTYPE(String ORDERTYPE)
    {
        this.ORDERTYPE = ORDERTYPE;
    }

    public String getPAYTYPE()
    {
        return PAYTYPE;
    }

    public void setPAYTYPE(String PAYTYPE)
    {
        this.PAYTYPE = PAYTYPE;
    }

    public String getAVAILABLENUM()
    {
        return AVAILABLENUM;
    }

    public void setAVAILABLENUM(String AVAILABLENUM)
    {
        this.AVAILABLENUM = AVAILABLENUM;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
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

    public String getAMOUNT()
    {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT)
    {
        this.AMOUNT = AMOUNT;
    }

    public String getHAVEUSEDNUM()
    {
        return HAVEUSEDNUM;
    }

    public void setHAVEUSEDNUM(String HAVEUSEDNUM)
    {
        this.HAVEUSEDNUM = HAVEUSEDNUM;
    }

    public String getMESSAGE()
    {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE)
    {
        this.MESSAGE = MESSAGE;
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


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.TOTAL);
        dest.writeString(this.IMG);
        dest.writeString(this.SELLNUM);
        dest.writeString(this.PRICE);
        dest.writeString(this.MEMBERID);
        dest.writeString(this.QUANTITY);
        dest.writeString(this.STOREID);
        dest.writeString(this.ORDERTYPE);
        dest.writeString(this.PAYTYPE);
        dest.writeString(this.AVAILABLENUM);
        dest.writeString(this.NAME);
        dest.writeValue(this.STATUS);
        dest.writeString(this.ORDERTIME);
        dest.writeString(this.MEMBERIMG);
        dest.writeString(this.NICKNAME);
        dest.writeString(this.ORDER_ID);
        dest.writeString(this.AMOUNT);
        dest.writeString(this.HAVEUSEDNUM);
        dest.writeString(this.MESSAGE);
        dest.writeString(this.APPLYTIME);
        dest.writeString(this.REVIEWTIME);
        dest.writeString(this.REMARK);
    }

    public OrderBean()
    {
    }

    protected OrderBean(Parcel in)
    {
        this.TOTAL = in.readString();
        this.IMG = in.readString();
        this.SELLNUM = in.readString();
        this.PRICE = in.readString();
        this.MEMBERID = in.readString();
        this.QUANTITY = in.readString();
        this.STOREID = in.readString();
        this.ORDERTYPE = in.readString();
        this.PAYTYPE = in.readString();
        this.AVAILABLENUM = in.readString();
        this.NAME = in.readString();
        this.STATUS = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ORDERTIME = in.readString();
        this.MEMBERIMG = in.readString();
        this.NICKNAME = in.readString();
        this.ORDER_ID = in.readString();
        this.AMOUNT = in.readString();
        this.HAVEUSEDNUM = in.readString();
        this.MESSAGE = in.readString();
        this.APPLYTIME = in.readString();
        this.REVIEWTIME = in.readString();
        this.REMARK = in.readString();
    }

    public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>()
    {
        @Override
        public OrderBean createFromParcel(Parcel source)
        {
            return new OrderBean(source);
        }

        @Override
        public OrderBean[] newArray(int size)
        {
            return new OrderBean[size];
        }
    };
}
