package com.fx.feemore.business.ui.home.fragment.interest;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.databinding.FInterestItemBinding;
import com.fx.feemore.business.ui.home.adapter.AdMyInterest;
import com.fx.feemore.business.ui.home.vm.VMInterestItem;
import com.fx.feemore.business.ui.interest.AcInterestDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * function:我的权益包列表
 * author: frj
 * modify date: 2018/12/30
 */
public class FMyInterest extends FInterestItem<FInterestItemBinding, VMInterestItem>
{

    public static FMyInterest newInstance()
    {
        return new FMyInterest();
    }

    @Override
    protected void bindType()
    {
        viewModel.setMyInterest();
    }

    @NonNull
    @Override
    protected BaseQuickAdapter getAdapter()
    {
        AdMyInterest adapter = new AdMyInterest();
        adapter.setNewData(getList());
        adapter.setOnItemClickListener((adapter1, view, position) -> jump(AcInterestDetail.class));
        return adapter;
    }

    private List<InterestBean> getList()
    {
        List<InterestBean> list = new ArrayList<>();
        list.add(new InterestBean());
        list.add(new InterestBean());
        list.add(new InterestBean());
        list.add(new InterestBean());
        return list;
    }
}
