package com.fx.feemore.business.ui.merchant;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.AllianceBean;
import com.fx.feemore.business.databinding.AcMyAllianceBinding;
import com.fx.feemore.business.ui.merchant.adapter.AdMyAlliance;
import com.fx.feemore.business.ui.merchant.vm.VMMyAlliance;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.LoadManageUtil;
import com.fx.feemore.business.util.PermissionUtil;
import com.fx.feemore.business.util.RequestAndResultCode;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:我的联盟
 * author: frj
 * modify date: 2019/1/2
 */
public class AcMyAlliance extends BaseBindActivity<AcMyAllianceBinding, VMMyAlliance> implements SwipeRefreshLayout.OnRefreshListener
{
    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_my_alliance;
    }

    @Override
    protected void init()
    {
        setToolbarTitle(R.string.my_alliance_title);

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
        getMenuInflater().inflate(R.menu.menu_my_alliance, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_go:
                if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_OPERATOR))
                {
                    AlertDialogUtil.createPermissionTips(this).show();
                    return true;
                }
                jump(AcInitateAlliance.class, RequestAndResultCode.REQUEST_NEED_REFRESH_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (RequestAndResultCode.REQUEST_NEED_REFRESH_CODE == requestCode && RESULT_OK == resultCode)
        {
            binding.swipeRefreshLayout.setRefreshing(true);
            onRefresh();
        }
    }
}
