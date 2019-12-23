package com.fx.feemore.business.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.fx.feemore.business.util.VerificationPerson;
import com.google.gson.annotations.SerializedName;

/**
 * function:银行卡实体信息
 * author: frj
 * modify date: 2019/3/5
 */
public class BankCardBean implements Parcelable
{
    private String phoneno; //手机号
    private String address;//地址
    private String storeid;//会员id
    @SerializedName("ACTUALNAME")
    private String actualname;//开户人姓名
    @SerializedName("BANKNO")
    private String bankno;//银行卡号
    private String bankname;//银行名称
    @SerializedName("BANK_ID")
    private String bank_ID;//银行卡id

    public String getPhoneno()
    {
        return phoneno;
    }

    public void setPhoneno(String phoneno)
    {
        this.phoneno = phoneno;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getStoreid()
    {
        return storeid;
    }

    public void setStoreid(String storeid)
    {
        this.storeid = storeid;
    }

    public String getActualname()
    {
        return actualname;
    }

    public void setActualname(String actualname)
    {
        this.actualname = actualname;
    }

    public String getBankno()
    {
        return bankno;
    }

    public void setBankno(String bankno)
    {
        this.bankno = bankno;
    }

    public String getBankname()
    {
        if (TextUtils.isEmpty(bankname) && !TextUtils.isEmpty(bankno))
        {
            return new VerificationPerson(bankno).getBankName();
        }
        return bankname;
    }

    public void setBankname(String bankname)
    {
        this.bankname = bankname;
    }

    public String getBank_ID()
    {
        return bank_ID;
    }

    public void setBank_ID(String bank_ID)
    {
        this.bank_ID = bank_ID;
    }


    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.phoneno);
        dest.writeString(this.address);
        dest.writeString(this.storeid);
        dest.writeString(this.actualname);
        dest.writeString(this.bankno);
        dest.writeString(this.bankname);
        dest.writeString(this.bank_ID);
    }

    public BankCardBean()
    {
    }

    protected BankCardBean(Parcel in)
    {
        this.phoneno = in.readString();
        this.address = in.readString();
        this.storeid = in.readString();
        this.actualname = in.readString();
        this.bankno = in.readString();
        this.bankname = in.readString();
        this.bank_ID = in.readString();
    }

    public static final Parcelable.Creator<BankCardBean> CREATOR = new Parcelable.Creator<BankCardBean>()
    {
        @Override
        public BankCardBean createFromParcel(Parcel source)
        {
            return new BankCardBean(source);
        }

        @Override
        public BankCardBean[] newArray(int size)
        {
            return new BankCardBean[size];
        }
    };
}
