package com.fx.feemore.business.ui.interest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.CategoryBean;
import com.fx.feemore.business.databinding.AcPublishInterestV2Binding;
import com.fx.feemore.business.ui.interest.adapter.AdPhoto;
import com.fx.feemore.business.ui.interest.vm.VMPublishInterest;
import com.fx.feemore.business.util.CashierInputFilter;
import com.fx.feemore.business.util.ProgressDialogUtil;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.fx.feemore.business.util.TextViewUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DateUtils;
import com.hengxian.common.DensityUtil;
import com.hengxian.common.DisplayUtil;
import com.hengxian.common.Log;
import com.hengxian.common.ToastUtil;
import com.shell.union.selectimage.ConstantValue;
import com.shell.union.selectimage.MultiImageSelectorActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * function:发布权益包
 * author: frj
 * modify date: 2019/1/19
 */
public class AcPublishInterestV2 extends BaseBindActivity<AcPublishInterestV2Binding, VMPublishInterest> implements View.OnClickListener {

    /**
     * 当前使用频次类型，默认不选择
     */
    private int currentUseType = -1;

    private int currentType = 1;

    private static final int MAX_IMG_COUNT = 8;

    private static final String moreningTimes[] = {"00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00"};

    private static final String afternoonTimes[] = {"12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00", "24:00"};

