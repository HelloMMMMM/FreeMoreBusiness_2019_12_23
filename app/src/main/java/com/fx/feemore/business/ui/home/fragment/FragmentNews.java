package com.fx.feemore.business.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.FNewsBinding;
import com.fx.feemore.business.ui.home.adapter.AdNews;
import com.fx.feemore.business.ui.home.vm.VMHome;
import com.fx.feemore.business.ui.home.vm.VMNews;
import com.hengxian.baselib.base.BaseBindFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentNews extends BaseBindFragment<FNewsBinding, VMHome> {
    @Override
    protected int getLayoutId() {
        return R.layout.f_news;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        binding.newsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        AdNews adNews = new AdNews(R.layout.layout_drainage_news_item);
        binding.newsList.setAdapter(adNews);
        List<VMNews> data = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            data.add(new VMNews());
        }
        adNews.addData(data);
    }
}
