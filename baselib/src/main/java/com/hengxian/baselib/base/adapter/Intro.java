package com.hengxian.baselib.base.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * function:Adapter使用说明
 * author: frj
 * modify date: 2018/6/11
 */
public class Intro {

    /**
     * 单类型适配器
     * 注意点：
     * layoutItem对应布局需要设置名为 item的variable
     *
     * @param recyclerView
     * @param layoutItem
     * @param <T>          数据实体类型
     */
    private <T> void singleAdapter(RecyclerView recyclerView, @LayoutRes int layoutItem) {
        BaseHxAdapter<T, ItemViewHolder> adapter = new BaseHxAdapter<>(layoutItem);
        //将适配器与RecyclerView绑定
        adapter.bindToRecyclerView(recyclerView);
        //设置加载更多监听，该步骤同时也做了bindToRecyclerView的操作，如果设置加载更多监听，那么可以不用调用adapter.bindToRecyclerView(recyclerView);
        adapter.setOnLoadMoreListener(() -> {
        }, recyclerView);

        //该方法需要在bindToRecyclerView()之后调用，或者在adapter.setOnLoadMoreListener之后调用，确保RecyclerView对象已与Adapter绑定
        //判断数据是否满屏，如果没有满屏，那么将会隐藏加载更多，否则显示
        adapter.disableLoadMoreIfNotFullPage();
        //表示是否启用加载更多；可用adapter.disableLoadMoreIfNotFullPage()代替，但是disableLoadMoreIfNotFullPage方法在数据未满屏时，默认不启用加载更多
        adapter.setEnableLoadMore(true);
        //表示一页数据加载完成
        adapter.loadMoreComplete();
        //表示全部数据加载结束，参数 true or false;true时，表示不显示“没有更多数据了”，false表示显示
        adapter.loadMoreEnd(true);
        //表示加载更多时数据加载失败
        adapter.loadMoreFail();
        //设置新数据集，一般在请求完数据或者刷新数据后调用
        adapter.setNewData(null);
        //表示在当前数据集末尾，新增一个数据集，一般在加载更多请求完成后调用
        adapter.addData((ArrayList<T>) null);
        //获取数据实体对象
        adapter.getItem(0);
        //获取适配器的数据集
        List<T> list = adapter.getData();
        //移除某行数据
        adapter.remove(0);
        //替换数据集内容；当前适配器的数据集对象引用不变，只变数据；如果想改变适配器的数据集的引用，可以调用setNewData();
        adapter.replaceData((ArrayList<T>) null);
    }

    /**
     * 多类型数据适配器；
     * 注意点：
     * 1.数据实体需实现IMultiItemType接口，并与layoutItems对应
     * 2.layoutItems对应布局需要设置名为 item的variable
     *
     * @param recyclerView
     * @param layoutItems
     */
    private void multipleAdapter(RecyclerView recyclerView, @LayoutRes int... layoutItems) {

        BaseMultiItemHxAdapter<IMultiItemType, ItemViewHolder> adapter = new BaseMultiItemHxAdapter<IMultiItemType, ItemViewHolder>() {
            @Override
            protected void bindTypeAndLayout() {
                for (int i = 0; i < layoutItems.length; i++) {
                    addItemType(i, layoutItems[0]);
                }
            }
        };
        //其余步骤，与singleAdapter相同
    }
}
