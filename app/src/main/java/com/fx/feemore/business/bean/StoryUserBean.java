package com.fx.feemore.business.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * function:商户用户信息
 * author: frj
 * modify date: 2019/1/21
 */
public class StoryUserBean implements Serializable, Cloneable
{

    private String POPULARITY;//人气值
    private String PAGEVIEWS;//浏览量
    private String STORE_ID;//店铺id
    private String NAME;//店铺名称
    private String TELEPHONE;//店铺电话
    private String STOREKEEPER;//"
    private String ADDRESS;//店铺地址
    private String LON;//经度
    private String LAT;//纬度
    private String CITY;//城市
    private String BUSINESSHOURS;//营业时间
    private String TYPE;//店铺类型
    private String GROUPS;//1,
    private Integer STATUS;////状态，0申请中，1通过，-1不通过
    private String SORTS;//1,
    private String ACCOUNT;//"13995611567",
    private String BALANCE;//余额
    private String AMOUNT;  //实收总余额
    private String IDCARD;//"123123123",
    private String ACTUALNAME;//"张三",
    private String REMARK;//驳回原因
    private String DESCRIPTION;//"测",
    private Double FREEZE;//营销费保证金
    private Double MARGIN;//店铺保证金
    private String AREAID;//区域码
    private String CATEGORYID;//"1"
    private String CREATETIME;  //创建时间
    private String IMG1;        //店铺照片
    private String IMG2;        //店内照片
    private String IMG3;        //身份证照片
    private String IMG4;        //营业执照照片
    private Integer ACCTYPE;    //帐号类型，1 运营者、2 操作者、0 店主
    private String ZINAME;      //登录人名称
    private String ZIACCOUNT;   //登录帐号

    public String getPOPULARITY()
    {
        return POPULARITY;
    }

    public void setPOPULARITY(String POPULARITY)
    {
        this.POPULARITY = POPULARITY;
    }

    public String getPAGEVIEWS()
    {
        return PAGEVIEWS;
    }

    public void setPAGEVIEWS(String PAGEVIEWS)
    {
        this.PAGEVIEWS = PAGEVIEWS;
    }

    public String getSTORE_ID()
    {
        return STORE_ID;
    }

    public void setSTORE_ID(String STORE_ID)
    {
        this.STORE_ID = STORE_ID;
    }

    public String getNAME()
    {
        return NAME;
    }

    public void setNAME(String NAME)
    {
        this.NAME = NAME;
    }

    public String getTELEPHONE()
    {
        return TELEPHONE;
    }

    public void setTELEPHONE(String TELEPHONE)
    {
        this.TELEPHONE = TELEPHONE;
    }

    public String getSTOREKEEPER()
    {
        return STOREKEEPER;
    }

    public void setSTOREKEEPER(String STOREKEEPER)
    {
        this.STOREKEEPER = STOREKEEPER;
    }

    public String getADDRESS()
    {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS)
    {
        this.ADDRESS = ADDRESS;
    }

    public String getLON()
    {
        return LON;
    }

    public void setLON(String LON)
    {
        this.LON = LON;
    }

    public String getLAT()
    {
        return LAT;
    }

    public void setLAT(String LAT)
    {
        this.LAT = LAT;
    }

    public String getCITY()
    {
        return CITY;
    }

    public void setCITY(String CITY)
    {
        this.CITY = CITY;
    }

    public String getBUSINESSHOURS()
    {
        return BUSINESSHOURS;
    }

    public void setBUSINESSHOURS(String BUSINESSHOURS)
    {
        this.BUSINESSHOURS = BUSINESSHOURS;
    }

    public String getTYPE()
    {
        return TYPE;
    }

    public void setTYPE(String TYPE)
    {
        this.TYPE = TYPE;
    }

    public String getGROUPS()
    {
        return GROUPS;
    }

    public void setGROUPS(String GROUPS)
    {
        this.GROUPS = GROUPS;
    }

