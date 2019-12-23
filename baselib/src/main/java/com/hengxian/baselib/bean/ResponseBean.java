package com.hengxian.baselib.bean;

/**
 * 网络请求状态
 *
 * @author wfq
 * @date 2018/6/7
 */
public class ResponseBean
{
    /**
     * 表示成功请求
     */
    public static final int SUCCESS = 1;

    private String msg;             //错误提示
    private int code;

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    /**
     * 判断是否是一个成功的返回
     *
     * @return
     */
    public boolean isSuccess()
    {
        return SUCCESS == code;
    }

    /**
     * 判断是否是一个成功的返回
     *
     * @param responseBean
     * @return
     */
    public static boolean isSuccess(ResponseBean responseBean)
    {
        if (responseBean == null)
        {
            return false;
        }
        return responseBean.isSuccess();
    }
}
