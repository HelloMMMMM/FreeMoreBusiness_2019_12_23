package com.fx.feemore.business.ui.home.fragment.interest;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fx.feemore.business.bean.ConsumeRecordBean;
import com.fx.feemore.business.databinding.FInterestItemBinding;
import com.fx.feemore.business.ui.home.adapter.AdConsumeRecord;
import com.fx.feemore.business.ui.home.vm.VMInterestItem;
import com.fx.feemore.business.ui.interest.AcInterestDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * function:权益包消费记录
 * author: frj
 * modify date: 2018/12/30
 */
public class FConsumeRecord extends FInterestItem<FInterestItemBinding, VMInterestItem>
{

    public static FConsumeRecord newInstance()
    {
        return new FConsumeRecord();
    }

    @Override
    protected void bindType()
    {
        viewModel.setConsumeRecord();
    }

    @NonNull
    @Override
    protected BaseQuickAdapter getAdapter()
    {
        AdConsumeRecord adapter = new AdConsumeRecord();
        adapter.setNewData(getList());
        adapter.setOnItemClickListener((adapter1, view, position) -> jump(AcInterestDetail.class));
        return adapter;
    }

    private List<ConsumeRecordBean> getList()
    {
        List<ConsumeRecordBean> list = new ArrayList<>();
        list.add(new ConsumeRecordBean());
        list.add(new ConsumeRecordBean());
        list.add(new ConsumeRecordBean());
        list.add(new ConsumeRecordBean());
        return list;
    }
}
