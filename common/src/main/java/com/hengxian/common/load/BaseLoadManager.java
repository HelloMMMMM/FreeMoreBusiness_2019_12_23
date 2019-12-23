package com.hengxian.common.load;

import android.support.annotation.IdRes;
import android.util.SparseArray;
import android.view.View;

/**
 * function:抽象的加载图层管理器
 * author: frj
 * modify date: 2016/12/29
 */

public abstract class BaseLoadManager {

    /**
     * loadManager加载完成或没进行任何显示时
     */
    public static final int STATE_NORMAL = 0;
    /**
     * loadManager加载时的状态
     */
    public static final int STATE_LOADDING = 1;
    /**
     * loadManager加载失败时的状态
     */
    public static final int STATE_FAIL = 2;

    /**
     * loadManager 加载结果为空时的状态
     */
    public static final int STATE_EMPTY = 3;

    /**
     * 当前加载图层的状态
     */
    private int state = STATE_NORMAL;

    /**
     * 根布局
     */
    private final View contentView;
    /**
     * 控件集合
     */
    private final SparseArray<View> views;

    /**
     * 如果是Fragment中，建议使用getView()返回值作为参数；
     * 如果是Activity中，建议使用rootView作为参数
     *
     * @param contentView
     */
    public BaseLoadManager(View contentView) {
        this.contentView = contentView;
        views = new SparseArray<>();
    }

    /**
     * 正在加载中
     */
    public void loadding() {
        state = STATE_LOADDING;
        if (!getViewVisible(getLoadRootView())) {
            setViewVisible(getLoadRootView(), true);
        }
        if (!getViewVisible(getLoaddingView())) {
            setViewVisible(getLoaddingView(), true);
        }
        setViewVisible(getFailView(), false);
        setViewVisible(getEmptyView(), false);
    }

    /**
     * 加载失败
     *
     * @param text     加载失败提示文本
     * @param listener 重试监听
     */
    public void loadFail(String text, OnReloadListener listener) {
        state = STATE_FAIL;
        if (!getViewVisible(getLoadRootView())) {
            setViewVisible(getLoadRootView(), true);
        }
        if (!getViewVisible(getFailView())) {
            setViewVisible(getFailView(), true);
        }
        setViewVisible(getEmptyView(), false);
        setViewVisible(getLoaddingView(), false);
    }

    /**
     * 加载失败
     *
     * @param listener 重试监听
     */
    public void loadFail(OnReloadListener listener) {
        loadFail("", listener);
    }

    /**
     * 空数据
     *
     * @param text 空数据提示
     */
    public void loadEmpty(String text) {
        state = STATE_EMPTY;
        if (!getViewVisible(getLoadRootView())) {
            setViewVisible(getLoadRootView(), true);
        }
        if (!getViewVisible(getEmptyView())) {
            setViewVisible(getEmptyView(), true);
        }
        setViewVisible(getFailView(), false);
        setViewVisible(getLoaddingView(), false);
    }

    /**
     * 加载成功
     */
    public void loadSuccess() {
        state = STATE_NORMAL;
        //设置控件隐藏
        setViewVisible(getLoadRootView(), false);
    }

    /**
     * 获取当前加载图层状态
     *
     * @return 值为{#STATE_NORMAL #STATE_EMPTY #STATE_FAIL #STATE_LOADDING}
     */
    public int getLoadState() {
        return state;
    }

    /**
     * 判断是否是正在加载中
     *
     * @return true表示正在加载中
     */
    public boolean isLoadding() {
        return STATE_LOADDING == state;
    }

    /**
     * 判断是否是加载失败
     *
     * @return true表示加载失败
     */
    public boolean isLoadFail() {
        return STATE_FAIL == state;
    }

    /**
     * 判断是否加载为空的
     *
     * @return true 表示加载为空
     */
    public boolean isLoadEmpty() {
        return STATE_EMPTY == state;
    }

    /**
     * 判断是否是加载成功的
     *
     * @return true 表示加载成功
     */
    public boolean isLoadSuccess() {
        return STATE_NORMAL == state;
    }

    /**
     * 获取空布局View
     *
     * @return
     */
    protected View getEmptyView() {
        return getView(getEmptyViewId());
    }

    /**
     * 获取加载失败布局View
     *
     * @return
     */
    protected View getFailView() {
        return getView(getFailViewId());
    }

    /**
     * 获取加载中布局View
     *
     * @return
     */
    protected View getLoaddingView() {
        return getView(getLoaddingViewId());
    }

    /**
     * 获取加载图层根布局View
     *
     * @return
     */
    protected View getLoadRootView() {
        return getView(getLoadRootViewId());
    }

    /**
     * 获取加载图层根布局Id
     *
     * @return
     */
    protected abstract @IdRes
    int getLoadRootViewId();

    /**
     * 获取空布局控件id
     *
     * @return
     */
    protected abstract @IdRes
    int getEmptyViewId();

    /**
     * 获取加载失败布局控件id
     *
     * @return
     */
    protected abstract @IdRes
    int getFailViewId();

    /**
     * 获取加载中布局控件id
     *
     * @return
     */
    protected abstract @IdRes
    int getLoaddingViewId();


    /**
     * 设置控件显示状态
     *
     * @param view      需要设置的控件
     * @param isVisible true表示显示；false表示隐藏
     */
    private void setViewVisible(View view, boolean isVisible) {
        if (view != null) {
            view.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 获取控件显示状态
     *
     * @param view
     * @return true 表示显示，false表示隐藏
     */
    private boolean getViewVisible(View view) {
        if (view != null) {
            return view.isShown();
        }
        return false;
    }


    /**
     * 通过Id 获取控件对象
     *
     * @param viewId
     * @param <T>
     * @return
     */
    protected <T extends View> T getView(@IdRes int viewId) {
        if (contentView == null || views == null) {
            return null;
        }
        View view = views.get(viewId);
        if (view == null) {
            view = contentView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}
