package com.fx.feemore.business.ui.interest;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcPublishInterestBinding;
import com.fx.feemore.business.ui.interest.adapter.AdPhoto;
import com.fx.feemore.business.ui.interest.vm.VMPublishInterest;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DensityUtil;

/**
 * function:发布权益包
 * author: frj
 * modify date: 2018/12/30
 */
public class AcPublishInterest extends BaseBindActivity<AcPublishInterestBinding, VMPublishInterest>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_publish_interest;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.publish_interest_title);
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(this, 15), false, false, true));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
//        AdPhoto adapter = new AdPhoto();
//        adapter.addData(new AdPhoto.Enity(AdPhoto.Enity.TYPE_ADD));
//        adapter.bindToRecyclerView(binding.recyclerView);
    }
}
