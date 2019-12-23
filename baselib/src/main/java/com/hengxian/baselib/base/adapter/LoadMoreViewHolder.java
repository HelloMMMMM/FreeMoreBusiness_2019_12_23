package com.hengxian.baselib.base.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * function:加载更多Item的ViewHolder
 * author: frj
 * modify date: 2018/6/6
 */
public class LoadMoreViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> views;

    public LoadMoreViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    /**
     * 设置控件显示或隐藏
     *
     * @param viewId    控件id
     * @param isVisible 显示或隐藏,true表示显示；false表示隐藏
     */
    public void setVisible(@IdRes int viewId, boolean isVisible) {
        View view = getView(viewId);
        if (view != null) {
            view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 通过Id 获取控件对象
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(@IdRes int viewId) {
        if (itemView == null || views == null) {
            return null;
        }
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}
