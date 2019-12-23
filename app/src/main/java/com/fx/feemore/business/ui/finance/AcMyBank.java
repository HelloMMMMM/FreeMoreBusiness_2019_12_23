package com.fx.feemore.business.ui.finance;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.fx.feemore.business.R;
import com.fx.feemore.business.bean.BankCardBean;
import com.fx.feemore.business.databinding.AcMyBankBinding;
import com.fx.feemore.business.ui.finance.adapter.AdMyBank;
import com.fx.feemore.business.ui.finance.vm.VMMyBank;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.LoadManageUtil;
import com.fx.feemore.business.util.PermissionUtil;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.RequestAndResultCode;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.ToastUtil;

/**
 * function:我的银行卡页面
 * author: frj
 * modify date: 2019/1/20
 */
public class AcMyBank extends BaseBindActivity<AcMyBankBinding, VMMyBank> implements SwipeRefreshLayout.OnRefreshListener
{

    private ProgressDialog dialog;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_my_bank;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("我的银行卡");

        initLiveData();

        binding.swipeRefreshLayout.setOnRefreshListener(this);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(this, 18), true, false, true));

        AdMyBank adapter = new AdMyBank();
        adapter.bindToRecyclerView(binding.recyclerView);

        adapter.setOnItemChildClickListener((adapter1, view, position) -> {
            if (R.id.tv_delete == view.getId())
            {
                BankCardBean bean = (BankCardBean) adapter1.getItem(position);
                if (bean != null)
                {
                    if (dialog == null)
                    {
                        dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
                    }
                    if (!dialog.isShowing())
                    {
                        dialog.show();
                    }
                    viewModel.delBankCard(bean.getBank_ID());
                }
            }
        });

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initLiveData()
    {
        viewModel.resHttpFail.observe(this, httpFailBean -> {
            if (binding.swipeRefreshLayout != null)
            {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (dialog != null)
            {
                dialog.dismiss();
            }
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.resBankCards.observe(this, list -> LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdMyBank) binding.recyclerView.getAdapter(), PAGE_START, list));
        viewModel.resSucc.observe(this, responseBean -> {
            if (dialog != null)
            {
                dialog.dismiss();
            }
            binding.swipeRefreshLayout.setRefreshing(true);
            onRefresh();
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_my_bank, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_add_bank:
                if (!PermissionUtil.havePermission(PermissionUtil.PERMISSION_STORE_OPERATOR))
                {
                    AlertDialogUtil.createPermissionTips(this).show();
                    return true;
                }
                jump(AcAddCard.class, RequestAndResultCode.REQUEST_NEED_REFRESH_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onRefresh()
    {
        viewModel.getData();
    }
}
