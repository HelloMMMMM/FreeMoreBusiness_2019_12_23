package com.fx.feemore.business.ui.character;

import android.Manifest;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.BarUtils;
import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.databinding.AcCharacterSelectedBinding;
import com.fx.feemore.business.location.BDLocationUtils;
import com.fx.feemore.business.ui.apply.AcApplyRecord;
import com.fx.feemore.business.ui.home.MainActivity;
import com.fx.feemore.business.ui.login.AcLogin;
import com.hengxian.baselib.base.BaseBindActivity;

/**
 * function:角色选择页面
 * author: frj
 * modify date: 2018/12/23
 */
public class AcCharacterSelected extends BaseBindActivity<AcCharacterSelectedBinding, VMCharacter> {
    @Override
    protected int getLayoutId() {
        return R.layout.ac_character_selected;
    }

    @Override
    protected void init() {
        //获取经纬度
        BDLocationUtils bdLocationUtils = new BDLocationUtils(this);
        bdLocationUtils.doLocation();//开启定位
        bdLocationUtils.mLocationClient.start();//开始定位

        binding.setVm(viewModel);

        if (AppContext.getInstanceBase().getStoryUserBean() != null && !TextUtils.isEmpty(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID())) {
            if (1 == AppContext.getInstanceBase().getStoryUserBean().getSTATUS()) {
                jump(MainActivity.class, true);
            } else {
                jump(AcApplyRecord.class, true);
            }
        }

        binding.btnBusiness.setOnClickListener(v -> {
            viewModel.setCharacter(VMCharacter.CHARACTER_BUSINESS);
            if (AppContext.getInstanceBase().getStoryUserBean() != null && !TextUtils.isEmpty(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID())) {
                if (1 == AppContext.getInstanceBase().getStoryUserBean().getSTATUS()) {
                    jump(MainActivity.class, true);
                } else {
                    jump(AcApplyRecord.class, true);
                }
            } else {
                jump(AcLogin.class, true);
            }
        });
        binding.btnProxy.setOnClickListener(v -> {
            viewModel.setCharacter(VMCharacter.CHARACTER_PROXY);
            jump(AcLogin.class, true);
        });
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
