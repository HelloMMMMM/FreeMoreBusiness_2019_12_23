package com.fx.feemore.business.bean;

import java.util.List;

/**
 * function:权益包详情实体
 * author: frj
 * modify date: 2019/1/21
 */
public class InterestInfoBean
{
    private String STOREID;//店铺id
    private String NAME;//权益包名称
    private String PRICE;//现价
    private String OLDPRICE;//原价
    private String QUANTITY;//数量
    private String AVAILABLETIMES;//有效使用次数
    private String TOPS;//1,
    private String SORT;//1,
    private Integer ISPRESELL;//是否预售；0：否；1：是
    private Integer ISREFUND;//是否支持无理由退款；0：否；1：是
    private Integer STATUS;//状态；0：申请中；1：正常；-1：不通过
    private Integer TYPE;//类型；1：普通权益包；2：拼团权益包
    private String CATEGORYNAME;//栏目名称
    private String STARTTIME;//开始时间
    private String ENDTIME;//结束时间
    private String DESCRIPTION;//描述
    private String IMG;//图片
    private String CREATETIME;//创建时间
    private String APPLYTIME;//申请时间
    private String AREAID;//区域id
    private String STARTCOSTTIME;//上午营业时间
    private String ENDCOSTTIME;//下午营业时间
    private String CONTACTE;//联系方式
    private String CATEGORYID;//栏目id
    private String NOTICE;//商家公告
    private String CUSTOMID;//
    private String CARD_ID;//权益包id
    private String STARTCOSTDATE;   //一周可开始使用周日期
    private String ENDCOSTDATE;     //一周结束使用周日期
    private Integer ISCURRENCY;  //是否节假日通用；0：否；1：是
    private String USERSNUM;  //使用人数
    private String REMARK;      //驳回原因
    private List<PictureBean> listPicture;

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

    public String getPRICE()
    {
        return PRICE;
    }

    public void setPRICE(String PRICE)
    {
        this.PRICE = PRICE;
    }

    public String getOLDPRICE()
    {
        return OLDPRICE;
    }

    public void setOLDPRICE(String OLDPRICE)
    {
        this.OLDPRICE = OLDPRICE;
    }

    public String getQUANTITY()
    {
        return QUANTITY;
    }

    public void setQUANTITY(String QUANTITY)
    {
        this.QUANTITY = QUANTITY;
    }

    public String getAVAILABLETIMES()
    {
        return AVAILABLETIMES;
    }

    public void setAVAILABLETIMES(String AVAILABLETIMES)
    {
        this.AVAILABLETIMES = AVAILABLETIMES;
    }

    public String getTOPS()
    {
        return TOPS;
    }

    public void setTOPS(String TOPS)
    {
        this.TOPS = TOPS;
    }

    public String getSORT()
    {
        return SORT;
    }

    public void setSORT(String SORT)
    {
        this.SORT = SORT;
    }

    public Integer getISPRESELL()
    {
        return ISPRESELL == null ? 0 : ISPRESELL;
    }

    public void setISPRESELL(Integer ISPRESELL)
    {
        this.ISPRESELL = ISPRESELL;
    }

    public Integer getISREFUND()
    {
        return ISREFUND == null ? 0 : ISREFUND;
    }

    public void setISREFUND(Integer ISREFUND)
    {
        this.ISREFUND = ISREFUND;
    }

    public Integer getSTATUS()
    {
        return STATUS == null ? 0 : STATUS;
    }

    public void setSTATUS(Integer STATUS)
    {
        this.STATUS = STATUS;
    }

    public Integer getTYPE()
    {
        return TYPE == null ? 0 : TYPE;
    }

    public void setTYPE(Integer TYPE)
    {
        this.TYPE = TYPE;
    }

    public String getCATEGORYNAME()
    {
        return CATEGORYNAME;
    }

    public void setCATEGORYNAME(String CATEGORYNAME)
    {
        this.CATEGORYNAME = CATEGORYNAME;
    }

    public String getSTARTTIME()
    {
        return STARTTIME;
    }

    public void setSTARTTIME(String STARTTIME)
    {
        this.STARTTIME = STARTTIME;
    }

    public String getENDTIME()
    {
        return ENDTIME;
    }

    public void setENDTIME(String ENDTIME)
    {
        this.ENDTIME = ENDTIME;
    }

    public String getDESCRIPTION()
    {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION)
    {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getIMG()
    {
        return IMG;
    }

    public void setIMG(String IMG)
    {
        this.IMG = IMG;
    }

    public String getCREATETIME()
    {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME)
    {
        this.CREATETIME = CREATETIME;
    }

    public String getAPPLYTIME()
    {
        return APPLYTIME;
    }

    public void setAPPLYTIME(String APPLYTIME)
    {
        this.APPLYTIME = APPLYTIME;
    }

    public String getAREAID()
    {
        return AREAID;
    }

    public void setAREAID(String AREAID)
    {
        this.AREAID = AREAID;
    }

    public String getSTARTCOSTTIME()
    {
        return STARTCOSTTIME;
    }

    public void setSTARTCOSTTIME(String STARTCOSTTIME)
    {
        this.STARTCOSTTIME = STARTCOSTTIME;
    }

    public String getENDCOSTTIME()
    {
        return ENDCOSTTIME;
    }

    public void setENDCOSTTIME(String ENDCOSTTIME)
    {
        this.ENDCOSTTIME = ENDCOSTTIME;
    }

    public String getCONTACTE()
    {
        return CONTACTE;
    }

    public void setCONTACTE(String CONTACTE)
    {
        this.CONTACTE = CONTACTE;
    }

    public String getCATEGORYID()
    {
        return CATEGORYID;
    }

    public void setCATEGORYID(String CATEGORYID)
    {
        this.CATEGORYID = CATEGORYID;
    }

    public String getNOTICE()
    {
        return NOTICE;
    }

    public void setNOTICE(String NOTICE)
    {
        this.NOTICE = NOTICE;
    }

    public String getCUSTOMID()
    {
        return CUSTOMID;
    }

    public void setCUSTOMID(String CUSTOMID)
    {
        this.CUSTOMID = CUSTOMID;
    }

    public String getCARD_ID()
    {
        return CARD_ID;
    }

    public void setCARD_ID(String CARD_ID)
    {
        this.CARD_ID = CARD_ID;
    }

    public Integer getISCURRENCY()
    {
        return ISCURRENCY == null ? 0 : 1;
    }

    public void setISCURRENCY(Integer ISCURRENCY)
    {
        this.ISCURRENCY = ISCURRENCY;
    }

    public String getSTARTCOSTDATE()
    {
        return STARTCOSTDATE;
    }

    public void setSTARTCOSTDATE(String STARTCOSTDATE)
    {
        this.STARTCOSTDATE = STARTCOSTDATE;
    }

    public String getENDCOSTDATE()
    {
        return ENDCOSTDATE;
    }

    public void setENDCOSTDATE(String ENDCOSTDATE)
    {
        this.ENDCOSTDATE = ENDCOSTDATE;
    }

    public List<PictureBean> getListPicture()
    {
        return listPicture;
    }

    public void setListPicture(List<PictureBean> listPicture)
    {
        this.listPicture = listPicture;
    }

    public String getUSERSNUM()
    {
        return USERSNUM;
    }

    public void setUSERSNUM(String USERSNUM)
    {
        this.USERSNUM = USERSNUM;
    }

    public String getREMARK()
    {
        return REMARK;
    }

    public void setREMARK(String REMARK)
    {
        this.REMARK = REMARK;
    }
}
