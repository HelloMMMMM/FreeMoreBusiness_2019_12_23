package com.fx.feemore.business.ui.register;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.baidu.mapapi.model.LatLng;
import com.fx.feemore.business.R;
import com.fx.feemore.business.bean.CategoryBean;
import com.fx.feemore.business.databinding.AcInputDataBinding;
import com.fx.feemore.business.ui.apply.AcApplyRecord;
import com.fx.feemore.business.util.Params;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.baselib.glide.GlideApp;
import com.hengxian.baselib.utils.Arithmetic;
import com.hengxian.common.ToastUtil;
import com.shell.union.selectimage.ConstantValue;
import com.shell.union.selectimage.MultiImageSelectorActivity;

import java.util.ArrayList;

/**
 * function:填写资料;接收参数：KEY_STR
 * author: frj
 * modify date: 2018/12/23
 */
public class AcInputData extends BaseBindActivity<AcInputDataBinding, VMInputData> implements View.OnClickListener {

    private static final int REQUEST_MAP_SELECTION = 0x10;

    //区域码
    private String areaCode;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_input_data;
    }

    @Override
    protected void init() {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.input_data_title);
        binding.btnConfirm.setOnClickListener(v -> {
            if (TextUtils.isEmpty(viewModel.nameStr.get())) {
                ToastUtil.show("请输入店铺名称");
                return;
            }
            if (TextUtils.isEmpty(viewModel.addressStr.get())) {
                ToastUtil.show("请在地图上选择店铺位置");
                return;
            }
            LatLng latLng = (LatLng) binding.tvAddress.getTag();
            if (latLng == null) {
                ToastUtil.show("选点无效，请重新选点");
                return;
            }
            String shopPhoto = binding.imgShopPhoto.getTag(R.integer.tag_photo).toString();
            if (TextUtils.isEmpty(shopPhoto)) {
                ToastUtil.show("请选择店铺图片");
                return;
            }
            String environmengtalPhoto = binding.imgEnvironmengtalPhoto.getTag(R.integer.tag_photo).toString();
            if (TextUtils.isEmpty(environmengtalPhoto)) {
                ToastUtil.show("请选择店内环境图片");
                return;
            }
            String idcardPhoto = binding.imgIdcardPhoto.getTag(R.integer.tag_photo).toString();
            if (TextUtils.isEmpty(idcardPhoto)) {
                ToastUtil.show("请选择身份证照片");
                return;
            }
            String licensePhoto = binding.imgLicensePhoto.getTag(R.integer.tag_photo).toString();
            if (TextUtils.isEmpty(licensePhoto)) {
                ToastUtil.show("请选择营业执照照片");
                return;
            }
            viewModel.registerStore(viewModel.nameStr.get(), getIntent().getStringExtra(KEY_STR), areaCode, viewModel.addressStr.get(), Arithmetic.doubleToStr(latLng.longitudeE6, 8), Arithmetic.doubleToStr(latLng.latitudeE6, 8), ((CategoryBean) binding.spType.getSelectedItem()).getCATEGORY_ID(), shopPhoto, environmengtalPhoto, idcardPhoto, licensePhoto);
        });

        binding.tvAddress.setOnClickListener(this);
        binding.ibCheck.setOnClickListener(this);
        binding.tvProtocol.setOnClickListener(this);

        initLiveData();

        viewModel.getStoryCategory();
    }

    private void initLiveData() {
        viewModel.compressPathRes.observe(this, value -> {
            if (value != null) {
                ImageView view;
                if (binding.imgShopPhoto.isSelected()) {
                    view = binding.imgShopPhoto;

                } else if (binding.imgEnvironmengtalPhoto.isSelected()) {
                    view = binding.imgEnvironmengtalPhoto;
                } else if (binding.imgIdcardPhoto.isSelected()) {
                    view = binding.imgIdcardPhoto;
                } else {
                    view = binding.imgLicensePhoto;
                }
                GlideApp.with(AcInputData.this).load(value).into(view);
                view.setSelected(false);
                view.setTag(R.integer.tag_photo, value.getAbsolutePath());
            }
        });
        viewModel.httpFailRes.observe(this, httpFailBean -> {
            if (httpFailBean != null) {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.registerSuccRes.observe(this, res -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("商家注册信息提交成功，请等待审核完成。\n审核完成之后，可通过手机号登录至您的商户后台~");
            builder.setPositiveButton("确定", (dialog, which) -> {
                Intent intent = new Intent(AcInputData.this, AcApplyRecord.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            });
            builder.show();
        });
        viewModel.categoriesSuccRes.observe(this, list -> {
            ArrayAdapter<CategoryBean> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.support.v7.appcompat.R.layout.support_simple_spinner_dropdown_item);
            binding.spType.setAdapter(adapter);
        });
    }

    /**
     * 跳转至选择图片页面
     */
    public void gotoSelectImage(View view) {
        view.setSelected(true);
        Bundle bundle = new Bundle();
        bundle.putInt(ConstantValue.EXTRA_TITLE_COLOR, Color.WHITE);
        bundle.putInt(ConstantValue.EXTRA_SELECT_MODE, ConstantValue.MODE_SINGLE);
        jump(MultiImageSelectorActivity.class, bundle, ConstantValue.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ConstantValue.REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            if (data == null) {
                return;
            }
            ArrayList<String> list = data.getStringArrayListExtra(ConstantValue.EXTRA_RESULT);
            //最多选取9张照片
            if (VerificationUtil.noEmpty(list)) {
                viewModel.compressBitmap(list.get(0));
            }
        } else if (REQUEST_MAP_SELECTION == requestCode && RESULT_OK == resultCode) {
            if (data != null) {
                viewModel.addressStr.set(data.getStringExtra(KEY_STR));
                LatLng latLng = data.getParcelableExtra(KEY);
                binding.tvAddress.setTag(latLng);
                areaCode = data.getStringExtra(Params.PARAM_AREA_CODE);
            }
        }
    }

    @Override
    protected String[] getPermissionArrays() {
        return new String[]{Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION};
    }

    @Override
    protected int[] getPermissionInfoTips() {
        return new int[]{R.string.permission_camera_tips, R.string.permission_fine_location_tips};
    }

    @Override
    public void onClick(View v) {
        if (isFastDoubleClick(v)) {
            return;
        }
        switch (v.getId()) {
            case R.id.tv_address:
                jump(AcMapSelection.class, REQUEST_MAP_SELECTION);
                break;
            case R.id.ib_check:
                break;
            case R.id.tv_protocol:
                break;
        }
    }
}
