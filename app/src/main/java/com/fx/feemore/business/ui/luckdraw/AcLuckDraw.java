package com.fx.feemore.business.ui.luckdraw;

import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcLuckDrawBinding;
import com.fx.feemore.business.ui.home.vm.VMMain;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DateUtils;

import java.util.Calendar;

public class AcLuckDraw extends BaseBindActivity<AcLuckDrawBinding, VMMain> implements View.OnClickListener {
    private AdRateSetting adRateSetting;
    private LuckDrawDialogFragment dialogFragment;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select_type:
                showLuckTypeDialog();
                break;
            case R.id.start_date:
                createTimePicker(v.findViewById(R.id.tv_start_date)).show();
                break;
            case R.id.end_date:
                createTimePicker(v.findViewById(R.id.tv_end_date)).show();
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ac_luck_draw;
    }

    @Override
    protected void init() {
        setToolbarTitle(R.string.publish_luck_draw_title);

        binding.selectType.setOnClickListener(this);
        binding.startDate.setOnClickListener(this);
        binding.endDate.setOnClickListener(this);

        binding.list.setLayoutManager(new LinearLayoutManager(this));
        adRateSetting = new AdRateSetting(R.layout.layout_rate_setting_item);
        binding.list.setAdapter(adRateSetting);

        refreshTvTitleLimit(0);
        refreshTvRemarkLimit(0);

        binding.etTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                refreshTvTitleLimit(binding.etTitle.length());
            }
        });
        binding.etRemark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                refreshTvRemarkLimit(binding.etRemark.length());
            }
        });
    }

    private void refreshTvTitleLimit(int count) {
        binding.tvTitleLimit.setText(getString(R.string.publish_luck_draw_title_input_limit, count));
    }

    private void refreshTvRemarkLimit(int count) {
        binding.tvRemarkCount.setText(getString(R.string.publish_luck_draw_remark_limit, count));
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
                .isCyclic(false)
                .isCenterLabel(true)
                .build();
    }

    private void showLuckTypeDialog() {
        if (dialogFragment == null) {
            dialogFragment = LuckDrawDialogFragment.newInstance();
            dialogFragment.setOnSure(selects -> adRateSetting.replaceData(selects));
            dialogFragment.show(getSupportFragmentManager(), "luckDraw");
        } else {
            dialogFragment.show(getSupportFragmentManager(), "luckDraw");
        }
    }
}
