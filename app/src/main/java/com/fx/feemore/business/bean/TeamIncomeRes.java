package com.fx.feemore.business.bean;

import java.util.List;

/**
 * function:团队收益返回结果
 * author: frj
 * modify date: 2019/3/4
 */
public class TeamIncomeRes
{
    private Double TOTAL;   //总推广奖励金额
    private Integer M2NUMS; //二级交易笔数
    private Integer M1NUMS;//一级交易笔数
    private List<IncomeBean> listPerformance;   //收益列表

    public Double getTOTAL()
    {
        return TOTAL == null ? 0 : TOTAL;
    }

    public void setTOTAL(Double TOTAL)
    {
        this.TOTAL = TOTAL;
    }

    public Integer getM2NUMS()
    {
        return M2NUMS == null ? 0 : M2NUMS;
    }

    public void setM2NUMS(Integer m2NUMS)
    {
        M2NUMS = m2NUMS;
    }

    public Integer getM1NUMS()
    {
        return M1NUMS == null ? 0 : M1NUMS;
    }

    public void setM1NUMS(Integer m1NUMS)
    {
        M1NUMS = m1NUMS;
    }

    public List<IncomeBean> getListPerformance()
    {
        return listPerformance;
    }

    public void setListPerformance(List<IncomeBean> listPerformance)
    {
        this.listPerformance = listPerformance;
    }
}
