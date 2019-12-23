package com.fx.feemore.business.util;

import android.support.v4.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.common.ToastUtil;

import java.util.List;

/**
 * function:数据加载工具类
 * author: frj
 * modify date: 2019/1/22
 */
public class DataLoadUtil
{

    /**
     * 加载数据
     *
     * @param swipeRefreshLayout SwipeRefreshLayout对象
     * @param adapter            适配器对象
     * @param list               数据源
     * @param page               当前页码
     * @param <T>                数据源数据类型
     * @param <K>                适配器ViewHolder
     */
    public static <T, K extends BaseViewHolder> void loadData(SwipeRefreshLayout swipeRefreshLayout, BaseQuickAdapter<T, K> adapter, List<T> list, int page)
    {
        if (adapter != null && list != null)
        {
            if (ConstantValue.PAGE_START >= page)
            {
                adapter.setNewData(list);
            } else
            {
                adapter.addData(list);
            }
            if (list.size() >= ConstantValue.PAGE_SIZE)
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

    /**
     * 处理失败
     *
     * @param swipeRefreshLayout SwipeRefreshLayout对象
     * @param httpFailBean       网络请求失败
     */
    public static void loadFail(SwipeRefreshLayout swipeRefreshLayout, HttpFailBean httpFailBean)
    {
        if (httpFailBean != null)
        {
            ToastUtil.show(httpFailBean.getText());
        }
        if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing())
        {
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
