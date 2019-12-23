package com.fx.feemore.business.ui.home;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;

import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.databinding.ActivityMainBinding;
import com.fx.feemore.business.ui.home.fragment.FragmentDrainage;
import com.fx.feemore.business.ui.home.fragment.FragmentHomeV2;
import com.fx.feemore.business.ui.home.fragment.FragmentHomeV3;
import com.fx.feemore.business.ui.home.fragment.FragmentMine;
import com.fx.feemore.business.ui.home.fragment.FragmentPerson;
import com.fx.feemore.business.ui.home.fragment.FragmentUtil;
import com.fx.feemore.business.ui.home.vm.VMMain;
import com.fx.feemore.business.util.BroadCastUtil;
import com.hengxian.baselib.base.BaseBindActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseBindActivity<ActivityMainBinding, VMMain> implements HasSupportFragmentInjector, View.OnClickListener {

    /**
     * 首页索引
     */
    private static final int INDEX_HOME = 0;

    /**
     * 引流案例索引
     */
    public static final int INDEX_DRAINAGE = 1;

    /**
     * 工具索引
     */
    public static final int INDEX_UTIL = 2;

    /**
     * 我的索引
     */
    public static final int INDEX_MINE = 3;

    /**
     * 项数量
     */
    private static final int SIZE = 4;

    /**
     * 无效值
     */
    private static final int INVALID = -1;

    private Fragment[] fragments;
    private TextView[] textViews;   //文本集合

    //当前选中索引
    private int currIndex = INVALID;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void handleCreate(@Nullable Bundle savedInstanceState) {
        super.handleCreate(savedInstanceState);
        initFragments(savedInstanceState);
        if (savedInstanceState != null) {
            currIndex = savedInstanceState.getInt(KEY, INVALID);
        }
    }

    @Override
    protected void init() {
        binding.setVm(viewModel);
        initListener();
        initData();
    }

    /**
     * 初始化监听事件
     */
    private void initListener() {
        binding.tvHome.setOnClickListener(this);
        binding.tvDrainage.setOnClickListener(this);
        binding.tvUtil.setOnClickListener(this);
        binding.tvMine.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        initItems();
        //判断页面是否被重新创建，如果是，那么恢复到之前状态
        if (currIndex > INVALID && currIndex < textViews.length) {
            switchItem(textViews[currIndex]);
        } else {
            switchItem(binding.tvHome);
        }
        viewModel.refreshStoryInfo(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), AppContext.getInstanceBase().getStoryUserBean().getZIACCOUNT());
        registerBroadCastReceiver();
    }

    /**
     * 初始化Fragment集合
     */
    private void initFragments(Bundle savedInstanceState) {
        if (fragments == null) {
            fragments = new Fragment[SIZE];
        }
        if (savedInstanceState != null) {
            fragments[INDEX_HOME] = getSupportFragmentManager().findFragmentByTag(String.valueOf(INDEX_HOME));
            fragments[INDEX_DRAINAGE] = getSupportFragmentManager().findFragmentByTag(String.valueOf(INDEX_DRAINAGE));
            fragments[INDEX_UTIL] = getSupportFragmentManager().findFragmentByTag(String.valueOf(INDEX_UTIL));
            fragments[INDEX_MINE] = getSupportFragmentManager().findFragmentByTag(String.valueOf(INDEX_MINE));
            if (fragments[INDEX_HOME] == null) {
//                fragments[INDEX_HOME] = new FragmentHome();
//                fragments[INDEX_HOME] = new FragmentHomeV2();
                fragments[INDEX_HOME] = new FragmentHomeV3();
            }
            if (fragments[INDEX_DRAINAGE] == null) {
//                fragments[INDEX_DRAINAGE] = new FragmentInterest();
                fragments[INDEX_DRAINAGE] = new FragmentDrainage();
            }
            if (fragments[INDEX_UTIL] == null) {
//                fragments[INDEX_UTIL] = new FragmentFinance();
                fragments[INDEX_UTIL] = new FragmentUtil();
            }
            if (fragments[INDEX_MINE] == null) {
//                fragments[INDEX_MINE] = new FragmentPerson();
                fragments[INDEX_MINE] = new FragmentMine();
            }
        } else {
//            fragments[INDEX_HOME] = new FragmentHome();
//            fragments[INDEX_HOME] = new FragmentHomeV2();
            fragments[INDEX_HOME] = new FragmentHomeV3();
//            fragments[INDEX_DRAINAGE] = new FragmentInterest();
            fragments[INDEX_DRAINAGE] = new FragmentDrainage();
//            fragments[INDEX_UTIL] = new FragmentFinance();
            fragments[INDEX_UTIL] = new FragmentUtil();
//            fragments[INDEX_MINE] = new FragmentPerson();
            fragments[INDEX_MINE] = new FragmentMine();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_content, fragments[INDEX_HOME], String.valueOf(INDEX_HOME))
                    .add(R.id.fl_content, fragments[INDEX_DRAINAGE], String.valueOf(INDEX_DRAINAGE))
                    .add(R.id.fl_content, fragments[INDEX_UTIL], String.valueOf(INDEX_UTIL))
                    .add(R.id.fl_content, fragments[INDEX_MINE], String.valueOf(INDEX_MINE))
                    .hide(fragments[INDEX_DRAINAGE])
                    .hide(fragments[INDEX_UTIL])
                    .hide(fragments[INDEX_MINE])
                    .commit();
        }
    }

    /**
     * 初始化切换项
     */
    private void initItems() {
        binding.tvHome.setTag(INDEX_HOME);
        binding.tvDrainage.setTag(INDEX_DRAINAGE);
        binding.tvUtil.setTag(INDEX_UTIL);
        binding.tvMine.setTag(INDEX_MINE);

        textViews = new TextView[SIZE];
        textViews[INDEX_HOME] = binding.tvHome;
        textViews[INDEX_DRAINAGE] = binding.tvDrainage;
        textViews[INDEX_UTIL] = binding.tvUtil;
        textViews[INDEX_MINE] = binding.tvMine;
    }

    /**
     * 注册广播接收者
     */
    private void registerBroadCastReceiver() {
        IntentFilter filter = new IntentFilter(BroadCastUtil.ACTION_REFRESH_STOREINFO);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, filter);
    }

    /**
     * 注销广播接收者
     */
    private void unRegisterBroadCastReceiver() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (BroadCastUtil.ACTION_REFRESH_STOREINFO.equals(intent.getAction())) {
                viewModel.refreshStoryInfo(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), AppContext.getInstanceBase().getStoryUserBean().getZIACCOUNT());
            }
        }
    };

    /**
     * 切换显示
     *
     * @param view 底部Item
     */
    private void switchItem(View view) {
        if (view == null) {
            return;
        }
        try {
            int index = (int) view.getTag();
            if (currIndex == index) {
                return;
            }
            if (textViews != null) {
                if (INVALID != currIndex) {
                    textViews[currIndex].setSelected(false);
                }
                textViews[index].setSelected(true);
                for (int i = 0; i < SIZE; i++) {
                    if (i != index && i != currIndex) {
                        textViews[i].setSelected(false);
                    }
                }
            }
            switchFragment(index, currIndex);
            currIndex = index;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 切换Fragment
     *
     * @param targetIndex 目标Fragment索引
     * @param currIndex   当前Fragment索引
     */
    private void switchFragment(int targetIndex, int currIndex) {
        if (currIndex == INVALID || targetIndex == currIndex) {
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .show(fragments[targetIndex])
                .hide(fragments[currIndex])
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }

    @Override
    public void onClick(View v) {
        if (isFastDoubleClick(v)) {
            return;
        }
        switch (v.getId()) {
            case R.id.tv_home:
            case R.id.tv_drainage:
            case R.id.tv_util:
            case R.id.tv_mine:
                switchItem(v);
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //将当前索引保存
        outState.putInt(KEY, currIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        unRegisterBroadCastReceiver();
        super.onDestroy();
    }

    @Override
    protected int[] getPermissionInfoTips() {
        return new int[]{R.string.permission_write_external_storage_tips,
                R.string.permission_read_phone_state_tips,
                R.string.permission_fine_location_tips,
                R.string.permission_camera_tips};
    }

    @Override
    protected String[] getPermissionArrays() {
        return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA};
    }

}