    private static final String weeks[] = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};

    private ProgressDialog dialog;

    private AdPhoto adPhoto;

    private ArrayList<String> selectImgs;

    private OptionsPickerView<CategoryBean> categoryOptionsPickerView;
    private OptionsPickerView<String> businessOptionsPickerView;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_publish_interest_v2;
    }

    @Override
    protected void init() {
        binding.setVm(viewModel);
        setToolbarTitle("发布权益包");

        initLiveData();
        initData();
        initListener();

        handleTimesView(true);
        setUseType(0);
    }

    private void initLiveData() {
        viewModel.categoryListRes.observe(this, list -> {
            categoryOptionsPickerView = createOptionPicker(list);
        });
        viewModel.failRes.observe(this, httpFailBean -> {
            if (dialog != null) {
                dialog.dismiss();
            }
            if (httpFailBean != null) {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.resSucc.observe(this, responseBean -> {
            if (dialog != null) {
                dialog.dismiss();
            }
            ToastUtil.show("发布成功");
            setResult(RESULT_OK);
            finish();
        });
    }

    private void initData() {
        dialog = ProgressDialogUtil.getProgressDialog(this, getString(R.string.progress_tips), true);
        int itemWidth = (DisplayUtil.getScreenSize(this).widthPixels - DensityUtil.dip2px(this, 18) * 5) / 4;

        CashierInputFilter.addFilter(binding.edPrice);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(DensityUtil.dip2px(this, 18), true, false, true));
        adPhoto = new AdPhoto(itemWidth);
        adPhoto.addData(new AdPhoto.Enity(AdPhoto.Enity.TYPE_ADD));
        adPhoto.bindToRecyclerView(binding.recyclerView);
        adPhoto.setOnItemClickListener((adapter, view, position) -> {
            AdPhoto.Enity enity = (AdPhoto.Enity) adapter.getItem(position);
            if (enity != null) {
                if (AdPhoto.Enity.TYPE_ADD == enity.getItemType()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(ConstantValue.EXTRA_TITLE_COLOR, Color.WHITE);
                    bundle.putInt(ConstantValue.EXTRA_SELECT_COUNT, MAX_IMG_COUNT);
                    bundle.putStringArrayList(ConstantValue.EXTRA_DEFAULT_SELECTED_LIST, selectImgs);
                    jump(MultiImageSelectorActivity.class, bundle, ConstantValue.REQUEST_CODE);
                }
            }
        });
        //最多只能选取9张图片
        selectImgs = new ArrayList<>(MAX_IMG_COUNT);
        viewModel.getCategory(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID());

        selectEquityType(1);
        binding.tvPeopleNumTips.setText("使用人数");
        binding.edPeopleNum.setHint("可供几人使用");
        binding.tvPriceTips.setText("价格");
    }

    private void initListener() {
        binding.tvTimesTypeLimited.setOnClickListener(this);
        binding.tvTimesTypeUnlimited.setOnClickListener(this);
        binding.tvConsumeWeekStart.setOnClickListener(this);
        binding.tvConsumeWeekEnd.setOnClickListener(this);
        binding.tvWhetherHoliday.setOnClickListener(this);
        binding.tvConsumeAfternoot.setOnClickListener(this);
        binding.tvConsumeMorening.setOnClickListener(this);
        binding.tvUnpresell.setOnClickListener(this);
        binding.tvPresell.setOnClickListener(this);
        binding.tvInterestType1.setOnClickListener(this);
        binding.tvInterestType2.setOnClickListener(this);
        binding.tvInterestType3.setOnClickListener(this);
        binding.tvInterestType4.setOnClickListener(this);
        binding.tvCommodityType.setOnClickListener(this);
        binding.tvStartTime.setOnClickListener(this);
        binding.tvEndTime.setOnClickListener(this);
        binding.btnSubmit.setOnClickListener(this);

        binding.rateNone.setOnClickListener(this);
        binding.rateMonth.setOnClickListener(this);
        binding.rateWeek.setOnClickListener(this);

        binding.edInterestTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.tvInterestTitleCount.setText(s.length() + "/20");
            }
        });

        binding.edCommodityDes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.tvDesCount.setText(s.length() + "/30");
            }
        });

        binding.edCommodityNotice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.tvCommodityNoticeCount.setText(s.length() + "/50");
            }
        });
    }

    /**
     * 创建日期选择器
     */
    private TimePickerView createTimePicker(TextView textView) {

        //默认选中
        Calendar defaultCalendar = Calendar.getInstance();
        //默认一天后
        defaultCalendar.set(defaultCalendar.get(Calendar.YEAR), defaultCalendar.get(Calendar.MONTH), defaultCalendar.get(Calendar.DAY_OF_MONTH) + 1);
        return new TimePickerBuilder(this, (date, v) -> {
            if (textView != null) {
                String time = DateUtils.DateToStr(date, "yyyy-MM-dd");
                textView.setText(time);
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setCancelText("取消")
                .setSubmitText("确定")
                .setCancelColor(getResources().getColor(R.color.text_light_black))
                .setSubmitColor(getResources().getColor(R.color.colorPrimary))
                .setTextColorOut(getResources().getColor(R.color.text_light_gray))
                .setTextColorCenter(getResources().getColor(R.color.text_dark_black))
                .setDate(defaultCalendar)
                .setTitleSize(18)
                .setContentTextSize(15)
                .setLineSpacingMultiplier(2.0f)
//                .isDialog(true)
                .isCyclic(false)
                .isCenterLabel(true)
                .build();
    }


    /**
     * 创建店铺类型选择器
     */
    private OptionsPickerView<CategoryBean> createOptionPicker(List<CategoryBean> list) {
        OptionsPickerView<CategoryBean> optionsPickerView = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            binding.tvCommodityType.setText(list.get(options1).toString());
            binding.tvCommodityType.setTag(list.get(options1).getCATEGORY_ID());
        })
                .setCancelText("取消")
                .setSubmitText("确定")
                .setCancelColor(getResources().getColor(R.color.text_light_black))
                .setSubmitColor(getResources().getColor(R.color.colorPrimary))
                .setTextColorOut(getResources().getColor(R.color.text_light_gray))
                .setTextColorCenter(getResources().getColor(R.color.text_dark_black))
                .setTitleSize(18)
                .setContentTextSize(15)
                .setLineSpacingMultiplier(2.0f)
                .isCenterLabel(true)
                .build();
        optionsPickerView.setPicker(list);
        return optionsPickerView;
    }

    /**
     * 创建营业时间选择器
     */
    private OptionsPickerView<String> createOptionPicker(List<String> list, TextView textView) {
        OptionsPickerView<String> optionsPickerView = new OptionsPickerBuilder(this, (options1, options2, options3, v) -> {
            if (textView != null) {
                textView.setText(list.get(options1));
            }
        })
                .setCancelText("取消")
                .setSubmitText("确定")
                .setCancelColor(getResources().getColor(R.color.text_light_black))
                .setSubmitColor(getResources().getColor(R.color.colorPrimary))
                .setTextColorOut(getResources().getColor(R.color.text_light_gray))
                .setTextColorCenter(getResources().getColor(R.color.text_dark_black))
                .setTitleSize(18)
                .setContentTextSize(15)
                .setLineSpacingMultiplier(2.0f)
                .isCenterLabel(true)
                .build();
        optionsPickerView.setPicker(list);
        return optionsPickerView;
    }

    /**
     * 发布
     */
    private void publish() {
//        binding.tvInterestNormalType.isSelected() ? "请输入可使用人数" : "请输入成团人数",
        if (VerificationUtil.requiredFieldValidator(new View[]{binding.edInterestTitle, binding.edCommodityDes, binding.edPrice, binding.edSaleNum, binding.tvCommodityType, binding.tvStartTime, binding.tvEndTime, binding.tvConsumeWeekStart, binding.tvConsumeWeekEnd, binding.tvConsumeMorening, binding.tvConsumeAfternoot, binding.edContact, binding.edCommodityNotice},
                new String[]{"请输入权益包名称", "请输入权益包描述", "请输入权益包价格", "请输入权益包可售出数量", "请输入权益包类型", "请输入开始时间", "请输入结束时间", "请输入一周内可使用的时间", "请输入一周内可使用的时间", "请输入上午开始消费时间", "请输入下午结束消费时间", "请输入联系方式", "请输入商家公告"})) {
            if (binding.edPeopleNum.isShown()) {
                if (!VerificationUtil.validator(binding.edPeopleNum, "请输入可使用人数")) {
                    return;
                }
            }

            if (!binding.tvTimesTypeUnlimited.isSelected()) {
                if (binding.ivRateMonth.isSelected() || binding.rateWeek.isSelected()) {
                    if (!VerificationUtil.validator(binding.etRateTimes, "请输入使用频次次数")) {
                        return;
                    }
                }
            }
            if (!VerificationUtil.noEmpty(selectImgs)) {
                ToastUtil.show("请选择权益包图片");
                return;
            }

            int price = 0;
            try {
                //价格单位为分
                price = (int) (Double.parseDouble(TextViewUtil.getText(binding.edPrice)) * 100);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                ToastUtil.show("价格输入错误");
                return;
            }
            if (dialog != null) {
                dialog.show();
            }
            String times = TextViewUtil.getText(binding.etRateTimes);
            if (TextUtils.isEmpty(times)) {
                times = "9999";
            }
            Log.i("type:" + String.valueOf(currentType));
            viewModel.handleImgs(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(),
                    TextViewUtil.getText(binding.edInterestTitle),
                    TextViewUtil.getText(binding.edCommodityDes),
                    binding.tvCommodityType.getTag().toString(),
                    "1",
                    binding.edPeopleNum.isShown() ? TextViewUtil.getText(binding.edPeopleNum) : "1",
                    TextViewUtil.getText(binding.edSaleNum),
                    times,
                    TextViewUtil.getText(binding.tvStartTime),
                    TextViewUtil.getText(binding.tvEndTime),
                    String.valueOf(price),
                    binding.tvPresell.isSelected() ? "1" : "0",
                    binding.switchBtn.isChecked() ? "1" : "0",
                    TextViewUtil.getText(binding.tvConsumeMorening),
                    TextViewUtil.getText(binding.tvConsumeAfternoot),
                    TextViewUtil.getText(binding.edContact),
                    binding.tvWhetherHoliday.isSelected() ? "1" : "0",
                    TextViewUtil.getText(binding.tvConsumeWeekStart),
                    TextViewUtil.getText(binding.tvConsumeWeekEnd),
                    TextViewUtil.getText(binding.edCommodityNotice),
                    String.valueOf(currentUseType),
                    String.valueOf(currentType),
                    selectImgs
            );
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ConstantValue.REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            if (data == null) {
                return;
            }
            ArrayList<String> list = data.getStringArrayListExtra(ConstantValue.EXTRA_RESULT);
            selectImgs.clear();
            selectImgs.addAll(list);
            //最多选取8张照片
            int size = VerificationUtil.getSize(list);
            //清除现有显示
            adPhoto.getData().clear();
            //重新添加新的资源
            for (int i = 0; i < size; i++) {
                AdPhoto.Enity entity = new AdPhoto.Enity(AdPhoto.Enity.TYPE_NORMAL, list.get(i));
                adPhoto.getData().add(entity);
            }
            if (size < MAX_IMG_COUNT) {
                adPhoto.getData().add(new AdPhoto.Enity(AdPhoto.Enity.TYPE_ADD));
            }
            adPhoto.notifyDataSetChanged();
        }
    }


    @Override
    public void onClick(View v) {
        if (isFastDoubleClick(v)) {
            return;
        }
        switch (v.getId()) {
            case R.id.tv_times_type_limited:
                handleTimesView(true);
                break;
            case R.id.tv_times_type_unlimited:
                handleTimesView(false);
                break;
            case R.id.tv_consume_week_start:
                createOptionPicker(Arrays.asList(weeks), binding.tvConsumeWeekStart).show();
                break;
            case R.id.tv_consume_week_end:
                createOptionPicker(Arrays.asList(weeks), binding.tvConsumeWeekEnd).show();
                break;
            case R.id.tv_whether_holiday:
                v.setSelected(!v.isSelected());
                break;
            case R.id.tv_consume_afternoot:
                createOptionPicker(Arrays.asList(afternoonTimes), binding.tvConsumeAfternoot).show();
                break;
            case R.id.tv_consume_morening:
                createOptionPicker(Arrays.asList(moreningTimes), binding.tvConsumeMorening).show();
                break;
            case R.id.tv_unpresell:
                binding.tvUnpresell.setSelected(!binding.tvUnpresell.isSelected());
                if (binding.tvPresell.isSelected()) {
                    binding.tvPresell.setSelected(false);
                }
                break;
            case R.id.tv_presell:
                binding.tvPresell.setSelected(!binding.tvPresell.isSelected());
                if (binding.tvUnpresell.isSelected()) {
                    binding.tvUnpresell.setSelected(false);
                }
                break;
            case R.id.tv_interest_type_1:
                selectEquityType(1);
                break;
            case R.id.tv_interest_type_2:
                selectEquityType(3);
                break;
            case R.id.tv_interest_type_3:
                selectEquityType(4);
                break;
            case R.id.tv_interest_type_4:
                selectEquityType(2);
                break;
            case R.id.tv_commodity_type:
                if (categoryOptionsPickerView != null) {
                    categoryOptionsPickerView.show();
                }
                break;
            case R.id.tv_start_time:
                createTimePicker(binding.tvStartTime).show();
                break;
            case R.id.tv_end_time:
                createTimePicker(binding.tvEndTime).show();
                break;
            case R.id.btn_submit:
                publish();
                break;
            case R.id.rate_none:
                setUseType(0);
                break;
            case R.id.rate_month:
                setUseType(2);
                break;
            case R.id.rate_week:
                setUseType(3);
                break;
        }
    }

    private void setUseType(int typeCode) {
        currentUseType = typeCode;
        binding.ivRateNone.setSelected(false);
        binding.ivRateMonth.setSelected(false);
        binding.ivRateWeek.setSelected(false);
        switch (typeCode) {
            case 0:
                //无
                binding.ivRateNone.setSelected(true);
                binding.etRateTimes.setText("1");
                break;
            case 2:
                //次/月
                binding.ivRateMonth.setSelected(true);
                binding.etRateTimes.setText("");
                break;
            case 3:
                //次/周
                binding.ivRateWeek.setSelected(true);
                binding.etRateTimes.setText("");
                break;
        }
    }

    private void selectEquityType(int index) {
        binding.tvInterestType1.setSelected(false);
        binding.tvInterestType2.setSelected(false);
        binding.tvInterestType3.setSelected(false);
        binding.tvInterestType4.setSelected(false);
        switch (index) {
            case 1:
                binding.tvInterestType1.setSelected(true);
                break;
            case 2:
                binding.tvInterestType4.setSelected(true);
                break;
            case 3:
                binding.tvInterestType2.setSelected(true);
                break;
            case 4:
                binding.tvInterestType3.setSelected(true);
                break;
        }
        currentType = index;
    }

    private void handleTimesView(boolean isLimit) {
        binding.tvTimesTypeLimited.setSelected(isLimit);
        binding.tvTimesTypeUnlimited.setSelected(!isLimit);
        binding.rate.setVisibility(isLimit ? View.VISIBLE : View.GONE);
    }
}
