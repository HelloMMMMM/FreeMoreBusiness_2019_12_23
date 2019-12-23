package com.hengxian.baselib.base.adapter;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

/**
 * function:RecyclerView加载更多Item抽象管理类
 * author: frj
 * modify date: 2018/6/6
 */
public abstract class LoadMoreView {

    /**
     * 加载状态，默认
     */
    public static final int STATUS_DEFAULT = 1;
    /**
     * 加载状态，加载中
     */
    public static final int STATUS_LOADING = 2;
    /**
     * 加载状态，加载失败
     */
    public static final int STATUS_FAIL = 3;
    /**
     * 加载状态，加载完成
     */
    public static final int STATUS_END = 4;

    //默认加载状态
    private int mLoadMoreStatus = STATUS_DEFAULT;
    //是否加载结束并隐藏
    private boolean mLoadMoreEndGone = false;

    public void setLoadMoreStatus(int loadMoreStatus) {
        this.mLoadMoreStatus = loadMoreStatus;
    }

    public int getLoadMoreStatus() {
        return mLoadMoreStatus;
    }

    /**
     * 切换显示状态
     *
     * @param holder
     */
    public void convert(LoadMoreViewHolder holder) {
        switch (mLoadMoreStatus) {
            case STATUS_LOADING:
                visibleLoading(holder, true);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;
            case STATUS_FAIL:
                visibleLoading(holder, false);
                visibleLoadFail(holder, true);
                visibleLoadEnd(holder, false);
                break;
            case STATUS_END:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, true);
                break;
            case STATUS_DEFAULT:
                visibleLoading(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;
        }
    }

    /**
     * 设置加载中显示状态
     *
     * @param holder
     * @param visible 显示或隐藏
     */
    private void visibleLoading(LoadMoreViewHolder holder, boolean visible) {
        holder.setVisible(getLoadingViewId(), visible);
    }

    /**
     * 设置加载失败显示状态
     *
     * @param holder
     * @param visible 显示或隐藏
     */
    private void visibleLoadFail(LoadMoreViewHolder holder, boolean visible) {
        holder.setVisible(getLoadFailViewId(), visible);
    }

    /**
     * 设置加载完成显示状态
     *
     * @param holder
     * @param visible 显示或隐藏
     */
    private void visibleLoadEnd(LoadMoreViewHolder holder, boolean visible) {
        final int loadEndViewId = getLoadEndViewId();
        if (loadEndViewId != 0) {
            holder.setVisible(loadEndViewId, visible);
        }
    }

    /**
     * 设置是否没有更多数据就隐藏
     *
     * @param loadMoreEndGone
     */
    public final void setLoadMoreEndGone(boolean loadMoreEndGone) {
        this.mLoadMoreEndGone = loadMoreEndGone;
    }

    /**
     * 表示是否没有更多数据就隐藏
     *
     * @return
     */
    public final boolean isLoadEndMoreGone() {
        if (getLoadEndViewId() == 0) {
            return true;
        }
        return mLoadMoreEndGone;
    }

    /**
     * 获取加载布局id
     *
     * @return
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * 获取加载中图层id
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadingViewId();

    /**
     * 获取加载失败图层id
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadFailViewId();

    /**
     * 获取加载完成图层id
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadEndViewId();
}
