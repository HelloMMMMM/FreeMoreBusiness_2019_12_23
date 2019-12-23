package com.hengxian.baselib.base.adapter;

import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;

import java.util.List;

/**
 * function:RecyclerView多类型适配器
 * author: frj
 * modify date: 2018/6/7
 */
public abstract class BaseMultiItemHxAdapter<T extends IMultiItemType, K extends ItemViewHolder> extends BaseHxAdapter<T, K> {

    /**
     * 布局引用集合
     */
    private SparseIntArray layouts;
    /**
     * 默认的布局类型
     */
    private static final int DEFAULT_VIEW_TYPE = -0xff;
    /**
     * 类型未找到
     */
    public static final int TYPE_NOT_FOUND = -404;

    public BaseMultiItemHxAdapter() {
        this(null);
    }

    public BaseMultiItemHxAdapter(List<T> data) {
        super(0, data);
        bindTypeAndLayout();
    }

    @Override
    protected int getDefItemViewType(int position) {
        T item = mData.get(position);
        if (item != null) {
            return item.getItemType();
        }
        return DEFAULT_VIEW_TYPE;
    }

    /**
     * 绑定类型和布局
     */
    protected abstract void bindTypeAndLayout();

    /**
     * 根据类型获取布局id
     *
     * @param viewType
     * @return
     */
    private int getLayoutId(int viewType) {
        return layouts.get(viewType, TYPE_NOT_FOUND);
    }

    /**
     * 添加项类型及对应的布局文件id
     *
     * @param type        项类型
     * @param layoutResId 布局文件id
     */
    protected void addItemType(int type, @LayoutRes int layoutResId) {
        if (layouts == null) {
            layouts = new SparseIntArray();
        }
        layouts.put(type, layoutResId);
    }

    /**
     * 根据类型返回布局id
     *
     * @param viewType
     * @return
     */
    @Override
    protected int getItemLayoutId(int viewType) {
        return getLayoutId(viewType);
    }
}
