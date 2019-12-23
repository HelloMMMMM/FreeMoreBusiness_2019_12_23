package com.hengxian.baselib.base.adapter;


import com.hengxian.baselib.R;

/**
 * function:通用的LoadMoreView，可通过实现LoadMoreView自定义
 * author: frj
 * modify date: 2018/6/7
 */
public class SimpleLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.hx_recycler_loadmore;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
