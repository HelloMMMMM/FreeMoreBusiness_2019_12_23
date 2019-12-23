package com.fx.feemore.business.bean;

import java.util.List;

/**
 * function:店铺卡包列表实体信息
 * author: frj
 * modify date: 2019/2/13
 */
public class InterestListBean
{

    private Integer TOTAL;  //发布中的数量
    private Integer SHELVES;    //已下架的数量

    private List<InterestBean> listStoreGoodsCard;

    public Integer getTOTAL()
    {
        return TOTAL == null ? 0 : TOTAL;
    }

    public void setTOTAL(Integer TOTAL)
    {
        this.TOTAL = TOTAL;
    }

    public Integer getSHELVES()
    {
        return SHELVES == null ? 0 : SHELVES;
    }

    public void setSHELVES(Integer SHELVES)
    {
        this.SHELVES = SHELVES;
    }

    public List<InterestBean> getListStoreGoodsCard()
    {
        return listStoreGoodsCard;
    }

    public void setListStoreGoodsCard(List<InterestBean> listStoreGoodsCard)
    {
        this.listStoreGoodsCard = listStoreGoodsCard;
    }
}
