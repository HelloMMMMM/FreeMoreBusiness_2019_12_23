package com.fx.feemore.business.bean;

import java.util.List;

/**
 * function:消费记录返回结果
 * author: frj
 * modify date: 2019/2/27
 */
public class ConsumeRecordRes
{
    private Integer TOTAL;  //总数
    private List<ConsumeRecordBean> listUseCards;

    public Integer getTOTAL()
    {
        return TOTAL == null ? 0 : TOTAL;
    }

    public void setTOTAL(Integer TOTAL)
    {
        this.TOTAL = TOTAL;
    }

    public List<ConsumeRecordBean> getListUseCards()
    {
        return listUseCards;
    }

    public void setListUseCards(List<ConsumeRecordBean> listUseCards)
    {
        this.listUseCards = listUseCards;
    }
}
