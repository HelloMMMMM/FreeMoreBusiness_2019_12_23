package com.fx.feemore.business.util;

import android.support.v4.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hengxian.baselib.base.BaseActivity;

import java.util.List;

/**
 * function:加载管理工具类
 * author: frj
 * modify date: 2018/11/24
 */
public class LoadManageUtil
{
    /**
     * 处理数据加载
     *
     * @param swipeRefreshLayout 刷新控件
     * @param t                  返回数据实体
     * @param page               页码
     * @param <T>                数据实体类型
     */
    public static <T, K extends BaseViewHolder> void handleLoadData(SwipeRefreshLayout swipeRefreshLayout, BaseQuickAdapter<T, K> adapter, int page, List<T> t)
    {

        //判断是否正在加载中
        if (adapter != null)
        {
            if (BaseActivity.PAGE_START == page)
            {
                adapter.setNewData(t);
            } else
            {
                adapter.addData(t);
            }
            if (VerificationUtil.getSize(t) >= BaseActivity.PAGE_NUM)
            {
                adapter.loadMoreComplete();
            } else
            {
                adapter.loadMoreEnd();
            }
        }
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing())
        {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
