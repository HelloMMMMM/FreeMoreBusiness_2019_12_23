package com.fx.feemore.business.ui.merchant;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcInitateActiveBinding;
import com.fx.feemore.business.ui.interest.adapter.AdPhoto;
import com.fx.feemore.business.ui.merchant.vm.VMInitateActive;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DensityUtil;

/**
 * function:发起活动
 * author: frj
 * modify date: 2018/12/31
 */
public class AcInitateActive extends BaseBindActivity<AcInitateActiveBinding, VMInitateActive>
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_initate_active;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.initate_active_title);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(this, 15), false, false, true));
//        AdPhoto adapter = new AdPhoto();
//        adapter.addData(new AdPhoto.Enity(AdPhoto.Enity.TYPE_ADD));
//        adapter.bindToRecyclerView(binding.recyclerView);
    }
}
