package com.fx.feemore.business.ui.merchant.account;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.AccountBean;
import com.fx.feemore.business.databinding.AcAccountListBinding;
import com.fx.feemore.business.ui.merchant.account.adapter.AdAccountItem;
import com.fx.feemore.business.ui.merchant.account.vm.VMAccountList;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.LoadManageUtil;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.RequestAndResultCode;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:子账号列表
 * author: frj
 * modify date: 2019/5/15
 */
public class AcAccountList extends BaseBindActivity<AcAccountListBinding, VMAccountList> implements SwipeRefreshLayout.OnRefreshListener
{

    private ProgressDialog progressDialog;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_account_list;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("子账号列表");

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(this, 6), false, true, true, true));
        AdAccountItem adAccountItem = new AdAccountItem();
        adAccountItem.bindToRecyclerView(binding.recyclerView);
        adAccountItem.setOnItemChildClickListener((adapter, view, position) -> {
            AccountBean bean = (AccountBean) adapter.getItem(position);
            if (bean != null)
            {
                AlertDialogUtil.create(this, "您确定删除该帐号吗？", (dialog, which) -> {
                    if (progressDialog == null)
                    {
                        progressDialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
                    }
                    progressDialog.show();
                    viewModel.delAccount(bean.getSTOREID(), bean.getSTOREUSER_ID());
                }, null).show();
            }
        });
        adAccountItem.setOnLoadMoreListener(() -> viewModel.getAccounts(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), viewModel.currPage + 1, PAGE_NUM), binding.recyclerView);

        initLiveData();

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    /**
     * 初始化
     */
    private void initLiveData()
    {
        viewModel.accountBeansRes.observe(this, list ->
                LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdAccountItem) binding.recyclerView.getAdapter(), viewModel.currPage, list)
        );

        viewModel.delRes.observe(this, responseBean -> {
            if (progressDialog != null)
            {
                progressDialog.dismiss();
            }
            binding.swipeRefreshLayout.setRefreshing(true);
            onRefresh();
        });

        viewModel.failRes.observe(this, httpFailBean -> {
            if (progressDialog != null)
            {
                progressDialog.dismiss();
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
        getMenuInflater().inflate(R.menu.menu_add_account, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (R.id.menu_add_account == item.getItemId())
        {
            jump(AcAddAccount.class, RequestAndResultCode.REQUEST_NEED_REFRESH_CODE);
            return true;
        } else
        {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh()
    {
        viewModel.getAccounts(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), PAGE_START, PAGE_NUM);
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
