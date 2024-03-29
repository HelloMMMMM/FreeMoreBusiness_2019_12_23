package com.fx.feemore.business.util;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import static android.widget.LinearLayout.VERTICAL;

/**
 * function:RecyclerView的分隔
 * author: frj
 * modify date: 2018/3/6
 */
public class SpaceDecoration extends RecyclerView.ItemDecoration
{
    private int space;
    private int headerCount = -1;
    private int footerCount = Integer.MAX_VALUE;
    private boolean mPaddingEdgeSide = true;
    private boolean mPaddingStart = true;
    private boolean mPaddingHeaderFooter = false;
    private boolean mPaddingLastItem = false;


    public SpaceDecoration(int space)
    {
        this.space = space;
    }

    public void setPaddingEdgeSide(boolean mPaddingEdgeSide)
    {
        this.mPaddingEdgeSide = mPaddingEdgeSide;
    }

    public void setPaddingStart(boolean mPaddingStart)
    {
        this.mPaddingStart = mPaddingStart;
    }

    public void setPaddingHeaderFooter(boolean mPaddingHeaderFooter)
    {
        this.mPaddingHeaderFooter = mPaddingHeaderFooter;
    }

    public void setPaddingLastItem(boolean mPaddingLastItem)
    {
        this.mPaddingLastItem = mPaddingLastItem;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        int position = parent.getChildAdapterPosition(view);
        int spanCount = 0;
        int orientation = 0;
        int spanIndex = 0;
        int headerCount = 0, footerCount = 0;
        if (parent.getAdapter() instanceof BaseQuickAdapter)
        {
            headerCount = ((BaseQuickAdapter) parent.getAdapter()).getHeaderLayoutCount();
            footerCount = ((BaseQuickAdapter) parent.getAdapter()).getFooterLayoutCount();
        }

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof StaggeredGridLayoutManager)
        {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            spanIndex = ((StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        } else if (layoutManager instanceof GridLayoutManager)
        {
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            spanIndex = ((GridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        } else if (layoutManager instanceof LinearLayoutManager)
        {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
            spanCount = 1;
            spanIndex = 0;
        }

        /**
         * 普通Item的尺寸
         */
        if ((position >= headerCount && position < parent.getAdapter().getItemCount() - footerCount))
        {

            if (orientation == VERTICAL)
            {
                float expectedWidth = (float) (parent.getWidth() - space * (spanCount + (mPaddingEdgeSide ? 1 : -1))) / spanCount;
                float originWidth = (float) parent.getWidth() / spanCount;
                float expectedX = (mPaddingEdgeSide ? space : 0) + (expectedWidth + space) * spanIndex;
                float originX = originWidth * spanIndex;
                outRect.left = (int) (expectedX - originX);
                outRect.right = (int) (originWidth - outRect.left - expectedWidth);
                if (position - headerCount < spanCount && mPaddingStart)
                {
                    outRect.top = space;
                }
                if (!mPaddingLastItem)
                {
                    if (!mPaddingHeaderFooter)
                    {
                        int dataCount = parent.getAdapter().getItemCount() - footerCount - headerCount;
                        int line = dataCount / spanCount;
                        if (dataCount % spanCount != 0)
                        {
                            line++;
                        }
                        int tempLine = (position + 1) / spanCount;
                        if ((position + 1) % spanCount != 0)
                        {
                            tempLine++;
                        }
                        if (tempLine == line)
                        {
                            outRect.bottom = 0;
                        } else
                        {
                            outRect.bottom = space;
                        }
                    } else
                    {
                        outRect.bottom = space;
                    }
                } else
                {
                    outRect.bottom = space;
                }
                return;
            } else
            {
                float expectedHeight = (float) (parent.getHeight() - space * (spanCount + (mPaddingEdgeSide ? 1 : -1))) / spanCount;
                float originHeight = (float) parent.getHeight() / spanCount;
                float expectedY = (mPaddingEdgeSide ? space : 0) + (expectedHeight + space) * spanIndex;
                float originY = originHeight * spanIndex;
                outRect.bottom = (int) (expectedY - originY);
                outRect.top = (int) (originHeight - outRect.bottom - expectedHeight);
                if (position - headerCount < spanCount && mPaddingStart)
                {
                    outRect.left = space;
                }
                outRect.right = space;
                return;
            }
        } else if (mPaddingHeaderFooter)
        {
            if (orientation == VERTICAL)
            {
                outRect.right = outRect.left = mPaddingEdgeSide ? space : 0;
                outRect.top = mPaddingStart ? space : 0;
            } else
            {
                outRect.top = outRect.bottom = mPaddingEdgeSide ? space : 0;
                outRect.left = mPaddingStart ? space : 0;
            }
        }

    }
}
