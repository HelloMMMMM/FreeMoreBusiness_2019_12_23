package com.fx.feemore.business.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * function:消费记录实体信息
 * author: frj
 * modify date: 2018/12/30
 */
public class ConsumeRecordBean implements Parcelable
{
    private String CARDRECORD_ID;//记录id
    private String NUMBERS;               //核销使用次数
    private String MEMBERID;//会员id
    private String CARDID;//权益包id
    private String STOREID;//店铺id
    private String MEMBERIMG;//会员头像
    private String ORDERID;//订单id
    private String CARDIMG;//权益包图片
    private String CARDNAME;   //权益包名称
    private String NICKNAME;//会员昵称
    private String USETIME;//核销使用时间
    private String CREATEBY;
    private String USEDATE;//使用日期
    private String AMOUNT;//使用金额
    private String CREATETIME;

    public String getCARDRECORD_ID()
    {
        return CARDRECORD_ID;
    }

    public void setCARDRECORD_ID(String CARDRECORD_ID)
    {
        this.CARDRECORD_ID = CARDRECORD_ID;
    }

    public String getNUMBERS()
    {
        return NUMBERS;
    }

    public void setNUMBERS(String NUMBERS)
    {
        this.NUMBERS = NUMBERS;
    }

    public String getMEMBERID()
    {
        return MEMBERID;
    }

    public void setMEMBERID(String MEMBERID)
    {
        this.MEMBERID = MEMBERID;
    }

    public String getCARDID()
    {
        return CARDID;
    }

    public void setCARDID(String CARDID)
    {
        this.CARDID = CARDID;
    }

    public String getSTOREID()
    {
        return STOREID;
    }

    public void setSTOREID(String STOREID)
    {
        this.STOREID = STOREID;
    }

    public String getMEMBERIMG()
    {
        return MEMBERIMG;
    }

    public void setMEMBERIMG(String MEMBERIMG)
    {
        this.MEMBERIMG = MEMBERIMG;
    }

    public String getORDERID()
    {
        return ORDERID;
    }

    public void setORDERID(String ORDERID)
    {
        this.ORDERID = ORDERID;
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

    public String getNICKNAME()
    {
        return NICKNAME;
    }

    public void setNICKNAME(String NICKNAME)
    {
        this.NICKNAME = NICKNAME;
    }

    public String getUSETIME()
    {
        return USETIME;
    }

    public void setUSETIME(String USETIME)
    {
        this.USETIME = USETIME;
    }

    public String getCREATEBY()
    {
        return CREATEBY;
    }

    public void setCREATEBY(String CREATEBY)
    {
        this.CREATEBY = CREATEBY;
    }

    public String getUSEDATE()
    {
        return USEDATE;
    }

    public void setUSEDATE(String USEDATE)
    {
        this.USEDATE = USEDATE;
    }

    public String getAMOUNT()
    {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT)
    {
        this.AMOUNT = AMOUNT;
    }

    public String getCREATETIME()
    {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME)
    {
        this.CREATETIME = CREATETIME;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.CARDRECORD_ID);
        dest.writeString(this.NUMBERS);
        dest.writeString(this.MEMBERID);
        dest.writeString(this.CARDID);
        dest.writeString(this.STOREID);
        dest.writeString(this.MEMBERIMG);
        dest.writeString(this.ORDERID);
        dest.writeString(this.CARDIMG);
        dest.writeString(this.CARDNAME);
        dest.writeString(this.NICKNAME);
        dest.writeString(this.USETIME);
        dest.writeString(this.CREATEBY);
        dest.writeString(this.USEDATE);
        dest.writeString(this.AMOUNT);
        dest.writeString(this.CREATETIME);
    }

    public ConsumeRecordBean()
    {
    }

    protected ConsumeRecordBean(Parcel in)
    {
        this.CARDRECORD_ID = in.readString();
        this.NUMBERS = in.readString();
        this.MEMBERID = in.readString();
        this.CARDID = in.readString();
        this.STOREID = in.readString();
        this.MEMBERIMG = in.readString();
        this.ORDERID = in.readString();
        this.CARDIMG = in.readString();
        this.CARDNAME = in.readString();
        this.NICKNAME = in.readString();
        this.USETIME = in.readString();
        this.CREATEBY = in.readString();
        this.USEDATE = in.readString();
        this.AMOUNT = in.readString();
        this.CREATETIME = in.readString();
    }

    public static final Parcelable.Creator<ConsumeRecordBean> CREATOR = new Parcelable.Creator<ConsumeRecordBean>()
    {
        @Override
        public ConsumeRecordBean createFromParcel(Parcel source)
        {
            return new ConsumeRecordBean(source);
        }

        @Override
        public ConsumeRecordBean[] newArray(int size)
        {
            return new ConsumeRecordBean[size];
        }
    };
}
