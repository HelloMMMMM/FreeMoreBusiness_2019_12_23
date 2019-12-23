package com.hengxian.baselib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wangfangqi on 2017/5/27.
 * 文中所有的方向都是布局方向
 */

public class GridDividerDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private final Rect mBounds = new Rect();
    private Drawable mVerticalDivider;
    private Drawable mHorizontalDivider;

    /**
     * 最后一组Span的索引，用于判断view是否绘制到最后一行
     */
    private int mSpanGroupLastIndex;
    /**
     * 最后一组Span所有项的Span Size总和，对比SpanCount可判断最后一项是否刚刚占满屏幕。
     */
    private int mLastSpanGroupSize;
    /**
     * Current orientation. Either {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    private int mOrientation;

    /**
     * 不绘制相交区域
     */
    private boolean mNoDrawIntersection;
    /**
     * 分割线间距，可手动设置
     */
    private int mSpacingSizeV, mSpacingSizeH;

    private GridDividerDecoration(Context context, boolean noDrawIntersection) {
        mNoDrawIntersection = noDrawIntersection;
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mVerticalDivider = a.getDrawable(0);
        mHorizontalDivider = a.getDrawable(0);
        a.recycle();
    }

    private void setVerticalDrawables(Drawable drawable) {
        if (drawable == null) return;
        mVerticalDivider = drawable;
    }

    private void setHorizontalDrawables(Drawable drawable) {
        if (drawable == null) return;
        mHorizontalDivider = drawable;
    }

    /**
     * 设置分割线颜色
     *
     * @param color int
     */
    public void setColor(@ColorInt int color) {
        if (color == -1) return;
        mHorizontalDivider = DrawableCompat.wrap(mHorizontalDivider);
        mVerticalDivider = DrawableCompat.wrap(mVerticalDivider);
        DrawableCompat.setTint(mHorizontalDivider, color);
        DrawableCompat.setTint(mVerticalDivider, color);
    }

    private int getSpacingSizeV() {
        if (mSpacingSizeV > 0)
            return mSpacingSizeV;
        return mVerticalDivider.getIntrinsicHeight();
    }

    private void setSpacingSizeV(int spacingSizeV) {
        this.mSpacingSizeV = spacingSizeV;
    }

    private int getSpacingSizeH() {
        if (mSpacingSizeH > 0)
            return mSpacingSizeH;
        return mHorizontalDivider.getIntrinsicWidth();
    }

    private void setSpacingSizeH(int spacingSizeH) {
        this.mSpacingSizeH = spacingSizeH;
    }

    @Override
    @SuppressLint("NewApi")
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        canvas.save();
        //v前缀表示横向分割线，h前缀表示纵向分割线
        int vTop;
        int hLeft;
        //右、下   两个坐标是公共的
        int right;
        int bottom;

        if (parent.getClipToPadding()) {
            canvas.clipRect(parent.getPaddingLeft(), parent.getPaddingTop(),
                    parent.getWidth() - parent.getPaddingRight(),
                    parent.getHeight() - parent.getPaddingBottom());
        }
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            right = mBounds.right + Math.round(ViewCompat.getTranslationX(child));
            bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));

            //设置垂直方向的分割线
            vTop = bottom - getSpacingSizeV();
            if (mBounds.bottom != child.getBottom() + lp.bottomMargin) {
                int actualRight = right;
                //如果不需要绘制相交区域，重新计算右坐标
                if (mNoDrawIntersection)
                    actualRight -= getSpacingSizeH();
                mVerticalDivider.setBounds(mBounds.left, vTop, actualRight, bottom);
                mVerticalDivider.draw(canvas);
                bottom -= getSpacingSizeV();
            }
            //设置水平方向的分割线
            hLeft = right - getSpacingSizeH();
            if (mBounds.right != child.getRight() + lp.rightMargin) {
                mHorizontalDivider.setBounds(hLeft, mBounds.top, right, bottom);
                mHorizontalDivider.draw(canvas);
            }
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        GridLayoutManager.SpanSizeLookup spanSizeLookup = layoutManager.getSpanSizeLookup();

        int position = parent.getChildAdapterPosition(view);
        int spanCount = layoutManager.getSpanCount();
        int spanSize = spanSizeLookup.getSpanSize(position);
        int spanGroupIndex = spanSizeLookup.getSpanGroupIndex(position, spanCount);

        if (position == 0) {
            mOrientation = layoutManager.getOrientation();
            //初始化，获取最后一组的索引
            int itemCount = parent.getAdapter().getItemCount();
            mSpanGroupLastIndex = spanSizeLookup.getSpanGroupIndex(itemCount - 1, spanCount);
        }

        //相等表示当前项位于最后一组
        if (spanGroupIndex == mSpanGroupLastIndex) {
            //计算最后一组中到当前item时的spanSize总和
            mLastSpanGroupSize += spanSize;

            if (mLastSpanGroupSize < spanCount) {
                if (mOrientation == VERTICAL) {
                    outRect.set(0, 0, getSpacingSizeH(), 0);
                } else {
                    outRect.set(0, 0, 0, getSpacingSizeV());
                }
            } else {
                super.getItemOffsets(outRect, view, parent, state);
            }
        } else {
            //得到下一项的SpanIndex
            int nextChildSpanIndex = spanSizeLookup.getSpanIndex(position + 1, spanCount);
            //如果下一项SpanIndex等于0，表示当前项是该行最后一个
            if (0 == nextChildSpanIndex) {
                //数值归零
                mLastSpanGroupSize = 0;
                if (mOrientation == VERTICAL) {
                    outRect.set(0, 0, 0, getSpacingSizeV());
                } else {
                    outRect.set(0, 0, getSpacingSizeH(), 0);
                }
            } else {
                outRect.set(0, 0, getSpacingSizeH(), getSpacingSizeV());
            }
        }
    }

    public static class Builder {

        private Context mContext;
        private int mColor = -1;
        private Drawable mVerticalDivider;
        private Drawable mHorizontalDivider;
        private boolean mNoDrawIntersection;
        private int mSpacingSizeV;
        private int mSpacingSizeH;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder noDrawIntersection() {
            mNoDrawIntersection = true;
            return this;
        }

        public Builder setColor(@ColorInt int color) {
            mColor = color;
            return this;
        }

        public Builder setDividerDrawable(Drawable drawable) {
            setVerticalDrawable(drawable);
            setHorizontalDrawable(drawable);
            return this;
        }

        public Builder setDividerDrawable(int drawable) {
            setVerticalDrawable(drawable);
            setHorizontalDrawable(drawable);
            return this;
        }

        public Builder setVerticalDrawable(Drawable drawable) {
            mVerticalDivider = drawable;
            return this;
        }

        public Builder setVerticalDrawable(int drawable) {
            mVerticalDivider = ContextCompat.getDrawable(mContext, drawable);
            return this;
        }

        public Builder setHorizontalDrawable(Drawable drawable) {
            mHorizontalDivider = drawable;
            return this;
        }

        public Builder setHorizontalDrawable(int drawable) {
            mHorizontalDivider = ContextCompat.getDrawable(mContext, drawable);
            return this;
        }

        public Builder setSpacingSizeV(int spacingSizeV) {
            this.mSpacingSizeV = spacingSizeV;
            return this;
        }

        public Builder setSpacingSizeH(int spacingSizeH) {
            this.mSpacingSizeH = spacingSizeH;
            return this;
        }

        public RecyclerView.ItemDecoration build() {
            GridDividerDecoration listDivider = new GridDividerDecoration(mContext, mNoDrawIntersection);
            listDivider.setVerticalDrawables(mVerticalDivider);
            listDivider.setHorizontalDrawables(mHorizontalDivider);
            listDivider.setColor(mColor);
            listDivider.setSpacingSizeH(mSpacingSizeH);
            listDivider.setSpacingSizeV(mSpacingSizeV);
            return listDivider;
        }
    }
}
