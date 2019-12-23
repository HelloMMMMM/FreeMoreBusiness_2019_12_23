package com.fx.feemore.business.ui.merchant;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.AllianceBean;
import com.fx.feemore.business.databinding.AcAllianceListBinding;
import com.fx.feemore.business.ui.merchant.adapter.AdMyAlliance;
import com.fx.feemore.business.ui.merchant.vm.VMAllianceList;
import com.fx.feemore.business.util.LoadManageUtil;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:商家联盟列表界面
 * author: frj
 * modify date: 2019/3/7
 */
public class AcAllianceList extends BaseBindActivity<AcAllianceListBinding, VMAllianceList> implements SwipeRefreshLayout.OnRefreshListener
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_alliance_list;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("商家联盟");

        initLiveData();

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(this, 6), false, false, true));
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdMyAlliance adapter = new AdMyAlliance();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            AllianceBean bean = (AllianceBean) adapter1.getItem(position);
            if (bean != null)
            {
                Bundle bundle = new Bundle();
                bundle.putString(KEY, bean.getNAME());
                bundle.putString(KEY_STR, bean.getIMG());
                jump(AcAllianceQRCode.class, bundle, false);
            }
        });
        adapter.setOnLoadMoreListener(() ->
                viewModel.getData(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), viewModel.currPage + 1, PAGE_NUM), binding.recyclerView);

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initLiveData()
    {
        viewModel.resAlliances.observe(this, list -> LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdMyAlliance) binding.recyclerView.getAdapter(), viewModel.currPage, list));
        viewModel.resHttpFail.observe(this, httpFailBean -> {
            if (binding.swipeRefreshLayout != null)
            {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_alliance, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_my:
                jump(AcMyAlliance.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh()
    {
        viewModel.getData(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), PAGE_START, PAGE_NUM);
    }
}
