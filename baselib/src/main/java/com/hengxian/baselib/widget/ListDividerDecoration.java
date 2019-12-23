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
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by wangfangqi on 16/8/18.
 * recyclerView分割线
 */
public class ListDividerDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private final Rect mBounds = new Rect();
    private Drawable mDivider;

    /**
     * 分割线间距，可手动设置
     */
    private int mSpacingSize;
    /**
     * Current orientation. Either {@link #HORIZONTAL} or {@link #VERTICAL}.
     */
    private int mOrientation;

    private boolean mHasEndLine;

    private ListDividerDecoration() {
    }

    private ListDividerDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0).mutate();
        a.recycle();
        setOrientation(orientation);
    }

    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("Invalid orientation.");
        }
        mOrientation = orientation;
    }

    private void setDrawable(Drawable drawable) {
        if (drawable == null) return;
        mDivider = drawable.mutate();
    }

    /**
     * 设置分割线颜色
     *
     * @param color int
     */
    private void setColor(@ColorInt int color) {
        if (color == -1) return;
        mDivider = DrawableCompat.wrap(mDivider);
        DrawableCompat.setTint(mDivider, color);
    }

    private void hasEndLine() {
        mHasEndLine = true;
    }

    public int getSpacingSize() {
        if (mSpacingSize > 0)
            return mSpacingSize;

        if (mOrientation == VERTICAL)
            return mDivider.getIntrinsicHeight();
        else
            return mDivider.getIntrinsicWidth();

    }

    public void setSpacingSize(int spacingSize) {
        this.mSpacingSize = spacingSize;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null) {
            return;
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    @SuppressLint("NewApi")
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            if (!mHasEndLine && parent.getChildAdapterPosition(child) == (parent.getAdapter().getItemCount() - 1)) {
                continue;
            }
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            final int bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
            final int top = bottom - getSpacingSize();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    @SuppressLint("NewApi")
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int top;
        final int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            if (!mHasEndLine && parent.getChildAdapterPosition(child) == (parent.getAdapter().getItemCount() - 1)) {
                continue;
            }
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            final int right = mBounds.right + Math.round(ViewCompat.getTranslationX(child));
            final int left = right - getSpacingSize();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (!mHasEndLine && parent.getChildAdapterPosition(view) == (parent.getAdapter().getItemCount() - 1)) {
            super.getItemOffsets(outRect, view, parent, state);
            return;
        }
        if (mOrientation == VERTICAL) {
            outRect.set(0, 0, 0, getSpacingSize());
        } else {
            outRect.set(0, 0, getSpacingSize(), 0);
        }
    }

    public static class Builder {
        private Context mContext;
        private int mColor = -1;
        private Drawable mDrawable;
        private int mOrientation = VERTICAL;
        private int mSpacingSize;
        private boolean hasEndLine = false;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder horizontal() {
            mOrientation = HORIZONTAL;
            return this;
        }

        /**
         * 设置分割线颜色
         * @param color
         * @return
         */
        public Builder setColor(@ColorInt int color) {
            mColor = color;
            return this;
        }

        public Builder setDividerDrawable(Drawable drawable) {
            mDrawable = drawable;
            return this;
        }

        public Builder setDividerDrawable(int drawable) {
            mDrawable = ContextCompat.getDrawable(mContext, drawable);
            return this;
        }

        /**
         * 设置线宽
         * @param spacingSize 宽度px
         */
        public Builder setSpacingSize(int spacingSize) {
            this.mSpacingSize = spacingSize;
            return this;
        }

        /**
         * 列表尾部添加分割线，默认不添加
         */
        public Builder hasEndLine() {
            hasEndLine = true;
            return this;
        }

        public RecyclerView.ItemDecoration build() {
            ListDividerDecoration listDivider = new ListDividerDecoration(mContext, mOrientation);
            listDivider.setDrawable(mDrawable);
            listDivider.setColor(mColor);
            listDivider.setSpacingSize(mSpacingSize);
            if (hasEndLine) listDivider.hasEndLine();
            return listDivider;
        }
    }
}
