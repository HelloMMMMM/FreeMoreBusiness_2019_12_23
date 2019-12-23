package com.fx.feemore.business.ui.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fx.feemore.business.R;

import java.util.ArrayList;
import java.util.List;

public class AdHomeV3Case extends PagerAdapter {

    private Context context;
    private List<Object> images;
    private List<View> views;

    public AdHomeV3Case(Context context, List<Object> images) {
        this.context = context;
        this.images = images;
        views = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return images != null ? images.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view;
        if (position < views.size()) {
            view = views.get(position);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.layout_home_v3_case_item, null);
            views.add(view);
        }
        ImageView imageView = view.findViewById(R.id.iv_case);
        Glide.with(context).load(images.get(position)).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
