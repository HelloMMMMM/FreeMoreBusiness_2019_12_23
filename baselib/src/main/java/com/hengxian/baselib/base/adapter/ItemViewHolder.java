package com.hengxian.baselib.base.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.hengxian.baselib.BR;



public class ItemViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public final T binding;


    /**
     * 创建ItemViewHolder
     *
     * @param parent
     * @param layoutRes
     * @return
     */
    public static ItemViewHolder create(@NonNull ViewGroup parent, @LayoutRes int layoutRes) {
        return new ItemViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutRes, parent, false));
    }

    public ItemViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * 绑定数据对象
     *
     * @param item
     * @param <K>
     */
    public <K> void bindItem(K item) {
        binding.setVariable(BR.item, item);
        binding.executePendingBindings();
    }
}
