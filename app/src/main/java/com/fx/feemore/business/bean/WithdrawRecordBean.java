package com.fx.feemore.business.bean;

import android.text.TextUtils;

import com.fx.feemore.business.util.VerificationPerson;
import com.google.gson.annotations.SerializedName;

/**
 * function:提现记录实体信息
 * author: frj
 * modify date: 2018/11/3
 */
public class WithdrawRecordBean
{
    @SerializedName("FEE")
    private String fee; //提现手续费

    @SerializedName("TYPE")
    private String type;

    @SerializedName("CREATETIME")
    private String createtime; //提现生成时间

    @SerializedName("RATIO")
    private String ratio;        //提现的比例

    private String audittime;   //审核时间

    @SerializedName("STOREID")
    private String storeid;     //店铺id

    @SerializedName("BANKNO")
    private String bankno; //提现银行卡号

    private String bankname;   //银行名称

    @SerializedName("AMOUNT")
    private String amount;      //提现金额

    @SerializedName("MONEY")
    private String money;    //提现的人民币金额

    @SerializedName("STATUSNAME")
    private String statusname;    //提现的状态

    private String bankaddress;   //开户行地址

    @SerializedName("WITHDRAWRECORD_ID")
    private String withdrawrecord_ID;  //提现id

    @SerializedName("DESCRIPTION")
    private String description;   //提现备注，驳回原因

    private String remittancetime; //汇款时间
    //1	待审核
    //2	汇款中
    //3	提现成功
    //4	被驳回
    //0	全部
    @SerializedName("STATUS")
    private Integer status;  //状态

    @SerializedName("NAME")
    private String name;   //真实名称

    public String getFee()
    {
        return fee;
    }

    public void setFee(String fee)
    {
        this.fee = fee;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(String createtime)
    {
        this.createtime = createtime;
    }

    public String getRatio()
    {
        return ratio;
    }

    public void setRatio(String ratio)
    {
        this.ratio = ratio;
    }

    public String getAudittime()
    {
        return audittime;
    }

    public void setAudittime(String audittime)
    {
        this.audittime = audittime;
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

    public String getAmount()
    {
        return amount;
    }

    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getMoney()
    {
        return money;
    }

    public void setMoney(String money)
    {
        this.money = money;
    }

    public String getStatusname()
    {
        return statusname;
    }

    public void setStatusname(String statusname)
    {
        this.statusname = statusname;
    }

    public String getBankaddress()
    {
        return bankaddress;
    }

    public void setBankaddress(String bankaddress)
    {
        this.bankaddress = bankaddress;
    }

    public String getWithdrawrecord_ID()
    {
        return withdrawrecord_ID;
    }

    public void setWithdrawrecord_ID(String withdrawrecord_ID)
    {
        this.withdrawrecord_ID = withdrawrecord_ID;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getRemittancetime()
    {
        return remittancetime;
    }

    public void setRemittancetime(String remittancetime)
    {
        this.remittancetime = remittancetime;
    }

    public Integer getStatus()
    {
        return status == null ? 0 : status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStoreid()
    {
        return storeid;
    }

    public void setStoreid(String storeid)
    {
        this.storeid = storeid;
    }
}
