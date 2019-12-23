package com.hengxian.baselib.base.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * function:RecyclerView适配器
 * author: frj
 * modify date: 2018/6/7
 */
public class BaseHxAdapter<K, V extends ItemViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 加载更多类型
     */
    private static final int TYPE_LOAD_MORE = 0x111;

    /**
     * 普通项类型
     */
    private static final int TYPE_ITEM = 0;

    /**
     * 加载图层
     */
    private LoadMoreView mLoadMoreView;

    //表示加载状态是否可用
    private boolean mNextLoadEnable = false;
    /**
     * 表示是否启用加载更多
     */
    private boolean mLoadMoreEnable = false;

    /**
     * 表示加载更多是否是正在加载中
     */
    private boolean mLoading = false;

    private int mLastPosition = -1;


    private RecyclerView mRecyclerView;

    /**
     * 加载更多监听
     */
    private OnLoadMoreListener onLoadMoreListener;

    /**
     * 数据条目布局id
     */
    private @LayoutRes
    int mItemLayoutId;

    /**
     * 数据集合
     */
    protected List<K> mData;

    protected Context mContext;

    protected LayoutInflater mLayoutInflater;


    public BaseHxAdapter(@LayoutRes int layoutResId) {
        mData = new ArrayList<>();
        this.mItemLayoutId = layoutResId;
        this.mLoadMoreView = new SimpleLoadMoreView();
    }

    public BaseHxAdapter(@LayoutRes int layoutResId, List<K> data) {
        this(layoutResId);
        setNewData(data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        this.mContext = parent.getContext();
        this.mLayoutInflater = LayoutInflater.from(mContext);
        //加载更多
        if (viewType == TYPE_LOAD_MORE) {
            viewHolder = new LoadMoreViewHolder(getItemView(getLoadMoreView().getLayoutId(), parent));
        } else {
            viewHolder = V.create(parent, getItemLayoutId(viewType));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int itemType = getItemViewType(position);
        //由于LoadMore是一个单独的ViewHolder类型，所以此处单独加判断
        if (itemType == TYPE_LOAD_MORE) {
            if (mLoadMoreView != null) {
                autoLoadMore(position);
                mLoadMoreView.convert((LoadMoreViewHolder) holder);
            }
        } else {
            bindDataItem((V) holder, position);
        }
        //加一行，问题解决闪烁
        if( holder.getClass()==ItemViewHolder.class){
            ((V) holder).binding.executePendingBindings();
        }

    }

    @Override
    public int getItemCount() {
        return mData.size() + getLoadMoreViewCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mLoadMoreEnable) {
            if (position == mData.size()) {
                return TYPE_LOAD_MORE;
            }
        }
        return getDefItemViewType(position);
    }

    /**
     * View附加到Window回调
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        int type = holder.getItemViewType();
        if (TYPE_LOAD_MORE == type) {
            setFullSpan(holder);
        }
    }

    /**
     * 获取Item布局
     *
     * @return
     */
    protected int getItemLayoutId(int viewType) {
        return mItemLayoutId;
    }

    /**
     * 加载不同的项类型
     *
     * @param position
     * @return
     */
    protected int getDefItemViewType(int position) {

        return TYPE_ITEM;
    }

    /**
     * 绑定数据对象
     *
     * @param holder
     * @param position
     */
    protected void bindDataItem(V holder, int position) {
        if (holder != null) {
            holder.bindItem(getItem(position));
        }
    }

    /**
     * 设置新数据
     *
     * @param data
     */
    public void setNewData(@Nullable List<K> data) {
        this.mData = data == null ? new ArrayList<K>() : data;
        if (onLoadMoreListener != null) {
            mNextLoadEnable = true;
            mLoadMoreEnable = true;
            mLoading = false;
            mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_DEFAULT);
        }
        mLastPosition = -1;
        notifyDataSetChanged();
    }

    /**
     * 在指定位置插入数据
     *
     * @param position
     * @param item
     * @deprecated 用 {@link #addData(int, Object)} 插入
     */
    @Deprecated
    public void add(@IntRange(from = 0) int position, @NonNull K item) {
        addData(position, item);
    }

    /**
     * 插入新数据至指定位置
     *
     * @param position
     */
    public void addData(@IntRange(from = 0) int position, @NonNull K data) {
        mData.add(position, data);
        notifyItemInserted(position);
        compatibilityDataSizeChanged(1);
    }

    /**
     * add one new data
     */
    public void addData(@NonNull K data) {
        mData.add(data);
        notifyItemInserted(mData.size());
        compatibilityDataSizeChanged(1);
    }

    /**
     * 移除指定位置项
     *
     * @param position
     */
    public void remove(@IntRange(from = 0) int position) {
        if (mData == null
                || position < 0
                || position >= mData.size()) {
            return;
        }

        mData.remove(position);
        int internalPosition = position;
        notifyItemRemoved(internalPosition);
        compatibilityDataSizeChanged(0);
        notifyItemRangeChanged(internalPosition, mData.size() - internalPosition);
    }

    /**
     * 改变数据
     */
    public void setData(@IntRange(from = 0) int index, @NonNull K data) {
        mData.set(index, data);
        notifyItemChanged(index);
    }

    /**
     * 添加新数据至指定位置
     *
     * @param position 插入位置
     * @param newData  新数据集
     */
    public void addData(@IntRange(from = 0) int position, @NonNull Collection<? extends K> newData) {
        mData.addAll(position, newData);
        notifyItemRangeInserted(position + 0, newData.size());
        compatibilityDataSizeChanged(newData.size());
    }

    /**
     * 将新数据添加到末尾
     *
     * @param newData 新数据集合
     */
    public void addData(@NonNull Collection<? extends K> newData) {
        mData.addAll(newData);
        notifyItemRangeInserted(mData.size() - newData.size(), newData.size());
        compatibilityDataSizeChanged(newData.size());
    }

    /**
     * use data to replace all item in mData. this method is different {@link #setNewData(List)},
     * it doesn't change the mData reference
     *
     * @param data data collection
     */
    public void replaceData(@NonNull Collection<? extends K> data) {
        // 不是同一个引用才清空列表
        if (data != mData) {
            mData.clear();
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    /**
     * 兼容的数据改变
     *
     * @param size 需要兼容的数据大小
     */
    private void compatibilityDataSizeChanged(int size) {
        final int dataSize = mData == null ? 0 : mData.size();
        if (dataSize == size) {
            notifyDataSetChanged();
        }
    }

    /**
     * 获取数据项集合
     *
     * @return 列表数据
     */
    @NonNull
    public List<K> getData() {
        return mData;
    }

    /**
     * 获取指定位置的数据项
     *
     * @param position
     * @return
     */
    @Nullable
    public K getItem(@IntRange(from = 0) int position) {
        if (position >= 0 && position < mData.size()) {
            return mData.get(position);
        } else {
            return null;
        }
    }

    /**
     * 刷新项数据
     *
     * @param position
     */
    public final void refreshNotifyItemChanged(int position) {
        notifyItemChanged(position);
    }

    /**
     * 检查RecyclerView对象是否为空
     */
    private void checkNotNull() {
        if (mRecyclerView == null) {
            throw new RuntimeException("please bind recyclerView first!");
        }
    }

    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    private void setRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        this.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {

                if (getItemCount() == 0) {

                }


                super.onChanged();
            }
        });
    }

    /**
     * 绑定RecyclerView对象，如果已绑定，抛出异常
     */
    public void bindToRecyclerView(RecyclerView recyclerView) {
        if (getRecyclerView() != null) {
            throw new RuntimeException("Don't bind twice");
        }
        setRecyclerView(recyclerView);
        if (getRecyclerView().getAdapter() == null) {
            getRecyclerView().setAdapter(this);
        }
    }

    /**
     * 获取加载更多管理类
     *
     * @return
     */
    protected LoadMoreView getLoadMoreView() {
        if (mLoadMoreView == null) {
            mLoadMoreView = new SimpleLoadMoreView();
        }
        return mLoadMoreView;
    }

    /**
     * 绑定自定义的加载更多
     *
     * @param loadMoreView
     */
    public void setLoadMoreView(LoadMoreView loadMoreView) {
        if (loadMoreView == null) {
            return;
        }
        this.mLoadMoreView = loadMoreView;
    }

    /**
     * 打开加载更多
     *
     * @param onLoadMoreListener
     */
    private void openLoadMore(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
        mNextLoadEnable = true;
        mLoadMoreEnable = true;
        mLoading = false;
    }

    /**
     * 设置加载更多监听
     *
     * @param onLoadMoreListener
     * @param recyclerView
     */
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener, RecyclerView recyclerView) {
        openLoadMore(onLoadMoreListener);
        bindToRecyclerView(recyclerView);
    }

    /**
     * 返回加载更多的启用状态
     *
     * @return true表示启用，false表示未启用
     */
    public boolean isLoadMoreEnable() {
        return mLoadMoreEnable;
    }

    /**
     * 加载更多的数量
     *
     * @return 0 or 1
     */
    public int getLoadMoreViewCount() {
        if (onLoadMoreListener == null || !mLoadMoreEnable) {
            return 0;
        }
        if (!mNextLoadEnable && mLoadMoreView.isLoadEndMoreGone()) {
            return 0;
        }
        if (mData.size() == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * Gets to load more locations
     *
     * @return
     */
    public int getLoadMoreViewPosition() {
        return mData.size();
    }

    /**
     * 设置加载更多的启用状态
     *
     * @param enable true表示启用加载更多；false表示不启用
     */
    public void setEnableLoadMore(boolean enable) {
        int oldLoadMoreCount = getLoadMoreViewCount();
        mLoadMoreEnable = enable;
        int newLoadMoreCount = getLoadMoreViewCount();

        if (oldLoadMoreCount == 1) {
            if (newLoadMoreCount == 0) {
                notifyItemRemoved(getLoadMoreViewPosition());
            }
        } else {
            if (newLoadMoreCount == 1) {
                mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_DEFAULT);
                notifyItemInserted(getLoadMoreViewPosition());
            }
        }
    }

    /**
     * @return 适配器是否正在显示加载更多进度
     */
    public boolean isLoading() {
        return mLoading;
    }


    /**
     * 加载结束，没有更多数据
     */
    public void loadMoreEnd() {
        loadMoreEnd(false);
    }

    /**
     * 加载结束没有更多数据
     *
     * @param gone true表示隐藏加载更多图层
     */
    public void loadMoreEnd(boolean gone) {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        mLoading = false;
        mNextLoadEnable = false;
        mLoadMoreView.setLoadMoreEndGone(gone);
        if (gone) {
            notifyItemRemoved(getLoadMoreViewPosition());
        } else {
            mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_END);
            notifyItemChanged(getLoadMoreViewPosition());
        }
    }

    /**
     * 加载完成
     */
    public void loadMoreComplete() {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        mLoading = false;
        mNextLoadEnable = true;
        mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_DEFAULT);
        notifyItemChanged(getLoadMoreViewPosition());
    }

    /**
     * 加载失败
     */
    public void loadMoreFail() {
        if (getLoadMoreViewCount() == 0) {
            return;
        }
        mLoading = false;
        mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_FAIL);
        notifyItemChanged(getLoadMoreViewPosition());
    }

    /**
     * 通知开始回调并加载更多
     */
    public void notifyLoadMoreToLoading() {
        if (mLoadMoreView.getLoadMoreStatus() == LoadMoreView.STATUS_LOADING) {
            return;
        }
        mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_DEFAULT);
        notifyItemChanged(getItemCount() - 1);
    }

    /**
     * 如果数据没有全屏，那么禁用加载更多；
     * 使用前线绑定RecyclerView
     *
     * @see #disableLoadMoreIfNotFullPage(RecyclerView)
     */
    public void disableLoadMoreIfNotFullPage() {
        checkNotNull();
        disableLoadMoreIfNotFullPage(getRecyclerView());
    }

    /**
     * <p>
     * 不是配置项！！
     * <p>
     * 这个方法是用来检查是否满一屏的，所以只推荐在 {@link #setNewData(List)} 之后使用
     * 原理很简单，先关闭 load more，检查完了再决定是否开启
     * <p>
     * 不是配置项！！
     *
     * @param recyclerView your recyclerView
     * @see #setNewData(List)
     */
    private void disableLoadMoreIfNotFullPage(RecyclerView recyclerView) {
        setEnableLoadMore(false);
        if (recyclerView == null) {
            return;
        }
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager == null) {
            return;
        }
        if (manager instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
            recyclerView.postDelayed(() -> {
                if (isFullScreen(linearLayoutManager)) {
                    setEnableLoadMore(true);
                }
            }, 50);
        } else if (manager instanceof StaggeredGridLayoutManager) {
            final StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) manager;
            recyclerView.postDelayed(() -> {
                final int[] positions = new int[staggeredGridLayoutManager.getSpanCount()];
                staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(positions);
                //得到当前显示的最大位置数
                int pos = getTheBiggestNumber(positions) + 1;
                if (pos != getItemCount()) {
                    setEnableLoadMore(true);
                }
            }, 50);
        }
    }

    /**
     * 解析布局文件，获取View
     *
     * @param layoutResId 布局文件id
     * @param parent
     * @return
     */
    protected View getItemView(@LayoutRes int layoutResId, ViewGroup parent) {
        return mLayoutInflater.inflate(layoutResId, parent, false);
    }

    /**
     * 得到最大数字
     *
     * @param numbers
     * @return
     */
    private int getTheBiggestNumber(int[] numbers) {
        int tmp = -1;
        if (numbers == null || numbers.length == 0) {
            return tmp;
        }
        for (int num : numbers) {
            if (num > tmp) {
                tmp = num;
            }
        }
        return tmp;
    }

    /**
     * 设置holder对应类型布局铺满
     *
     * @param holder 要铺满的Holde
     */
    protected void setFullSpan(RecyclerView.ViewHolder holder) {
        if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder
                    .itemView.getLayoutParams();
            params.setFullSpan(true);
        }
    }

    /**
     * 判断是否满屏
     *
     * @param llm
     * @return
     */
    private boolean isFullScreen(LinearLayoutManager llm) {
        return (llm.findLastCompletelyVisibleItemPosition() + 1) != getItemCount() ||
                llm.findFirstCompletelyVisibleItemPosition() != 0;
    }

    /**
     * 自动加载更多
     *
     * @param position
     */
    private void autoLoadMore(int position) {

        if (TYPE_LOAD_MORE != getItemViewType(position)) {
            return;
        }
        if (mLoadMoreView.getLoadMoreStatus() != LoadMoreView.STATUS_DEFAULT) {
            return;
        }
        mLoadMoreView.setLoadMoreStatus(LoadMoreView.STATUS_LOADING);
        if (!mLoading) {
            mLoading = true;
            if (mRecyclerView != null) {
                mRecyclerView.post(() -> {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                });
            } else {
                if (onLoadMoreListener != null) {
                    onLoadMoreListener.onLoadMore();
                }
            }
        }
    }

    /**
     * 加载更多监听
     */
    public interface OnLoadMoreListener {

        /**
         * 加载更多回掉
         */
        void onLoadMore();
    }
}
