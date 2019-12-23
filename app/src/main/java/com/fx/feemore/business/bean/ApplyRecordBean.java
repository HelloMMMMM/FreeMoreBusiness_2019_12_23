package com.fx.feemore.business.bean;

import com.baoyachi.stepview.bean.StepBean;

import java.util.List;

/**
 * function:申请记录实体信息
 * author: frj
 * modify date: 2018/12/24
 */
public class ApplyRecordBean
{

    private List<StepBean> stepBeans;
    private String storyName;   //店铺名称
    private String img;         //店铺图片
    private int status;         //  状态，0申请中，1通过，-1不通过
    private String remark;    //驳回原因

    public ApplyRecordBean(List<StepBean> stepBeans, String storyName, String img, int status, String remark)
    {
        this.stepBeans = stepBeans;
        this.storyName = storyName;
        this.img = img;
        this.status = status;
        this.remark = remark;
    }

    public List<StepBean> getStepBeans()
    {
        return stepBeans;
    }

    public void setStepBeans(List<StepBean> stepBeans)
    {
        this.stepBeans = stepBeans;
    }

    public String getStoryName()
    {
        return storyName;
    }

    public void setStoryName(String storyName)
    {
        this.storyName = storyName;
    }

    public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}
