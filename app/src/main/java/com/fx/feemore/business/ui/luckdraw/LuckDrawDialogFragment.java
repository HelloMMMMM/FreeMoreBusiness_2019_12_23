package com.fx.feemore.business.ui.luckdraw;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fx.feemore.business.R;

import java.util.ArrayList;
import java.util.List;

public class LuckDrawDialogFragment extends DialogFragment implements View.OnClickListener {

    private AdLuckDrawType adLuckDrawType;
    private View ivSelectAll;
    private OnSure onSure;

    public static LuckDrawDialogFragment newInstance() {
        Bundle args = new Bundle();
        LuckDrawDialogFragment fragment = new LuckDrawDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void setOnSure(OnSure onSure) {
        this.onSure = onSure;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
        }
        View view = inflater.inflate(R.layout.dialog_luck_draw_type, container, false);
        init(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Dialog_Bottom);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setLayout(-1, -2);
        }
    }

    private void init(View view) {
        ivSelectAll = view.findViewById(R.id.iv_select_all);

        view.findViewById(R.id.iv_close).setOnClickListener(this);
        view.findViewById(R.id.select_all).setOnClickListener(this);
        view.findViewById(R.id.tv_sure).setOnClickListener(this);

        RecyclerView list = view.findViewById(R.id.list);
        ((SimpleItemAnimator) list.getItemAnimator()).setSupportsChangeAnimations(false);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        adLuckDrawType = new AdLuckDrawType(R.layout.luck_draw_item);
        List<LuckDrawTypeBean> luckDrawTypeBeans = new ArrayList<>();
        luckDrawTypeBeans.add(new LuckDrawTypeBean("超值单人套餐超值单人套餐", "幸福西饼", false));
        luckDrawTypeBeans.add(new LuckDrawTypeBean("肩颈脊柱养生肩颈脊柱养生", "尤美有氧科技皮肤管理中心", false));
        luckDrawTypeBeans.add(new LuckDrawTypeBean("头疗放松+腹部艾灸/热敷2选1", "悦辰美容皮肤管理", false));
        luckDrawTypeBeans.add(new LuckDrawTypeBean("日式樱花美人日式樱花美人", "美丽之源美颜纤体养生", false));
        luckDrawTypeBeans.add(new LuckDrawTypeBean("肩颈疏通/腰背疏通二选一（惊爆体验价）", "PD.PinDream", false));
        luckDrawTypeBeans.add(new LuckDrawTypeBean("快速祛痘痘肌 只留青春不留痘", "镜面祛斑祛痘皮肤管理", false));
        adLuckDrawType.addData(luckDrawTypeBeans);
        adLuckDrawType.setOnItemClickListener((adapter, view1, position) -> {
            LuckDrawTypeBean bean = (LuckDrawTypeBean) adapter.getItem(position);
            if (bean != null) {
                bean.setSelected(!bean.isSelected());
                view1.setSelected(bean.isSelected());
                adLuckDrawType.notifyItemChanged(position);
                refreshIvSelectAll();
            }
        });
        list.setAdapter(adLuckDrawType);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.select_all:
                selectAllOrNoSelect(!ivSelectAll.isSelected());
                break;
            case R.id.tv_sure:
                if (onSure != null) {
                    onSure.onSure(getSelected());
                    dismiss();
                }
                break;
        }
    }

    private void selectAllOrNoSelect(boolean isSelectAll) {
        List<LuckDrawTypeBean> luckDrawTypeBeans = adLuckDrawType.getData();
        for (LuckDrawTypeBean bean : luckDrawTypeBeans) {
            bean.setSelected(isSelectAll);
        }
        adLuckDrawType.notifyDataSetChanged();
        refreshIvSelectAll();
    }

    private void refreshIvSelectAll() {
        ivSelectAll.setSelected(hasSelectAll());
    }

    private boolean hasSelectAll() {
        List<LuckDrawTypeBean> luckDrawTypeBeans = adLuckDrawType.getData();
        for (LuckDrawTypeBean bean : luckDrawTypeBeans) {
            if (!bean.isSelected()) {
                return false;
            }
        }
        return true;
    }

    private List<LuckDrawTypeBean> getSelected() {
        List<LuckDrawTypeBean> selects = new ArrayList<>();
        List<LuckDrawTypeBean> luckDrawTypeBeans = adLuckDrawType.getData();
        for (LuckDrawTypeBean bean : luckDrawTypeBeans) {
            if (bean.isSelected()) {
                selects.add(bean);
            }
        }
        return selects;
    }

    public interface OnSure {
        void onSure(List<LuckDrawTypeBean> selects);
    }
}
