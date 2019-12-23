package com.fx.feemore.business.ui.order.bean;

import com.fx.feemore.business.bean.PictureBean;

import java.util.List;

/**
 * function:评论实体信息
 * author: frj
 * modify date: 2019/2/14
 */
public class CommentBean {
    private String NICKNAME;    //
    private String MEMBERIMG;   //会员头像
    private String NAME;        //商品名称
    private String PRICE;       //商品价格
    private String ORDERIMG;    //订单商品路径
    private String ORDERTIME;   //订单时间会员昵称
    private String MEMBERID;    //会员id
    private String MESSAGE;     //评论内容
    private String STOREID;     //店铺id
    private Integer TYPE;       //评论级别，几颗星
    private Integer STATUS;     //评论状态，1已评论，2店家已回复
    private String REPLYTIME;   //店铺回复时间
    private String REPLY;       //店铺回复内容
    private String ITEMID;      //
    private String ORDERID;     //订单id
    private String CREATETIME;  //评论时间
    private String COMMENT_ID;  //评论id
    private List<PictureBean> listPicture;  //图片列表

    public String getNICKNAME() {
        return NICKNAME;
    }

    public void setNICKNAME(String NICKNAME) {
        this.NICKNAME = NICKNAME;
    }

    public String getMEMBERIMG() {
        return MEMBERIMG;
    }

    public void setMEMBERIMG(String MEMBERIMG) {
        this.MEMBERIMG = MEMBERIMG;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getORDERIMG() {
        return ORDERIMG;
    }

    public void setORDERIMG(String ORDERIMG) {
        this.ORDERIMG = ORDERIMG;
    }

    public String getORDERTIME() {
        return ORDERTIME;
    }

    public void setORDERTIME(String ORDERTIME) {
        this.ORDERTIME = ORDERTIME;
    }

    public String getMEMBERID() {
        return MEMBERID;
    }

    public void setMEMBERID(String MEMBERID) {
        this.MEMBERID = MEMBERID;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getSTOREID() {
        return STOREID;
    }

    public void setSTOREID(String STOREID) {
        this.STOREID = STOREID;
    }

    public Integer getTYPE() {
        return TYPE == null ? 0 : TYPE;
    }

    public void setTYPE(Integer TYPE) {
        this.TYPE = TYPE;
    }

    public Integer getSTATUS() {
        return STATUS == null ? 0 : STATUS;
    }

    public void setSTATUS(Integer STATUS) {
        this.STATUS = STATUS;
    }

    public String getREPLYTIME() {
        return REPLYTIME;
    }

    public void setREPLYTIME(String REPLYTIME) {
        this.REPLYTIME = REPLYTIME;
    }

    public String getREPLY() {
        return REPLY;
    }

    public void setREPLY(String REPLY) {
        this.REPLY = REPLY;
    }

    public String getITEMID() {
        return ITEMID;
    }

    public void setITEMID(String ITEMID) {
        this.ITEMID = ITEMID;
    }

    public String getCREATETIME() {
        return CREATETIME;
    }

    public void setCREATETIME(String CREATETIME) {
        this.CREATETIME = CREATETIME;
    }

    public String getCOMMENT_ID() {
        return COMMENT_ID;
    }

    public void setCOMMENT_ID(String COMMENT_ID) {
        this.COMMENT_ID = COMMENT_ID;
    }

    public List<PictureBean> getListPicture() {
        return listPicture;
    }

    public void setListPicture(List<PictureBean> listPicture) {
        this.listPicture = listPicture;
    }


    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getORDERID() {
        return ORDERID;
    }

    public void setORDERID(String ORDERID) {
        this.ORDERID = ORDERID;
    }
}