    public Integer getSTATUS()
    {
        return STATUS == null ? 0 : STATUS;
    }

    public void setSTATUS(Integer STATUS)
    {
        this.STATUS = STATUS;
    }

    public String getSORTS()
    {
        return SORTS;
    }

    public void setSORTS(String SORTS)
    {
        this.SORTS = SORTS;
    }

    public String getACCOUNT()
    {
        return ACCOUNT;
    }

    public void setACCOUNT(String ACCOUNT)
    {
        this.ACCOUNT = ACCOUNT;
    }

    public String getBALANCE()
    {
        return BALANCE;
    }

    public void setBALANCE(String BALANCE)
    {
        this.BALANCE = BALANCE;
    }

    public String getIDCARD()
    {
        return IDCARD;
    }

    public void setIDCARD(String IDCARD)
    {
        this.IDCARD = IDCARD;
    }

    public String getACTUALNAME()
    {
        return ACTUALNAME;
    }

    public void setACTUALNAME(String ACTUALNAME)
    {
        this.ACTUALNAME = ACTUALNAME;
    }

    public String getREMARK()
    {
        return REMARK;
    }

    public void setREMARK(String REMARK)
    {
        this.REMARK = REMARK;
    }

    public String getDESCRIPTION()
    {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION)
    {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getAREAID()
    {
        return AREAID;
    }

    public void setAREAID(String AREAID)
    {
        this.AREAID = AREAID;
    }

    public String getCATEGORYID()
    {
        return CATEGORYID;
    }

    public void setCATEGORYID(String CATEGORYID)
    {
        this.CATEGORYID = CATEGORYID;
    }

    public String getCREATETIME()
    {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME)
    {
        this.CREATETIME = CREATETIME;
    }

    public Double getFREEZE()
    {
        return FREEZE == null ? 0 : FREEZE;
    }

    public void setFREEZE(Double FREEZE)
    {
        this.FREEZE = FREEZE;
    }

    public Double getMARGIN()
    {
        return MARGIN == null ? 0 : MARGIN;
    }

    public void setMARGIN(Double MARGIN)
    {
        this.MARGIN = MARGIN;
    }

    public String getIMG1()
    {
        return IMG1;
    }

    public void setIMG1(String IMG1)
    {
        this.IMG1 = IMG1;
    }

    public String getIMG2()
    {
        return IMG2;
    }

    public void setIMG2(String IMG2)
    {
        this.IMG2 = IMG2;
    }

    public String getIMG3()
    {
        return IMG3;
    }

    public void setIMG3(String IMG3)
    {
        this.IMG3 = IMG3;
    }

    public String getIMG4()
    {
        return IMG4;
    }

    public void setIMG4(String IMG4)
    {
        this.IMG4 = IMG4;
    }

    public String getAMOUNT()
    {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT)
    {
        this.AMOUNT = AMOUNT;
    }

    public Integer getACCTYPE()
    {
        return ACCTYPE == null ? 0 : ACCTYPE;
    }

    public void setACCTYPE(Integer ACCTYPE)
    {
        this.ACCTYPE = ACCTYPE;
    }

    public String getZINAME()
    {
        return ZINAME;
    }

    public void setZINAME(String ZINAME)
    {
        this.ZINAME = ZINAME;
    }

    public String getZIACCOUNT()
    {
        if (TextUtils.isEmpty(ZIACCOUNT))
        {
            return ACCOUNT;
        }
        return ZIACCOUNT;
    }

    public void setZIACCOUNT(String ZIACCOUNT)
    {
        this.ZIACCOUNT = ZIACCOUNT;
    }

    @Override
    public StoryUserBean clone()
    {
        StoryUserBean bean = null;
        try
        {
            bean = (StoryUserBean) super.clone();
        } catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
            bean = new StoryUserBean();
        }
        return bean;
    }
}
