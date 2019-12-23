package com.fx.feemore.business.ui.login;

import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.FragmentViewPagerBase;
import com.fx.feemore.business.databinding.AcLoginBinding;
import com.fx.feemore.business.ui.home.adapter.AdHomeItem;
import com.fx.feemore.business.ui.login.fragment.FPhoneVerify;
import com.fx.feemore.business.ui.login.vm.VMLogin;
import com.fx.feemore.business.ui.register.AcRegister;
import com.fx.feemore.business.util.JPushUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * function:登录页面
 * author: frj
 * modify date: 2018/12/23
 */
public class AcLogin extends BaseBindActivity<AcLoginBinding, VMLogin> implements HasSupportFragmentInjector
{

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_login;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        binding.tvRegister.setOnClickListener(v -> jump(AcRegister.class));

        JPushUtil.cleanNotifies();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.tvRegister.getLayoutParams();
            if (params != null)
            {
                params.topMargin += DisplayUtil.getStatusBarHeight(this);
                binding.tvRegister.setLayoutParams(params);
            }
        }

        initData();
    }

    private void initData()
    {
        String[] tabTitles = getResources().getStringArray(R.array.login_tabs);
        final List<FragmentViewPagerBase> fragmengs = new ArrayList<>();
//        fragmengs.add(new FAccountPassword());
        fragmengs.add(new FPhoneVerify());
        AdHomeItem adapter = new AdHomeItem(getSupportFragmentManager(), fragmengs, tabTitles);
        binding.viewPager.setAdapter(adapter);
//        binding.viewPager.setOffscreenPageLimit(tabTitles.length);
        binding.tabs.setViewPager(binding.viewPager);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector()
    {
        return fragmentInjector;
    }
}
